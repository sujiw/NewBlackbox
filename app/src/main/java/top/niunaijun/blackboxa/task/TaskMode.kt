package top.niunaijun.blackboxa.task

import android.content.Context
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * Task mode switch shared by the api, the engine and the main screen.
 */
object TaskMode {

    private const val PREF = "blackbox_task_mode"
    private const val KEY_ENABLED = "enabled"
    private const val KEY_PAUSED = "paused"

    private val enabledState = MutableLiveData(false)
    private val pausedState = MutableLiveData(false)

    val enabled: LiveData<Boolean> get() = enabledState
    val paused: LiveData<Boolean> get() = pausedState

    private fun prefs(context: Context) =
        context.applicationContext.getSharedPreferences(PREF, Context.MODE_PRIVATE)

    fun init(context: Context) {
        enabledState.postValue(isEnabled(context))
        pausedState.postValue(isPaused(context))
    }

    fun isEnabled(context: Context): Boolean = prefs(context).getBoolean(KEY_ENABLED, false)

    fun isPaused(context: Context): Boolean = prefs(context).getBoolean(KEY_PAUSED, false)

    fun setEnabled(context: Context, enabled: Boolean) {
        prefs(context).edit { putBoolean(KEY_ENABLED, enabled) }
        enabledState.postValue(enabled)
        TaskEngine.ensureInit(context)
        TaskEngine.onModeChanged()
    }

    fun setPaused(context: Context, paused: Boolean) {
        prefs(context).edit { putBoolean(KEY_PAUSED, paused) }
        pausedState.postValue(paused)
        TaskEngine.onModeChanged()
    }
}
