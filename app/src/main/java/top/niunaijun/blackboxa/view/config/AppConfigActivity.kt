package top.niunaijun.blackboxa.view.config

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import top.niunaijun.blackbox.BlackBoxCore
import top.niunaijun.blackboxa.R
import top.niunaijun.blackboxa.bean.AppDeviceConfig
import top.niunaijun.blackboxa.bean.AppDeviceStore
import top.niunaijun.blackboxa.bean.AppGaidConfig
import top.niunaijun.blackboxa.bean.AppGaidStore
import top.niunaijun.blackboxa.bean.AppProxyConfig
import top.niunaijun.blackboxa.bean.AppProxyStore
import top.niunaijun.blackboxa.databinding.ActivityAppConfigBinding
import top.niunaijun.blackboxa.util.SafeKeyListener
import top.niunaijun.blackboxa.util.SafePasswordTransformation
import top.niunaijun.blackboxa.util.inflate
import top.niunaijun.blackboxa.util.toast
import top.niunaijun.blackboxa.view.base.BaseActivity

/**
 * Editor for the per-app overrides (proxy / advertising id / device fingerprint).
 *
 * The values are persisted through [AppProxyStore], [AppGaidStore] and
 * [AppDeviceStore]; the guest process picks them up from the host files dir
 * when it binds, so changes only apply after the app is restarted.
 */
class AppConfigActivity : BaseActivity() {

    companion object {
        private const val TAG = "AppConfigActivity"

        private const val EXTRA_PACKAGE = "packageName"
        private const val EXTRA_NAME = "appName"

        private val UUID_REGEX = Regex(
            "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$"
        )

        fun start(context: Context, packageName: String, appName: String) {
            val intent = Intent(context, AppConfigActivity::class.java)
            intent.putExtra(EXTRA_PACKAGE, packageName)
            intent.putExtra(EXTRA_NAME, appName)
            context.startActivity(intent)
        }
    }

    private val viewBinding: ActivityAppConfigBinding by inflate()

    private lateinit var packageName: String

    private var appName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        packageName = intent.getStringExtra(EXTRA_PACKAGE).orEmpty()
        appName = intent.getStringExtra(EXTRA_NAME).orEmpty()

        if (packageName.isEmpty()) {
            finish()
            return
        }

