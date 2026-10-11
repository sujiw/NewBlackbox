package top.niunaijun.blackboxa.util

import android.content.pm.ApplicationInfo
import android.os.Parcel
import android.util.AtomicFile
import top.niunaijun.blackbox.BlackBoxCore
import top.niunaijun.blackbox.core.env.BEnvironment
import top.niunaijun.blackbox.core.system.pm.BPackageSettings
import java.io.File

/**
 * Per virtual-app capture mode.
 *
 * Rewrites the virtual package configuration (package.conf parcel) inside the
 * engine, lowering the app's visible targetSdkVersion below 24. The framework
 * default network security config then trusts user-installed CAs, so a proxy
 * CA installed through the system KeyChain (no root) is accepted.
 *
 * The APK on disk is untouched, so no signature change and no tamper
 * detection. Apps with code-level pinning or Play Integrity still need root
 * or a Frida gadget.
 *
 * NOTE: virtual apps must be fully stopped before toggling, otherwise the
 * running engine can persist the old settings back.
 */
object VirtualCapturePatcher {

    const val CAPTURE_TARGET_SDK = 23

    /** Virtual-user id used by the container. */
    const val DEFAULT_USER_ID = 0

    data class AppCapture(
        val packageName: String,
        val label: String,
        val targetSdk: Int,
        val confExists: Boolean
    ) {
        val captureEnabled: Boolean get() = targetSdk in 1..23
    }

    fun listVirtualApps(): List<AppCapture> {
        val infos = try {
            BlackBoxCore.get().getInstalledApplications(0, DEFAULT_USER_ID)
        } catch (e: Exception) {
            null
        } ?: return emptyList()
        return infos.map { info ->
            val sdk = readTargetSdk(info.packageName)
            AppCapture(
                packageName = info.packageName,
                label = info.loadLabel(BlackBoxCore.getPackageManager()).toString(),
                targetSdk = sdk ?: info.targetSdkVersion,
                confExists = sdk != null
            )
        }.sortedBy { it.label }
    }

    /** Original targetSdk of a virtual app, or null when unavailable. */
    fun readTargetSdk(packageName: String): Int? {
        val conf = confFile(packageName) ?: return null
        return try {
            unmarshall(conf.readBytes()).pkg.applicationInfo.targetSdkVersion
        } catch (e: Exception) {
            null
        }
    }

    /**
     * @return old value to new value
     */
    fun setCaptureMode(packageName: String, enable: Boolean): Pair<Int, Int> {
        val conf = confFile(packageName) ?: throw CertException("package.conf not found")
        val bytes = conf.readBytes()
        val ps = unmarshall(bytes)
        val old = ps.pkg.applicationInfo.targetSdkVersion
        ps.pkg.applicationInfo.targetSdkVersion =
            if (enable) CAPTURE_TARGET_SDK else restoreTargetSdk(packageName, old)
        save(conf, ps)
        rememberTargetSdk(packageName, old)
        return old to ps.pkg.applicationInfo.targetSdkVersion
    }

    private fun confFile(packageName: String): File? {
        val f = try {
            BEnvironment.getPackageConf(packageName)
        } catch (e: Exception) {
            null
        } ?: return null
        return if (f.isFile) f else null
    }

    private fun unmarshall(bytes: ByteArray): BPackageSettings {
        val parcel = Parcel.obtain()
        try {
            parcel.unmarshall(bytes, 0, bytes.size)
            parcel.setDataPosition(0)
            return BPackageSettings.CREATOR.createFromParcel(parcel)
                ?: throw CertException("bad package.conf")
        } finally {
            parcel.recycle()
        }
    }

    private fun save(conf: File, ps: BPackageSettings) {
        val parcel = Parcel.obtain()
        try {
            ps.writeToParcel(parcel, 0)
            val data = parcel.marshall()
            val af = AtomicFile(conf)
            val fos = af.startWrite()
            fos.write(data)
            af.finishWrite(fos)
        } catch (e: Exception) {
            throw CertException("save failed: ${e.message}")
        } finally {
            parcel.recycle()
        }
    }

    // keep the original value so the mode can be reverted
    private val prefs get() = AppPrefs.captureTargetSdk

    private fun rememberTargetSdk(pkg: String, value: Int) {
        if (!prefs.contains(pkg)) prefs.edit().putInt(pkg, value).apply()
    }

    private fun restoreTargetSdk(pkg: String, fallback: Int): Int {
        val v = prefs.getInt(pkg, 0)
        return if (v in 1..99) {
            prefs.edit().remove(pkg).apply()
            v
        } else fallback
    }

    fun isVirtualAppRunning(packageName: String): Boolean =
        BlackBoxCore.isRunningApplication(packageName, DEFAULT_USER_ID)
}
