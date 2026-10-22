package top.niunaijun.blackboxa.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import top.niunaijun.blackbox.BlackBoxCore

/**
 *
 * @Description: 快捷方式跳转activity
 * @Author: wukaicheng
 * @CreateDate: 2022/2/11 23:13
 */
class ShortcutActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pkg = intent.getStringExtra("pkg")

        lifecycleScope.launch {
            // Shortcuts created by older builds still carry a "userId" extra;
            // the container is single user now, so it is ignored.
            BlackBoxCore.get().launchApk(pkg, 0)
            finish()
        }
    }
}