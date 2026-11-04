package top.niunaijun.blackboxa.api

import android.content.Context
import android.util.Log
import org.json.JSONObject
import top.niunaijun.blackbox.BlackBoxCore
import top.niunaijun.blackbox.core.env.BEnvironment
import top.niunaijun.blackbox.utils.FileUtils
import top.niunaijun.blackboxa.bean.AppReferrerConfig
import top.niunaijun.blackboxa.bean.AppReferrerStore
import java.io.File

/**
 * 安装逻辑的唯一入口，REST 与任务引擎共用，保证两边行为一致。
 *
 * <p>默认「先卸后装」：目标包名已在容器内安装时先卸载（同时清掉旧的 proxy / gaid / device），
 * 再执行安装，等价于覆盖安装 / 重装。可用 `reinstall=false` 关闭该行为。</p>
 */
object InstallOps {

    private const val TAG = "InstallOps"

    data class Outcome(
        val success: Boolean,
        val packageName: String,
        val message: String,
        val reinstalled: Boolean,
        val referrer: String?,
        val config: JSONObject?
    )

    /**
     * @param url 远程地址（下载后安装），与 path / packageName 三选一
     * @param path 容器可见的本地路径
     * @param packageName 设备上已安装的同包名应用
     * @param config 安装成功后写入的配置
     * @param reinstall 目标已存在时是否先卸载（默认 true）
     */
    fun install(
        context: Context,
        url: String,
        path: String,
        packageName: String,
        config: JSONObject?,
        reinstall: Boolean,
        referrer: String?,
        userId: Int
    ): Outcome {
        // 冷启动/服务重启时先等包管理器就绪，否则后面的 install 会拿到 null
        awaitPackageManager(userId)
        var file: File? = when {
            url.isNotEmpty() -> Downloader.download(context, url)
            path.isNotEmpty() -> File(path)
            else -> null
        }
        // url / path 场景要解析 apk 才能知道包名
        var archiveName = if (file != null) archivePackageName(context, file) else null
        if (file != null && archiveName == null) {
            throw IllegalArgumentException("invalid apk: ${file.name}")
        }

        // package 源：设备上找不到就退回容器内已安装的 APK，避免「先卸载再发现没有源」
        if (file == null) {
            if (hostHasPackage(context, packageName)) {
                archiveName = packageName
            } else if (isInstalled(packageName, userId)) {
                val installed = containerApk(packageName)
                    ?: throw IllegalArgumentException(
                        "package $packageName has no usable apk in container"
                    )
                val cached = File(context.cacheDir, "reinstall-${System.currentTimeMillis()}.apk")
                FileUtils.copyFile(installed, cached)
                file = cached
                archiveName = packageName
                Log.d(TAG, "reinstall source: container apk ${installed.absolutePath}")
            } else {
                throw IllegalArgumentException(
                    "package $packageName is not installed on the device or in the container"
                )
            }
        }

        val target = archiveName ?: packageName

        var reinstalled = false
        if (reinstall && target.isNotEmpty() && isInstalled(target, userId)) {
            try {
                BlackBoxCore.get().uninstallPackageAsUser(target, userId)
                ConfigOps.clear(context, target)
                reinstalled = true
                Log.d(TAG, "uninstalled existing $target before reinstall")
                try {
                    Thread.sleep(200L)
                } catch (ignored: InterruptedException) {
                    Thread.currentThread().interrupt()
                }
            } catch (e: Throwable) {
                Log.w(TAG, "uninstall existing $target failed: ${e.message}")
            }
        }

        // 卸载会把承载 BPackageManagerService 的进程带走，紧接着的 install 会拿到 null，
        // 这里等一会儿再带退避重试。
        var result: top.niunaijun.blackbox.entity.pm.InstallResult? = null
        var attempt = 0
        while (result == null && attempt < 5) {
            if (attempt > 0) {
                try {
                    Thread.sleep(300L * attempt)
                } catch (ignored: InterruptedException) {
                    Thread.currentThread().interrupt()
                }
                Log.w(TAG, "install retry #$attempt for ${packageName.ifEmpty { file?.name }}")
            }
            result = installOnce(file, packageName, userId)
            attempt++
        }
        if (result == null) {
            throw IllegalStateException(
                "install service returned null after $attempt attempts (package manager restarting?)"
            )
        }

        val installed = result.packageName?.takeIf { it.isNotEmpty() }
            ?: target.ifEmpty { packageName }
        var savedReferrer: String? = null
        if (result.success && installed.isNotEmpty() && !referrer.isNullOrBlank()) {
            try {
                val normalized = AppReferrerConfig.normalize(referrer)
                if (normalized.isNotEmpty()) {
                    val now = System.currentTimeMillis() / 1000L
                    AppReferrerStore.save(
                        context, installed,
                        AppReferrerConfig(
                            referrer = normalized,
                            clickTimestamp = now,
                            installTimestamp = now
                        )
                    )
                    savedReferrer = normalized
                }
            } catch (e: Throwable) {
                Log.w(TAG, "save referrer failed: ${e.message}")
            }
        }
        var applied: JSONObject? = null
        if (result.success && installed.isNotEmpty() && config != null) {
            try {
                applied = ConfigOps.apply(context, installed, config)
            } catch (e: Throwable) {
                Log.w(TAG, "apply config after install failed: ${e.message}")
            }
        }
        return Outcome(
            success = result.success,
            packageName = installed,
            message = result.msg ?: "",
            reinstalled = reinstalled,
            referrer = savedReferrer,
            config = applied
        )
    }

