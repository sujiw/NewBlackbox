package top.niunaijun.blackboxa.api

import android.content.Context
import java.util.UUID

/**
 * Persisted settings for the local automation API.
 */
object ApiSettings {

    private const val PREF = "blackbox_api"
    private const val KEY_ENABLED = "enabled"
    private const val KEY_PORT = "port"
    private const val KEY_TOKEN = "token"

    const val DEFAULT_PORT = 8080

    private fun prefs(context: Context) =
        context.applicationContext.getSharedPreferences(PREF, Context.MODE_PRIVATE)

    fun isEnabled(context: Context): Boolean =
        prefs(context).getBoolean(KEY_ENABLED, false)

    fun setEnabled(context: Context, enabled: Boolean) {
        prefs(context).edit().putBoolean(KEY_ENABLED, enabled).apply()
    }

    fun port(context: Context): Int =
        prefs(context).getInt(KEY_PORT, DEFAULT_PORT).coerceIn(1024, 65535)

    fun setPort(context: Context, port: Int) {
        prefs(context).edit().putInt(KEY_PORT, port.coerceIn(1024, 65535)).apply()
    }

    /** Returns the api token, creating one on first use. */
    fun token(context: Context): String {
        val prefs = prefs(context)
        val existing = prefs.getString(KEY_TOKEN, null)
        if (!existing.isNullOrEmpty()) {
            return existing
        }
        val created = newToken()
        prefs.edit().putString(KEY_TOKEN, created).apply()
        return created
    }

    fun resetToken(context: Context): String {
        val created = newToken()
        prefs(context).edit().putString(KEY_TOKEN, created).apply()
        return created
    }

    private fun newToken(): String = UUID.randomUUID().toString().replace("-", "")
}
