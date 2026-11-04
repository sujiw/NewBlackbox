package top.niunaijun.blackboxa.api

import android.content.Context
import android.util.Log
import org.json.JSONObject
import top.niunaijun.blackbox.BlackBoxCore
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
        val file: File? = when {
            url.isNotEmpty() -> Downloader.download(context, url)
            path.isNotEmpty() -> File(path)
            else -> null
        }
        // url / path 场景要解析 apk 才能知道包名；解析失败就退回请求里的 package
        val target = when {
            file != null -> archivePackageName(context, file) ?: packageName
            else -> packageName
        }

        var reinstalled = false
        if (reinstall && target.isNotEmpty() && isInstalled(target, userId)) {
            try {
                BlackBoxCore.get().uninstallPackageAsUser(target, userId)
                ConfigOps.clear(context, target)
                reinstalled = true
                Log.d(TAG, "uninstalled existing $target before reinstall")
            } catch (e: Throwable) {
                Log.w(TAG, "uninstall existing $target failed: ${e.message}")
            }
        }

        val result = when {
            file != null -> BlackBoxCore.get().installPackageAsUser(file, userId)
            packageName.isNotEmpty() -> BlackBoxCore.get().installPackageAsUser(packageName, userId)
            else -> throw IllegalArgumentException("one of url, path or package is required")
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

    fun isInstalled(packageName: String, userId: Int): Boolean = try {
        BlackBoxCore.get().isInstalled(packageName, userId)
    } catch (e: Throwable) {
        Log.w(TAG, "check installed state of $packageName failed: ${e.message}")
        false
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
