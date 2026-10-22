package top.niunaijun.blackboxa.view.automation

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.util.Log
import android.view.View
import top.niunaijun.blackbox.BlackBoxCore
import top.niunaijun.blackbox.entity.pm.InstallResult
import top.niunaijun.blackboxa.R
import top.niunaijun.blackboxa.api.ApiService
import top.niunaijun.blackboxa.api.ApiSettings
import top.niunaijun.blackboxa.api.NetworkUtils
import top.niunaijun.blackboxa.bean.AppDeviceStore
import top.niunaijun.blackboxa.bean.AppGaidStore
import top.niunaijun.blackboxa.bean.AppProxyStore
import top.niunaijun.blackboxa.databinding.ActivityAutomationBinding
import top.niunaijun.blackboxa.databinding.ItemAutomationAppBinding
import top.niunaijun.blackboxa.util.SafeKeyListener
import top.niunaijun.blackboxa.util.inflate
import top.niunaijun.blackboxa.util.toast
import top.niunaijun.blackboxa.view.base.LoadingActivity
import top.niunaijun.blackboxa.view.config.AppConfigActivity
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Host side control panel for the local automation API.
 *
 * Keeps the api service switch, token, all-files-access shortcut, apk
 * installation from url/path and the list of installed apps in one screen.
 */
class AutomationActivity : LoadingActivity() {

    companion object {
        private const val TAG = "AutomationActivity"

        fun start(context: Context) {
            context.startActivity(Intent(context, AutomationActivity::class.java))
        }
    }

    private val viewBinding: ActivityAutomationBinding by inflate()

    private val executor: ExecutorService = Executors.newSingleThreadExecutor()

    private data class AppRow(
        val label: String,
        val packageName: String,
        val icon: Drawable?,
        val badges: String
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        initToolbar(viewBinding.toolbarLayout.toolbar, R.string.automation, true)
        initInputs()
        initActions()
    }

    override fun onResume() {
        super.onResume()
        refreshServiceState()
        loadApps()
    }

    override fun onDestroy() {
        executor.shutdownNow()
        super.onDestroy()
    }

    private fun initInputs() {
        viewBinding.installUrl.keyListener = SafeKeyListener.url()
        viewBinding.installPath.keyListener = SafeKeyListener.path()
    }

    private fun initActions() {
        viewBinding.apiSwitch.setOnCheckedChangeListener { _, checked ->
            ApiSettings.setEnabled(this, checked)
            if (checked) {
                ApiService.start(this)
            } else {
                ApiService.stop(this)
            }
            refreshServiceState()
        }

        viewBinding.copyToken.setOnClickListener { copyToken() }
        viewBinding.resetToken.setOnClickListener { resetToken() }
        viewBinding.filesAccess.setOnClickListener { openAllFilesAccess() }
        viewBinding.installFromUrl.setOnClickListener { installFromUrl() }
        viewBinding.installFromPath.setOnClickListener { installFromPath() }
    }

    private fun refreshServiceState() {
        val enabled = ApiSettings.isEnabled(this)
        if (viewBinding.apiSwitch.isChecked != enabled) {
            viewBinding.apiSwitch.isChecked = enabled
        }
        viewBinding.apiAddress.text =
            "http://${NetworkUtils.localAddress()}:${ApiSettings.port(this)}"
        refreshToken()
    }

    private fun refreshToken() {
        viewBinding.apiTokenText.text = "token: ${ApiSettings.token(this)}"
    }

    private fun copyToken() {
        try {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            clipboard.setPrimaryClip(
                ClipData.newPlainText("token", ApiSettings.token(this))
            )
            toast(R.string.api_copied)
        } catch (e: Exception) {
            Log.e(TAG, "copy token failed: ${e.message}")
        }
    }

    private fun resetToken() {
        try {
            ApiSettings.resetToken(this)
            refreshToken()
            toast(R.string.api_token_reset)
        } catch (e: Exception) {
            Log.e(TAG, "reset token failed: ${e.message}")
        }
    }

