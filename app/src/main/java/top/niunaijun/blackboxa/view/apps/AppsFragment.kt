package top.niunaijun.blackboxa.view.apps

import android.graphics.Point
import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import android.text.method.DigitsKeyListener
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import cbfg.rvadapter.RVAdapter
import com.afollestad.materialdialogs.MaterialDialog
import top.niunaijun.blackbox.BlackBoxCore
import top.niunaijun.blackboxa.R
import top.niunaijun.blackboxa.bean.AppGaidConfig
import top.niunaijun.blackboxa.bean.AppGaidStore
import top.niunaijun.blackboxa.bean.AppInfo
import top.niunaijun.blackboxa.bean.AppProxyConfig
import top.niunaijun.blackboxa.bean.AppProxyStore
import top.niunaijun.blackboxa.databinding.DialogAppGaidBinding
import top.niunaijun.blackboxa.databinding.DialogAppProxyBinding
import top.niunaijun.blackboxa.databinding.FragmentAppsBinding
import top.niunaijun.blackboxa.util.InjectionUtil
import top.niunaijun.blackboxa.util.SafeKeyListener
import top.niunaijun.blackboxa.util.SafePasswordTransformation
import top.niunaijun.blackboxa.util.ShortcutUtil
import top.niunaijun.blackboxa.util.inflate
import top.niunaijun.blackboxa.util.toast
import top.niunaijun.blackboxa.view.base.LoadingActivity
import top.niunaijun.blackboxa.view.config.AppConfigActivity
import top.niunaijun.blackboxa.view.main.MainActivity
import java.util.*
import kotlin.math.abs


/**
 *
 * @Description:
 * @Author: wukaicheng
 * @CreateDate: 2021/4/29 22:21
 */
class AppsFragment : Fragment() {

    private lateinit var viewModel: AppsViewModel

    private lateinit var mAdapter: RVAdapter<AppInfo>

    private val viewBinding: FragmentAppsBinding by inflate()

    private var popupMenu: PopupMenu? = null