        initToolbar(viewBinding.toolbarLayout.toolbar, R.string.config_title, true)
        initInputs()
        initActions()
        bindConfig()
    }

    private fun initInputs() {
        viewBinding.proxyHost.keyListener = SafeKeyListener.host()
        viewBinding.proxyPort.keyListener = SafeKeyListener.number()
        viewBinding.proxyUsername.inputType = InputType.TYPE_CLASS_TEXT
        viewBinding.proxyUsername.keyListener = SafeKeyListener.ascii()
        viewBinding.proxyPassword.inputType =
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        viewBinding.proxyPassword.transformationMethod = SafePasswordTransformation()
        viewBinding.proxyPassword.keyListener = SafeKeyListener.ascii()
        viewBinding.proxyBypass.keyListener = SafeKeyListener.bypass()

        viewBinding.gaidValue.keyListener = SafeKeyListener.hex()

        listOf(
            viewBinding.deviceBrand,
            viewBinding.deviceModel,
            viewBinding.deviceManufacturer,
            viewBinding.deviceDevice,
            viewBinding.deviceProduct
        ).forEach {
            it.inputType = InputType.TYPE_CLASS_TEXT
            it.keyListener = SafeKeyListener.ascii()
        }
        listOf(viewBinding.deviceWidth, viewBinding.deviceHeight, viewBinding.deviceDpi).forEach {
            it.keyListener = SafeKeyListener.number()
        }
    }

    private fun initActions() {
        viewBinding.proxyEnabled.setOnCheckedChangeListener { _, checked ->
            if (checked && viewBinding.proxyTypeGroup.checkedRadioButtonId == R.id.proxyTypeNone) {
                viewBinding.proxyTypeGroup.check(R.id.proxyTypeHttp)
            }
            updateProxyState()
        }

        viewBinding.proxyTypeGroup.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.proxyTypeNone) {
                viewBinding.proxyEnabled.isChecked = false
            } else if (!viewBinding.proxyEnabled.isChecked) {
                viewBinding.proxyEnabled.isChecked = true
            }
            updateProxyState()
        }

        viewBinding.saveButton.setOnClickListener { save() }
        viewBinding.clearButton.setOnClickListener { clearAll() }
        viewBinding.launchButton.setOnClickListener { launchApp() }
    }

    private fun updateProxyState() {
        val enabled = viewBinding.proxyEnabled.isChecked
        viewBinding.proxyTypeGroup.isEnabled = enabled
        for (i in 0 until viewBinding.proxyTypeGroup.childCount) {
            viewBinding.proxyTypeGroup.getChildAt(i).isEnabled = enabled
        }
        viewBinding.proxyHost.isEnabled = enabled
        viewBinding.proxyPort.isEnabled = enabled
        viewBinding.proxyUsername.isEnabled = enabled
        viewBinding.proxyPassword.isEnabled = enabled
        viewBinding.proxyBypass.isEnabled = enabled
    }

    private fun bindConfig() {
        viewBinding.appName.text = appName.ifEmpty { packageName }
        viewBinding.appPackage.text = packageName

        val proxy = AppProxyStore.load(this, packageName)
        viewBinding.proxyEnabled.isChecked = proxy?.enabled == true
        viewBinding.proxyHost.setText(proxy?.host.orEmpty())
        viewBinding.proxyPort.setText(if ((proxy?.port ?: 0) > 0) proxy?.port.toString() else "")
        viewBinding.proxyUsername.setText(proxy?.username.orEmpty())
        viewBinding.proxyPassword.setText(proxy?.password.orEmpty())
        viewBinding.proxyBypass.setText(proxy?.bypass ?: "localhost,127.0.0.1")
        viewBinding.proxyTypeGroup.check(
            when {
                proxy == null || !proxy.enabled -> R.id.proxyTypeNone
                proxy.type == AppProxyConfig.TYPE_SOCKS5 -> R.id.proxyTypeSocks5
                else -> R.id.proxyTypeHttp
            }
        )
        if (proxy?.enabled == true) {
            viewBinding.proxyEnabled.isChecked = true
        }
        updateProxyState()

        val gaid = AppGaidStore.load(this, packageName)
        viewBinding.gaidValue.setText(gaid?.gaid.orEmpty())
        viewBinding.gaidLimit.isChecked = gaid?.limitAdTracking ?: false

        val device = AppDeviceStore.load(this, packageName)
        viewBinding.deviceBrand.setText(device?.brand.orEmpty())
        viewBinding.deviceModel.setText(device?.model.orEmpty())
        viewBinding.deviceManufacturer.setText(device?.manufacturer.orEmpty())
        viewBinding.deviceDevice.setText(device?.device.orEmpty())
        viewBinding.deviceProduct.setText(device?.product.orEmpty())
        viewBinding.deviceWidth.setText(if ((device?.width ?: 0) > 0) device?.width.toString() else "")
        viewBinding.deviceHeight.setText(if ((device?.height ?: 0) > 0) device?.height.toString() else "")
        viewBinding.deviceDpi.setText(if ((device?.dpi ?: 0) > 0) device?.dpi.toString() else "")
    }

    private fun save() {
        try {
            saveProxy()
            saveGaid()
            saveDevice()
            toast(R.string.config_saved)
        } catch (e: Exception) {
            Log.e(TAG, "save config failed: ${e.message}")
        }
    }

    private fun saveProxy() {
        val checkedId = viewBinding.proxyTypeGroup.checkedRadioButtonId
        val enabled = viewBinding.proxyEnabled.isChecked && checkedId != R.id.proxyTypeNone
        if (!enabled) {
            AppProxyStore.remove(this, packageName)
            return
        }

        val host = viewBinding.proxyHost.text.toString().trim()
        val port = viewBinding.proxyPort.text.toString().trim().toIntOrNull() ?: 0
        if (host.isEmpty() || port !in 1..65535) {
            toast(R.string.proxy_invalid)
            return
        }

        AppProxyStore.save(
            this,
            packageName,
            AppProxyConfig(
                enabled = true,
                type = if (checkedId == R.id.proxyTypeSocks5) {
                    AppProxyConfig.TYPE_SOCKS5
                } else {
                    AppProxyConfig.TYPE_HTTP
                },
                host = host,
                port = port,
                username = viewBinding.proxyUsername.text.toString().trim(),
                password = viewBinding.proxyPassword.text.toString(),
                bypass = viewBinding.proxyBypass.text.toString().trim()
                    .ifEmpty { "localhost,127.0.0.1" }
            )
        )
    }

    private fun saveGaid() {
        val gaid = viewBinding.gaidValue.text.toString().trim()
        if (gaid.isEmpty()) {
            AppGaidStore.remove(this, packageName)
            return
        }
        if (!UUID_REGEX.matches(gaid)) {
            toast(R.string.gaid_invalid)
            return
        }
        AppGaidStore.save(
            this,
            packageName,
            AppGaidConfig(
                gaid = gaid,
                limitAdTracking = viewBinding.gaidLimit.isChecked
            )
        )
    }

    private fun saveDevice() {
        val config = AppDeviceConfig(
            brand = viewBinding.deviceBrand.text.toString().trim(),
            model = viewBinding.deviceModel.text.toString().trim(),
            manufacturer = viewBinding.deviceManufacturer.text.toString().trim(),
            device = viewBinding.deviceDevice.text.toString().trim(),
            product = viewBinding.deviceProduct.text.toString().trim(),
            width = viewBinding.deviceWidth.text.toString().trim().toIntOrNull() ?: 0,
            height = viewBinding.deviceHeight.text.toString().trim().toIntOrNull() ?: 0,
            dpi = viewBinding.deviceDpi.text.toString().trim().toIntOrNull() ?: 0,
            // 保留 API 写入的完整档案（androidId / imei1 / mcc …），只改这里编辑的几项
            raw = AppDeviceStore.load(this, packageName)?.raw.orEmpty()
        )
        if (config.isEmpty) {
            AppDeviceStore.remove(this, packageName)
        } else {
            AppDeviceStore.save(this, packageName, config)
        }
    }

    private fun clearAll() {
        try {
            AppProxyStore.remove(this, packageName)
            AppGaidStore.remove(this, packageName)
            AppDeviceStore.remove(this, packageName)
            bindConfig()
            toast(R.string.config_cleared)
        } catch (e: Exception) {
            Log.e(TAG, "clear config failed: ${e.message}")
        }
    }

    private fun launchApp() {
        val started = try {
            BlackBoxCore.get().launchApk(packageName, 0)
        } catch (e: Exception) {
            Log.e(TAG, "launch failed: ${e.message}")
            false
        }
        if (!started) {
            toast(R.string.start_fail)
        }
    }
}