    private fun openAllFilesAccess() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            return
        }
        try {
            if (Environment.isExternalStorageManager()) {
                toast(R.string.automation_files_granted)
                return
            }
            val intent = Intent(
                Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION,
                Uri.parse("package:$packageName")
            )
            startActivity(intent)
        } catch (e: Exception) {
            try {
                startActivity(Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION))
            } catch (t: Exception) {
                Log.e(TAG, "open files access failed: ${t.message}")
            }
        }
    }

    private fun installFromUrl() {
        val url = viewBinding.installUrl.text.toString().trim()
        if (url.isEmpty()) {
            toast(R.string.automation_install_input_required)
            return
        }
        install { BlackBoxCore.get().installPackageAsUser(Uri.parse(url), 0) }
    }

    private fun installFromPath() {
        val path = viewBinding.installPath.text.toString().trim()
        if (path.isEmpty()) {
            toast(R.string.automation_install_input_required)
            return
        }
        install { BlackBoxCore.get().installPackageAsUser(File(path), 0) }
    }

    private fun install(block: () -> InstallResult) {
        showLoading()
        toast(R.string.automation_installing)
        runAsync {
            val result = try {
                block()
            } catch (e: Throwable) {
                Log.e(TAG, "install failed: ${e.message}")
                null
            }
            runOnUiThread {
                hideLoading()
                if (result?.success == true) {
                    toast(getString(R.string.automation_install_ok, result.packageName.orEmpty()))
                    loadApps()
                } else {
                    toast(getString(R.string.automation_install_failed, result?.msg.orEmpty()))
                }
            }
        }
    }

    private fun loadApps() {
        runAsync {
            val apps = mutableListOf<AppRow>()
            try {
                BlackBoxCore.getBPackageManager()
                    .getInstalledPackagesAsUser(0)
                    .forEach { installed ->
                        val packageName = installed.packageName ?: return@forEach
                        var label = packageName
                        var icon: Drawable? = null
                        try {
                            val info = installed.application
                            if (info != null) {
                                label = BlackBoxCore.getPackageManager()
                                    .getApplicationLabel(info).toString()
                                icon = BlackBoxCore.getPackageManager()
                                    .getApplicationIcon(info)
                            }
                        } catch (e: Exception) {
                            Log.w(TAG, "load app info failed for $packageName: ${e.message}")
                        }
                        apps.add(AppRow(label, packageName, icon, badgesFor(packageName)))
                    }
            } catch (e: Throwable) {
                Log.e(TAG, "load apps failed: ${e.message}")
            }
            apps.sortBy { it.label.lowercase() }
            runOnUiThread { renderApps(apps) }
        }
    }

    /** Marks which overrides are stored for the app, mirroring the config screen. */
    private fun badgesFor(packageName: String): String {
        val badges = mutableListOf<String>()
        try {
            val proxy = AppProxyStore.load(this, packageName)
            if (proxy?.enabled == true && proxy.isValid) {
                badges.add("proxy")
            }
            if (AppGaidStore.load(this, packageName) != null) {
                badges.add("gaid")
            }
            if (AppDeviceStore.load(this, packageName) != null) {
                badges.add("device")
            }
        } catch (e: Exception) {
            Log.w(TAG, "read overrides failed for $packageName: ${e.message}")
        }
        return badges.joinToString(" · ")
    }

    private fun renderApps(apps: List<AppRow>) {
        try {
            viewBinding.appList.removeAllViews()
            viewBinding.emptyApps.visibility = if (apps.isEmpty()) View.VISIBLE else View.GONE
            apps.forEach { app ->
                val item = ItemAutomationAppBinding.inflate(
                    layoutInflater,
                    viewBinding.appList,
                    false
                )
                item.appName.text = app.label
                item.appPackage.text = app.packageName
                item.appIcon.setImageDrawable(app.icon)
                if (app.badges.isEmpty()) {
                    item.appBadges.visibility = View.GONE
                } else {
                    item.appBadges.text = app.badges
                    item.appBadges.visibility = View.VISIBLE
                }
                item.startButton.setOnClickListener { launchApp(app.packageName) }
                item.root.setOnClickListener {
                    AppConfigActivity.start(this, app.packageName, app.label)
                }
                viewBinding.appList.addView(item.root)
            }
        } catch (e: Exception) {
            Log.e(TAG, "render apps failed: ${e.message}")
        }
    }

    private fun launchApp(packageName: String) {
        val started = try {
            BlackBoxCore.get().launchApk(packageName, 0)
        } catch (e: Exception) {
            Log.e(TAG, "launch failed: ${e.message}")
            false
        }
        if (!started) {
            toast(getString(R.string.automation_launch_failed, packageName))
        }
    }

    private fun runAsync(block: () -> Unit) {
        executor.execute {
            try {
                block()
            } catch (t: Throwable) {
                Log.e(TAG, "async task failed: ${t.message}")
            }
        }
    }

}