    companion object {
        private const val TAG = "AppsFragment"

        private val UUID_REGEX = Regex(
            "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$"
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            viewModel =
                ViewModelProvider(this, InjectionUtil.getAppsFactory()).get(AppsViewModel::class.java)
        } catch (e: Exception) {
            Log.e(TAG, "Error in onCreate: ${e.message}")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        try {
            viewBinding.stateView.showEmpty()

            mAdapter =
                RVAdapter<AppInfo>(requireContext(), AppsAdapter()).bind(viewBinding.recyclerView)

            viewBinding.recyclerView.adapter = mAdapter
            viewBinding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

            val touchCallBack = AppsTouchCallBack { from, to ->
                try {
                    onItemMove(from, to)
                    viewModel.updateSortLiveData.postValue(true)
                } catch (e: Exception) {
                    Log.e(TAG, "Error in touch callback: ${e.message}")
                }
            }

            val itemTouchHelper = ItemTouchHelper(touchCallBack)
            itemTouchHelper.attachToRecyclerView(viewBinding.recyclerView)

            mAdapter.setItemClickListener { _, data, _ ->
                try {
                    showLoading()
                    viewModel.launchApk(data.packageName)
                } catch (e: Exception) {
                    Log.e(TAG, "Error launching app: ${e.message}")
                    hideLoading()
                }
            }

            interceptTouch()
            setOnLongClick()
            return viewBinding.root
        } catch (e: Exception) {
            Log.e(TAG, "Error in onCreateView: ${e.message}")
            // Return a simple view to prevent crash
            return View(requireContext())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {
            super.onViewCreated(view, savedInstanceState)
            initData()
        } catch (e: Exception) {
            Log.e(TAG, "Error in onViewCreated: ${e.message}")
        }
    }

    override fun onStart() {
        try {
            super.onStart()
            viewModel.getInstalledApps()
        } catch (e: Exception) {
            Log.e(TAG, "Error in onStart: ${e.message}")
        }
    }

    /**
     * 拖拽优化
     */
    private fun interceptTouch() {
        try {
            val point = Point()
            viewBinding.recyclerView.setOnTouchListener { v, e ->
                try {
                    when (e.action) {
                        MotionEvent.ACTION_UP -> {
                            if (!isMove(point, e)) {
                                popupMenu?.show()
                            }
                            popupMenu = null
                            point.set(0, 0)
                        }

                        MotionEvent.ACTION_MOVE -> {
                            if (point.x == 0 && point.y == 0) {
                                point.x = e.rawX.toInt()
                                point.y = e.rawY.toInt()
                            }
                            isDownAndUp(point, e)

                            if (isMove(point, e)) {
                                popupMenu?.dismiss()
                            }
                        }
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Error in touch listener: ${e.message}")
                }
                return@setOnTouchListener false
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error in interceptTouch: ${e.message}")
        }
    }

    private fun isMove(point: Point, e: MotionEvent): Boolean {
        return try {
            val max = 40

            val x = point.x
            val y = point.y

            val xU = abs(x - e.rawX)
            val yU = abs(y - e.rawY)
            xU > max || yU > max
        } catch (e: Exception) {
            Log.e(TAG, "Error in isMove: ${e.message}")
            false
        }
    }

    private fun isDownAndUp(point: Point, e: MotionEvent) {
        try {
            val min = 10
            val y = point.y
            val yU = y - e.rawY

            if (abs(yU) > min) {
                (requireActivity() as? MainActivity)?.showFloatButton(yU < 0)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error in isDownAndUp: ${e.message}")
        }
    }

    private fun onItemMove(fromPosition:Int, toPosition:Int){
        try {
            if (fromPosition < toPosition) {
                for (i in fromPosition until toPosition) {
                    Collections.swap(mAdapter.getItems(), i, i + 1)
                }
            } else {
                for (i in fromPosition downTo toPosition + 1) {
                    Collections.swap(mAdapter.getItems(), i, i - 1)
                }
            }
            mAdapter.notifyItemMoved(fromPosition, toPosition)
        } catch (e: Exception) {
            Log.e(TAG, "Error in onItemMove: ${e.message}")
        }
    }

    private fun setOnLongClick() {
        try {
            mAdapter.setItemLongClickListener { view, data, _ ->
                try {
                    popupMenu = PopupMenu(requireContext(),view).also {
                        it.inflate(R.menu.app_menu)
                        it.setOnMenuItemClickListener { item ->
                            try {
                                when (item.itemId) {
                                    R.id.app_remove -> {
                                        if (data.isXpModule) {
                                            toast(R.string.uninstall_module_toast)
                                        } else {
                                            unInstallApk(data)
                                        }
                                    }

                                    R.id.app_clear -> {
                                        clearApk(data)
                                    }

                                    R.id.app_stop -> {
                                        stopApk(data)
                                    }

                                    R.id.app_shortcut -> {
                                        ShortcutUtil.createShortcut(requireContext(), data)
                                    }

                                    R.id.app_proxy -> {
                                        showProxyDialog(data)
                                    }

                                    R.id.app_gaid -> {
                                        showGaidDialog(data)
                                    }

                                    R.id.app_config -> {
                                        AppConfigActivity.start(
                                            requireContext(),
                                            data.packageName,
                                            data.name
                                        )
                                    }
                                }
                                return@setOnMenuItemClickListener true
                            } catch (e: Exception) {
                                Log.e(TAG, "Error in menu item click: ${e.message}")
                                return@setOnMenuItemClickListener false
                            }
                        }
                        it.show()
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Error in long click: ${e.message}")
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error in setOnLongClick: ${e.message}")
        }
    }
    
    private fun showProxyDialog(data: AppInfo) {
        try {
            val binding = DialogAppProxyBinding.inflate(layoutInflater)
            val context = requireContext()
            val config = AppProxyStore.load(context, data.packageName)

            binding.proxyAppName.text = data.name
            binding.proxyHost.setText(config?.host.orEmpty())
            binding.proxyPort.setText(if ((config?.port ?: 0) > 0) config?.port.toString() else "")
            binding.proxyUsername.setText(config?.username.orEmpty())
            binding.proxyPassword.setText(config?.password.orEmpty())
            binding.proxyBypass.setText(config?.bypass ?: "localhost,127.0.0.1")

            // Plain EditTexts use TextKeyListener, which reads Settings.System on
            // the first key press and throws SecurityException inside the virtual
            // space (calling uid mismatch), killing the host process.
            binding.proxyHost.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_URI
            binding.proxyHost.keyListener = SafeKeyListener.host()
            binding.proxyPort.keyListener = DigitsKeyListener.getInstance("0123456789")
            binding.proxyUsername.inputType = InputType.TYPE_CLASS_TEXT
            binding.proxyUsername.keyListener = SafeKeyListener.ascii()
            binding.proxyPassword.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            binding.proxyPassword.transformationMethod = SafePasswordTransformation()
            binding.proxyPassword.keyListener = SafeKeyListener.ascii()
            binding.proxyBypass.inputType = InputType.TYPE_CLASS_TEXT
            binding.proxyBypass.keyListener = SafeKeyListener.bypass()

            binding.proxyTypeGroup.check(
                when {
                    config == null || !config.enabled -> R.id.proxyTypeNone
                    config.type == AppProxyConfig.TYPE_SOCKS5 -> R.id.proxyTypeSocks5
                    else -> R.id.proxyTypeHttp
                }
            )

            val dialog = AlertDialog.Builder(context)
                .setTitle(R.string.app_proxy)
                .setView(binding.root)
                .setPositiveButton(android.R.string.ok, null)
                .setNegativeButton(android.R.string.cancel, null)
                .create()

            dialog.setOnShowListener {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                    val checkedId = binding.proxyTypeGroup.checkedRadioButtonId
                    if (checkedId == R.id.proxyTypeNone) {
                        AppProxyStore.remove(context, data.packageName)
                        toast(R.string.proxy_removed)
                        dialog.dismiss()
                        return@setOnClickListener
                    }

                    val host = binding.proxyHost.text.toString().trim()
                    val port = binding.proxyPort.text.toString().trim().toIntOrNull() ?: 0
                    if (host.isEmpty() || port !in 1..65535) {
                        toast(R.string.proxy_invalid)
                        return@setOnClickListener
                    }

                    AppProxyStore.save(
                        context,
                        data.packageName,
                        AppProxyConfig(
                            enabled = true,
                            type = if (checkedId == R.id.proxyTypeSocks5) {
                                AppProxyConfig.TYPE_SOCKS5
                            } else {
                                AppProxyConfig.TYPE_HTTP
                            },
                            host = host,
                            port = port,
                            username = binding.proxyUsername.text.toString().trim(),
                            password = binding.proxyPassword.text.toString(),
                            bypass = binding.proxyBypass.text.toString().trim()
                                .ifEmpty { "localhost,127.0.0.1" }
                        )
                    )
                    toast(R.string.proxy_saved)
                    dialog.dismiss()
                }
            }
            dialog.show()
        } catch (e: Exception) {
            Log.e(TAG, "Error showing proxy dialog: ${e.message}")
            toast(R.string.proxy_invalid)
        }
    }

    private fun showGaidDialog(data: AppInfo) {
        try {
            val binding = DialogAppGaidBinding.inflate(layoutInflater)
            val context = requireContext()
            val config = AppGaidStore.load(context, data.packageName)

            binding.gaidAppName.text = data.name
            binding.gaidValue.setText(config?.gaid.orEmpty())
            // Qwerty/TextKeyListener reads Settings.System on the first key press,
            // which throws SecurityException inside the virtual space (calling uid
            // mismatch) and kills the host process. A digits key listener never
            // touches Settings.
            binding.gaidValue.inputType = InputType.TYPE_CLASS_TEXT or
                    InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD or
                    InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
            binding.gaidValue.keyListener =
                DigitsKeyListener.getInstance("0123456789abcdefABCDEF-")
            binding.gaidLimit.isChecked = config?.limitAdTracking ?: false

            val dialog = AlertDialog.Builder(context)
                .setTitle(R.string.app_gaid)
                .setView(binding.root)
                .setPositiveButton(android.R.string.ok, null)
                .setNegativeButton(android.R.string.cancel, null)
                .create()

            dialog.setOnShowListener {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                    val gaid = binding.gaidValue.text.toString().trim()
                    if (gaid.isEmpty()) {
                        AppGaidStore.remove(context, data.packageName)
                        toast(R.string.gaid_removed)
                        dialog.dismiss()
                        return@setOnClickListener
                    }
                    if (!UUID_REGEX.matches(gaid)) {
                        toast(R.string.gaid_invalid)
                        return@setOnClickListener
                    }

                    AppGaidStore.save(
                        context,
                        data.packageName,
                        AppGaidConfig(
                            gaid = gaid,
                            limitAdTracking = binding.gaidLimit.isChecked
                        )
                    )
                    toast(R.string.gaid_saved)
                    dialog.dismiss()
                }
            }
            dialog.show()
        } catch (e: Exception) {
            Log.e(TAG, "Error showing gaid dialog: ${e.message}")
            toast(R.string.gaid_invalid)
        }
    }

    private fun initData() {
        try {
            viewBinding.stateView.showLoading()
            viewModel.getInstalledApps()
            viewModel.appsLiveData.observe(viewLifecycleOwner) {
                try {
                    if (it != null) {
                        mAdapter.setItems(it)
                        if (it.isEmpty()) {
                            viewBinding.stateView.showEmpty()
                        } else {
                            viewBinding.stateView.showContent()
                        }
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Error observing apps data: ${e.message}")
                }
            }

            viewModel.resultLiveData.observe(viewLifecycleOwner) {
                try {
                    if (!TextUtils.isEmpty(it)) {
                        hideLoading()
                        requireContext().toast(it)
                        viewModel.getInstalledApps()
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Error observing result data: ${e.message}")
                }
            }

            viewModel.launchLiveData.observe(viewLifecycleOwner) {
                try {
                    it?.run {
                        hideLoading()
                        if (!it) {
                            toast(R.string.start_fail)
                        }
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Error observing launch data: ${e.message}")
                }
            }

            viewModel.updateSortLiveData.observe(viewLifecycleOwner) {
                try {
                    if (this::mAdapter.isInitialized) {
                        viewModel.updateApkOrder(mAdapter.getItems())
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Error observing sort data: ${e.message}")
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error in initData: ${e.message}")
        }
    }

    override fun onStop() {
        try {
            super.onStop()
            viewModel.resultLiveData.value = null
            viewModel.launchLiveData.value = null
        } catch (e: Exception) {
            Log.e(TAG, "Error in onStop: ${e.message}")
        }
    }

    private fun unInstallApk(info: AppInfo) {
        try {
            MaterialDialog(requireContext()).show {
                title(R.string.uninstall_app)
                message(text = getString(R.string.uninstall_app_hint, info.name))
                positiveButton(R.string.done) {
                    try {
                        showLoading()
                        viewModel.unInstall(info.packageName)
                    } catch (e: Exception) {
                        Log.e(TAG, "Error uninstalling app: ${e.message}")
                        hideLoading()
                    }
                }
                negativeButton(R.string.cancel)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error showing uninstall dialog: ${e.message}")
        }
    }

    /**
     * 强行停止软件
     * @param info AppInfo
     */
    private fun stopApk(info: AppInfo) {
        try {
            MaterialDialog(requireContext()).show {
                title(R.string.app_stop)
                message(text = getString(R.string.app_stop_hint,info.name))
                positiveButton(R.string.done) {
                    try {
                        BlackBoxCore.get().stopPackage(info.packageName, 0)
                        toast(getString(R.string.is_stop,info.name))
                    } catch (e: Exception) {
                        Log.e(TAG, "Error stopping app: ${e.message}")
                    }
                }
                negativeButton(R.string.cancel)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error showing stop dialog: ${e.message}")
        }
    }

    /**
     * 清除软件数据
     * @param info AppInfo
     */
    private fun clearApk(info: AppInfo) {
        try {
            MaterialDialog(requireContext()).show {
                title(R.string.app_clear)
                message(text = getString(R.string.app_clear_hint,info.name))
                positiveButton(R.string.done) {
                    try {
                        showLoading()
                        viewModel.clearApkData(info.packageName)
                    } catch (e: Exception) {
                        Log.e(TAG, "Error clearing app data: ${e.message}")
                        hideLoading()
                    }
                }
                negativeButton(R.string.cancel)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error showing clear dialog: ${e.message}")
        }
    }

    fun installApk(source: String) {
        try {
            showLoading()
            viewModel.install(source)
        } catch (e: Exception) {
            Log.e(TAG, "Error installing APK: ${e.message}")
            hideLoading()
        }
    }

    private fun showLoading() {
        try {
            if(requireActivity() is LoadingActivity){
                (requireActivity() as LoadingActivity).showLoading()
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error showing loading: ${e.message}")
        }
    }

    private fun hideLoading() {
        try {
            if(requireActivity() is LoadingActivity){
                (requireActivity() as LoadingActivity).hideLoading()
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error hiding loading: ${e.message}")
        }
    }
}


