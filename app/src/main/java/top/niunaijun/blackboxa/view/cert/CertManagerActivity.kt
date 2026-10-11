package top.niunaijun.blackboxa.view.cert

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
import top.niunaijun.blackboxa.R
import top.niunaijun.blackboxa.databinding.ActivityCertManagerBinding
import top.niunaijun.blackboxa.util.CertManager
import top.niunaijun.blackboxa.util.CertPasswordException
import top.niunaijun.blackboxa.util.inflate
import top.niunaijun.blackboxa.util.toast
import top.niunaijun.blackboxa.view.base.LoadingActivity
import java.security.cert.X509Certificate

/**
 * 抓包根证书管理。
 *
 *  - 用户证书（KeyChain，无需 Root）：对信任用户证书的 App / 系统浏览器生效
 *  - 系统根证书（Root）：所有 App 生效
 *  - 虚拟 App 抓包模式：改容器内 targetSdk，APK 零改动，无签名问题
 */
class CertManagerActivity : LoadingActivity() {

    private val viewBinding: ActivityCertManagerBinding by inflate()

    private var currentCerts: List<X509Certificate> = emptyList()

    private var rootChecked = false

    private val pickLauncher =
        registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
            if (uri != null) loadCert(uri, null)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        initToolbar(viewBinding.toolbarLayout.toolbar, R.string.cert_manager, true)

        viewBinding.btnImport.setOnClickListener {
            pickLauncher.launch(
                arrayOf(
                    "application/x-pem-file",
                    "application/x-x509-ca-cert",
                    "application/x-pkcs12",
                    "application/octet-stream",
                    "*/*"
                )
            )
        }
        viewBinding.btnUserCert.setOnClickListener { installUserCert() }
        viewBinding.btnSystemCert.setOnClickListener { confirmInstallSystem() }
        viewBinding.btnRemoveSystem.setOnClickListener { confirmRemoveSystem() }
        viewBinding.btnVirtualCapture.setOnClickListener { VirtualCaptureActivity.start(this) }
        viewBinding.btnProxy.setOnClickListener { openProxySettings() }

        viewBinding.tvRootStatus.text = getString(R.string.cert_root_unknown)
    }

    // ---------------- import ----------------

    private fun loadCert(uri: Uri, password: String?) {
        try {
            currentCerts = CertManager.parseCertificate(uri, password)
            viewBinding.tvCertInfo.text =
                currentCerts.joinToString("\n\n") { CertManager.certInfo(it) }
            viewBinding.btnUserCert.isEnabled = true
            viewBinding.btnSystemCert.isEnabled = true
            viewBinding.btnRemoveSystem.isEnabled = true
            toast(R.string.cert_import_ok)
        } catch (e: CertPasswordException) {
            promptPassword { pwd -> loadCert(uri, pwd) }
        } catch (e: Exception) {
            toast(getString(R.string.cert_import_failed, e.message ?: ""))
        }
    }

    private fun promptPassword(callback: (String) -> Unit) {
        MaterialDialog(this).show {
            title(R.string.cert_password_title)
            input(hintRes = R.string.cert_password_hint, allowEmpty = true) { _, text ->
                callback(text.toString())
            }
            positiveButton(R.string.done)
            negativeButton(R.string.cancel)
        }
    }

    // ---------------- install paths ----------------

    private fun installUserCert() {
        if (currentCerts.isEmpty()) {
            toast(R.string.cert_none_imported)
            return
        }
        try {
            val alias = currentCerts.first().subjectDN.name
                .split(",").firstOrNull()?.removePrefix("CN=") ?: "capture-ca"
            val intent = CertManager.userCertInstallIntent(currentCerts, alias)
            startActivity(intent)
        } catch (e: Exception) {
            toast(getString(R.string.cert_install_failed, e.message ?: ""))
        }
    }

    private fun confirmInstallSystem() {
        if (currentCerts.isEmpty()) {
            toast(R.string.cert_none_imported)
            return
        }
        MaterialDialog(this).show {
            title(R.string.cert_install_system)
            message(R.string.cert_install_system_hint)
            positiveButton(R.string.done) { installSystem() }
            negativeButton(R.string.cancel) {}
        }
    }

    private fun installSystem() {
        if (currentCerts.isEmpty()) {
            toast(R.string.cert_none_imported)
            return
        }
        showLoading()
        Thread {
            val root = CertManager.hasRoot()
            runOnUiThread {
                rootChecked = true
                viewBinding.tvRootStatus.text = getString(
                    if (root) R.string.cert_root_available else R.string.cert_root_unavailable
                )
            }
            if (!root) {
                runOnUiThread {
                    hideLoading()
                    toast(R.string.cert_root_unavailable)
                }
                return@Thread
            }
            val result = CertManager.installSystem(currentCerts)
            runOnUiThread {
                hideLoading()
                showTargetResult(R.string.cert_install_system, result)
            }
        }.start()
    }

    private fun confirmRemoveSystem() {
        if (currentCerts.isEmpty()) {
            toast(R.string.cert_none_imported)
            return
        }
        MaterialDialog(this).show {
            title(R.string.cert_remove_system)
            message(R.string.cert_remove_system_hint)
            positiveButton(R.string.done) { removeSystem() }
            negativeButton(R.string.cancel) {}
        }
    }

    private fun removeSystem() {
        showLoading()
        Thread {
            val root = CertManager.hasRoot()
            runOnUiThread { rootChecked = true }
            if (!root) {
                runOnUiThread {
                    hideLoading()
                    toast(R.string.cert_root_unavailable)
                }
                return@Thread
            }
            val result = CertManager.removeSystem(currentCerts)
            runOnUiThread {
                hideLoading()
                showTargetResult(R.string.cert_remove_system, result)
            }
        }.start()
    }

    private fun showTargetResult(titleRes: Int, result: Map<String, Boolean>) {
        val sb = StringBuilder()
        result.forEach { (path, ok) ->
            sb.append(path).append(": ")
                .append(getString(if (ok) R.string.cert_result_ok else R.string.cert_result_failed))
                .append("\n")
        }
        MaterialDialog(this).show {
            title(titleRes)
            message(text = sb.toString().trim())
            positiveButton(R.string.done)
        }
    }

    // ---------------- proxy ----------------

    private fun openProxySettings() {
        val actions = listOf(
            "android.settings.WIFI_IP_SETTINGS",
            android.provider.Settings.ACTION_WIFI_SETTINGS,
            android.provider.Settings.ACTION_SETTINGS
        )
        for (action in actions) {
            try {
                startActivity(Intent(action).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
                return
            } catch (e: ActivityNotFoundException) {
                continue
            }
        }
        toast(R.string.cert_proxy_failed)
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, CertManagerActivity::class.java))
        }
    }
}