    /**
     * 以「已安装列表」为准：BlackManager 的 isInstalled() 走 binder，
     * 服务重启/降级时会返回 false 负值，导致跳过卸载或误判未安装。
     */
    fun isInstalled(packageName: String, userId: Int): Boolean = try {
        val listed = BlackBoxCore.getBPackageManager()
            .getInstalledPackagesAsUser(userId)
            .any { it.packageName == packageName }
        if (listed) {
            true
        } else {
            BlackBoxCore.get().isInstalled(packageName, userId)
        }
    } catch (e: Throwable) {
        Log.w(TAG, "check installed state of $packageName failed: ${e.message}")
        false
    }

    /** 等到 IBPackageManagerService 可用（最多 [timeoutMs]），避免冷启动瞬间拿到 null。 */
    private fun awaitPackageManager(userId: Int, timeoutMs: Long = 5000L): Boolean {
        val deadline = System.currentTimeMillis() + timeoutMs
        var waited = 0L
        while (System.currentTimeMillis() < deadline) {
            try {
                if (BlackBoxCore.getBPackageManager().getInstalledPackagesAsUser(userId) != null) {
                    if (waited > 0) {
                        Log.d(TAG, "package manager ready after ${waited}ms")
                    }
                    return true
                }
            } catch (e: Throwable) {
                Log.w(TAG, "package manager not ready: ${e.message}")
            }
            try {
                Thread.sleep(200L)
            } catch (ignored: InterruptedException) {
                Thread.currentThread().interrupt()
                return false
            }
            waited += 200L
        }
        Log.w(TAG, "package manager still unavailable after ${timeoutMs}ms")
        return false
    }

    private fun installOnce(
        file: File?,
        packageName: String,
        userId: Int
    ): top.niunaijun.blackbox.entity.pm.InstallResult? = try {
        when {
            file != null -> BlackBoxCore.get().installPackageAsUser(file, userId)
            packageName.isNotEmpty() -> BlackBoxCore.get().installPackageAsUser(packageName, userId)
            else -> throw IllegalArgumentException("one of url, path or package is required")
        }
    } catch (e: IllegalArgumentException) {
        throw e
    } catch (e: Throwable) {
        Log.w(TAG, "install attempt failed: ${e.message}")
        null
    }

    /** 设备（宿主 PackageManager）上是否存在该包，用于判断 package 源能不能直接复制。 */
    private fun hostHasPackage(context: Context, packageName: String): Boolean = try {
        context.packageManager.getPackageInfo(packageName, 0) != null
    } catch (e: Throwable) {
        false
    }

    /** 容器内已安装应用的 base.apk（重装时可以拿它当源）。 */
    private fun containerApk(packageName: String): File? = try {
        // 注意：getBaseApkDir 返回的就是 base.apk 文件本身
        val apk = BEnvironment.getBaseApkDir(packageName)
        if (apk != null && apk.exists()) apk else null
    } catch (e: Throwable) {
        Log.w(TAG, "locate container apk of $packageName failed: ${e.message}")
        null
    }

    private fun archivePackageName(context: Context, file: File): String? = try {
        if (!file.exists()) {
            null
        } else {
            context.packageManager.getPackageArchiveInfo(file.absolutePath, 0)?.packageName
        }
    } catch (e: Throwable) {
        Log.w(TAG, "read package name from ${file.name} failed: ${e.message}")
        null
    }
}
