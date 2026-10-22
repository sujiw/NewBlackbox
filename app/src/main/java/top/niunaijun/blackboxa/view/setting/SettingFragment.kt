package top.niunaijun.blackboxa.view.setting

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.preference.EditTextPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import top.niunaijun.blackbox.BlackBoxCore
import top.niunaijun.blackboxa.R
import top.niunaijun.blackboxa.api.ApiService
import top.niunaijun.blackboxa.api.ApiSettings
import top.niunaijun.blackboxa.app.AppManager
import top.niunaijun.blackboxa.util.toast
import top.niunaijun.blackboxa.view.cert.CertManagerActivity
import top.niunaijun.blackboxa.view.gms.GmsManagerActivity
import top.niunaijun.blackboxa.view.xp.XpActivity

/**
 *
 * @Description:
 * @Author: wukaicheng
 * @CreateDate: 2021/5/6 22:13
 */
class SettingFragment : PreferenceFragmentCompat() {

    private lateinit var xpEnable: SwitchPreferenceCompat

    private lateinit var xpModule: Preference

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.setting, rootKey)

        xpEnable = findPreference("xp_enable")!!
        xpEnable.isChecked = BlackBoxCore.get().isXPEnable

        xpEnable.setOnPreferenceChangeListener { _, newValue ->
            BlackBoxCore.get().isXPEnable = (newValue == true)
            true
        }
        //xp模块跳转
        xpModule = findPreference("xp_module")!!
        xpModule.setOnPreferenceClickListener {
            val intent = Intent(requireActivity(), XpActivity::class.java)
            requireContext().startActivity(intent)
            true
        }
        initGms()
        initApi()

        invalidHideState{
            val xpHidePreference: Preference = (findPreference("xp_hide")!!)
            val hideXposed = AppManager.mBlackBoxLoader.hideXposed()
            xpHidePreference.setDefaultValue(hideXposed)
            xpHidePreference
        }

        invalidHideState{
            val rootHidePreference: Preference = (findPreference("root_hide")!!)
            val hideRoot = AppManager.mBlackBoxLoader.hideRoot()
            rootHidePreference.setDefaultValue(hideRoot)
            rootHidePreference
        }

        invalidHideState {
            val daemonPreference: Preference = (findPreference("daemon_enable")!!)
            val mDaemonEnable = AppManager.mBlackBoxLoader.daemonEnable()
            daemonPreference.setDefaultValue(mDaemonEnable)
            daemonPreference
        }
    }

    private fun initGms() {
        val gmsManagerPreference: Preference = (findPreference("gms_manager")!!)

        if (BlackBoxCore.get().isSupportGms) {

            gmsManagerPreference.setOnPreferenceClickListener {
                GmsManagerActivity.start(requireContext())
                true
            }
        } else {
            gmsManagerPreference.summary = getString(R.string.no_gms)
            gmsManagerPreference.isEnabled = false
        }
        initCert()
    }

    private fun initApi() {
        val context = requireContext()

        val enablePreference = findPreference<SwitchPreferenceCompat>("api_enable")!!
        enablePreference.isChecked = ApiSettings.isEnabled(context)
        enablePreference.setOnPreferenceChangeListener { _, newValue ->
            val enabled = newValue == true
            ApiSettings.setEnabled(context, enabled)
            if (enabled) {
                ApiService.start(context)
            } else {
                ApiService.stop(context)
            }
            true
        }

        val portPreference = findPreference<EditTextPreference>("api_port")!!
        portPreference.text = ApiSettings.port(context).toString()
        portPreference.summary = ApiSettings.port(context).toString()
        portPreference.setOnPreferenceChangeListener { _, newValue ->
            val port = (newValue as? String)?.trim()?.toIntOrNull()
            if (port == null || port !in 1024..65535) {
                toast(R.string.api_port_invalid)
                return@setOnPreferenceChangeListener false
            }
            ApiSettings.setPort(context, port)
            portPreference.summary = port.toString()
            if (ApiSettings.isEnabled(context)) {
                ApiService.stop(context)
                ApiService.start(context)
            }
            true
        }

        val tokenPreference = findPreference<Preference>("api_token")!!
        tokenPreference.setOnPreferenceClickListener {
            showTokenDialog()
            true
        }

        val filesPreference = findPreference<Preference>("api_files_access")!!
        filesPreference.setOnPreferenceClickListener {
            openAllFilesAccess()
            true
        }
    }

    private fun showTokenDialog() {
        val context = requireContext()
        AlertDialog.Builder(context)
            .setTitle(R.string.api_token)
            .setMessage(ApiSettings.token(context))
            .setPositiveButton(R.string.api_copy) { _, _ ->
                val clipboard =
                    context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                clipboard.setPrimaryClip(
                    ClipData.newPlainText("token", ApiSettings.token(context))
                )
                toast(R.string.api_copied)
            }
            .setNeutralButton(R.string.api_reset) { _, _ ->
                ApiSettings.resetToken(context)
                toast(R.string.api_token_reset)
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }

    private fun openAllFilesAccess() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            return
        }
        val context = requireContext()
        val intent = Intent(
            Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION,
            Uri.parse("package:${context.packageName}")
        )
        try {
            startActivity(intent)
        } catch (e: Exception) {
            startActivity(Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION))
        }
    }

    private fun initCert() {
        val certManagerPreference: Preference = (findPreference("cert_manager")!!)
        certManagerPreference.setOnPreferenceClickListener {
            CertManagerActivity.start(requireContext())
            true
        }
    }

    private fun invalidHideState(block: () -> Preference) {
        val pref = block()
        pref.setOnPreferenceChangeListener { preference, newValue ->
            val tmpHide = (newValue == true)
            when (preference.key) {
                "xp_hide" -> {
                    AppManager.mBlackBoxLoader.invalidHideXposed(tmpHide)
                }

                "root_hide" -> {

                    AppManager.mBlackBoxLoader.invalidHideRoot(tmpHide)
                }

                "daemon_enable" -> {
                    AppManager.mBlackBoxLoader.invalidDaemonEnable(tmpHide)
                }
            }

            toast(R.string.restart_module)
            return@setOnPreferenceChangeListener true
        }
    }
}