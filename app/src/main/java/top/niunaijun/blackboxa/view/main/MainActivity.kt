package top.niunaijun.blackboxa.view.main

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import com.afollestad.materialdialogs.MaterialDialog
import top.niunaijun.blackbox.BlackBoxCore
import top.niunaijun.blackboxa.R
import top.niunaijun.blackboxa.app.App
import top.niunaijun.blackboxa.databinding.ActivityMainBinding
import top.niunaijun.blackboxa.task.TaskEngine
import top.niunaijun.blackboxa.task.TaskMode
import top.niunaijun.blackboxa.util.Resolution
import top.niunaijun.blackboxa.util.inflate
import top.niunaijun.blackboxa.util.toast
import top.niunaijun.blackboxa.view.apps.AppsFragment
import top.niunaijun.blackboxa.view.automation.AutomationActivity
import top.niunaijun.blackboxa.view.base.LoadingActivity
import top.niunaijun.blackboxa.view.fake.FakeManagerActivity
import top.niunaijun.blackboxa.view.list.ListActivity
import top.niunaijun.blackboxa.view.setting.SettingActivity
import top.niunaijun.blackboxa.view.task.TaskConsoleFragment


class MainActivity : LoadingActivity() {

    private val viewBinding: ActivityMainBinding by inflate()

    companion object {
        private const val TAG = "MainActivity"

        fun start(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)

            try {
                BlackBoxCore.get().onBeforeMainActivityOnCreate(this)
            } catch (e: Exception) {
                Log.e(TAG, "Error in onBeforeMainActivityOnCreate: ${e.message}")
            }

            setContentView(viewBinding.root)
            initToolbar(viewBinding.toolbarLayout.toolbar, R.string.app_name)
            TaskMode.init(this)
            TaskEngine.ensureInit(this)
            showContent()
            TaskMode.enabled.observe(this) { showContent() }
            initFab()

            try {
                BlackBoxCore.get().onAfterMainActivityOnCreate(this)
            } catch (e: Exception) {
                Log.e(TAG, "Error in onAfterMainActivityOnCreate: ${e.message}")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Critical error in onCreate: ${e.message}")
            finish()
        }
    }

    /** Swaps between the app list and the task console depending on the mode. */
    private fun showContent() {
        try {
            val taskMode = TaskMode.isEnabled(this)
            viewBinding.fab.visibility = if (taskMode) View.GONE else View.VISIBLE

            val existing = supportFragmentManager.findFragmentById(R.id.fragment_container)
            val target = if (taskMode) TaskConsoleFragment::class.java else AppsFragment::class.java
            if (existing != null && target.isInstance(existing)) {
                return
            }
            val fragment = if (taskMode) TaskConsoleFragment() else AppsFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        } catch (e: Exception) {
            Log.e(TAG, "Error showing content: ${e.message}")
        }
    }

    private fun toggleTaskMode() {
        try {
            if (!TaskMode.isEnabled(this)) {
                TaskMode.setEnabled(this, true)
                toast(R.string.task_mode_on)
                return
            }
            val counts = TaskEngine.counts()
            val pending = counts.queued + counts.running
            if (pending <= 0) {
                disableTaskMode()
                return
            }
            MaterialDialog(this).show {
                title(R.string.task_exit_title)
                message(text = getString(R.string.task_exit_message, pending))
                positiveButton(R.string.task_exit_ok) { disableTaskMode() }
                negativeButton(R.string.cancel)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error toggling task mode: ${e.message}")
        }
    }

    private fun disableTaskMode() {
        TaskMode.setEnabled(this, false)
        toast(R.string.task_mode_off)
    }

    private fun initFab() {
        try {
            viewBinding.fab.setOnClickListener {
                try {
                    apkPathResult.launch(Intent(this, ListActivity::class.java))
                } catch (e: Exception) {
                    Log.e(TAG, "Error launching ListActivity: ${e.message}")
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error in initFab: ${e.message}")
        }
    }

    fun showFloatButton(show: Boolean) {
        try {
            val tranY: Float = Resolution.convertDpToPixel(120F, App.getContext())
            val time = 200L
            if (show) {
                viewBinding.fab.animate().translationY(0f).alpha(1f).setDuration(time)
                    .start()
            } else {
                viewBinding.fab.animate().translationY(tranY).alpha(0f).setDuration(time)
                    .start()
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error in showFloatButton: ${e.message}")
        }
    }

    private val apkPathResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            try {
                if (it.resultCode == RESULT_OK) {
                    val source = it.data?.getStringExtra("source")
                    if (source != null) {
                        val fragment =
                            supportFragmentManager.findFragmentById(R.id.fragment_container) as? AppsFragment
                        fragment?.installApk(source)
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error handling APK path result: ${e.message}")
            }
        }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        try {
            menuInflater.inflate(R.menu.menu_main, menu)
            return true
        } catch (e: Exception) {
            Log.e(TAG, "Error creating options menu: ${e.message}")
            return false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        try {
            when (item.itemId) {
                R.id.main_git -> {
                    val intent =
                        Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/ALEX5402/NewBlackbox"))
                    startActivity(intent)
                }

                R.id.main_setting -> {
                    SettingActivity.start(this)
                }

                R.id.main_tg -> {
                    val intent =
                        Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/newblackboxa"))
                    startActivity(intent)
                }

                R.id.fake_location -> {
                    startActivity(Intent(this, FakeManagerActivity::class.java))
                }

                R.id.main_task_mode -> {
                    toggleTaskMode()
                }

                R.id.main_automation -> {
                    AutomationActivity.start(this)
                }
            }

            return true
        } catch (e: Exception) {
            Log.e(TAG, "Error handling menu item selection: ${e.message}")
            return false
        }
    }
}
