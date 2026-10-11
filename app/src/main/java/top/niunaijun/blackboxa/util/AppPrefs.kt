package top.niunaijun.blackboxa.util

import android.content.Context
import android.content.SharedPreferences
import top.niunaijun.blackboxa.app.App

object AppPrefs {

    private const val NAME = "blackbox_capture_prefs"

    private val prefs: SharedPreferences by lazy {
        App.getContext().getSharedPreferences(NAME, Context.MODE_PRIVATE)
    }

    /** packageName -> original targetSdk captured before lowering it. */
    val captureTargetSdk: SharedPreferences get() = prefs
}
