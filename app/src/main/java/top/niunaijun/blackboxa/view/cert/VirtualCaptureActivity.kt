package top.niunaijun.blackboxa.view.cert

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.ListView
import android.widget.TextView
import com.afollestad.materialdialogs.MaterialDialog
import top.niunaijun.blackboxa.R
import top.niunaijun.blackboxa.databinding.ActivityVirtualCaptureBinding
import top.niunaijun.blackboxa.util.VirtualCapturePatcher
import top.niunaijun.blackboxa.util.inflate
import top.niunaijun.blackboxa.util.toast
import top.niunaijun.blackboxa.view.base.LoadingActivity

/**
 * 虚拟 App 抓包模式：不动 APK，把容器内该 App 可见的 targetSdk 降到 23，
 * 框架默认网络安全配置即信任用户证书（KeyChain 安装的代理 CA），无签名变更。
 */
class VirtualCaptureActivity : LoadingActivity() {

    private val viewBinding: ActivityVirtualCaptureBinding by inflate()

    private var items: List<VirtualCapturePatcher.AppCapture> = emptyList()

    private lateinit var adapter: CaptureAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        initToolbar(viewBinding.toolbarLayout.toolbar, R.string.virtual_capture_mode, true)

        adapter = CaptureAdapter(this)
        viewBinding.listView.adapter = adapter
        viewBinding.listView.setOnItemClickListener { _, _, position, _ ->
            toggle(position)
        }
        refresh()
    }

    private fun refresh() {
        showLoading()
        Thread {
            items = VirtualCapturePatcher.listVirtualApps()
            runOnUiThread {
                hideLoading()
                adapter.clear()
                adapter.addAll(items)
                adapter.notifyDataSetChanged()
                if (items.isEmpty()) {
                    toast(R.string.capture_no_virtual_app)
                }
            }
        }.start()
    }

    private fun toggle(position: Int) {
        val item = items.getOrNull(position) ?: return
        val enable = !item.captureEnabled
        if (enable) {
            if (VirtualCapturePatcher.isVirtualAppRunning(item.packageName)) {
                MaterialDialog(this).show {
                    title(R.string.capture_app_running_title)
                    message(R.string.capture_app_running_hint)
                    positiveButton(R.string.done)
                }
                return
            }
            MaterialDialog(this).show {
                title(R.string.capture_enable_title)
                message(text = getString(R.string.capture_enable_hint, item.label))
                positiveButton(R.string.done) { applyCapture(item, true) }
                negativeButton(R.string.cancel) {}
            }
        } else {
            applyCapture(item, false)
        }
    }

    private fun applyCapture(item: VirtualCapturePatcher.AppCapture, enable: Boolean) {
        showLoading()
        Thread {
            val message = try {
                val (old, new) = VirtualCapturePatcher.setCaptureMode(item.packageName, enable)
                getString(
                    if (enable) R.string.capture_enabled else R.string.capture_disabled,
                    item.label,
                    old,
                    new
                )
            } catch (e: Exception) {
                getString(R.string.capture_failed, e.message ?: "")
            }
            runOnUiThread {
                hideLoading()
                MaterialDialog(this).show {
                    title(R.string.virtual_capture_mode)
                    message(text = message)
                    positiveButton(R.string.done)
                }
                refresh()
            }
        }.start()
    }

    private inner class CaptureAdapter(context: Context) :
        ArrayAdapter<VirtualCapturePatcher.AppCapture>(context, R.layout.item_capture_app) {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = convertView ?: layoutInflater.inflate(
                R.layout.item_capture_app, parent, false
            )
            val item = getItem(position) ?: return view
            view.findViewById<TextView>(R.id.tvName).text = item.label
            view.findViewById<CheckBox>(R.id.checkbox).isChecked = item.captureEnabled
            view.findViewById<TextView>(R.id.tvSdk).text =
                getString(R.string.capture_sdk_value, item.targetSdk)
            return view
        }
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, VirtualCaptureActivity::class.java))
        }
    }
}
