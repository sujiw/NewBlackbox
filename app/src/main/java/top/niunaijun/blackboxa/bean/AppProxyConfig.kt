package top.niunaijun.blackboxa.bean

import android.content.Context
import android.util.Log
import org.json.JSONObject
import top.niunaijun.blackbox.utils.AppProxyManager
import java.io.File

/**
 * Per-app network proxy configuration.
 *
 * The file is stored inside the guest data dir so the guest process can read
 * it without touching SharedPreferences (which deadlocks during bind).
 */
data class AppProxyConfig(
    val enabled: Boolean = false,
    val type: String = TYPE_HTTP,
    val host: String = "",
    val port: Int = 0,
    val username: String = "",
    val password: String = "",
    val bypass: String = "localhost,127.0.0.1"
) {

    fun toJson(): String = JSONObject().apply {
        put("enabled", enabled)
        put("type", type)
        put("host", host)
        put("port", port)
        put("username", username)
        put("password", password)
        put("bypass", bypass)
    }.toString()

    val isValid: Boolean
        get() = host.isNotBlank() && port in 1..65535

    val display: String
        get() = "$type://$host:$port"

    companion object {
        const val TYPE_HTTP = "http"
        const val TYPE_SOCKS5 = "socks5"

        fun fromJson(raw: String?): AppProxyConfig? {
            if (raw.isNullOrEmpty()) return null
            return try {
                val json = JSONObject(raw)
                AppProxyConfig(
                    enabled = json.optBoolean("enabled", false),
                    type = json.optString("type", TYPE_HTTP),
                    host = json.optString("host", ""),
                    port = json.optInt("port", 0),
                    username = json.optString("username", ""),
                    password = json.optString("password", ""),
                    bypass = json.optString("bypass", "localhost,127.0.0.1")
                )
            } catch (e: Exception) {
                Log.e(TAG, "parse proxy config failed: ${e.message}")
                null
            }
        }

        private const val TAG = "AppProxyConfig"
    }
}

object AppProxyStore {

    private const val TAG = "AppProxyStore"

    private fun configFile(context: Context): File =
        File(context.filesDir, AppProxyManager.CONFIG_FILE)

    private fun readAll(context: Context): JSONObject = try {
        val file = configFile(context)
        if (file.exists()) JSONObject(file.readText()) else JSONObject()
    } catch (e: Exception) {
        Log.e(TAG, "read proxy file failed: ${e.message}")
        JSONObject()
    }

    private fun writeAll(context: Context, all: JSONObject) {
        configFile(context).writeText(all.toString())
    }

    fun load(context: Context, packageName: String): AppProxyConfig? =
        try {
            val all = readAll(context)
            val raw = all.optString(AppProxyManager.key(packageName), null)
                ?: legacyKeys(all, packageName).firstOrNull()?.let { all.optString(it, null) }
            AppProxyConfig.fromJson(raw)
        } catch (e: Exception) {
            Log.e(TAG, "load proxy config failed: ${e.message}")
            null
        }

    fun save(context: Context, packageName: String, config: AppProxyConfig) {
        try {
            val all = readAll(context)
            // Drop legacy "<userId>_<packageName>" entries, if any.
            legacyKeys(all, packageName).forEach { all.remove(it) }
            all.put(AppProxyManager.key(packageName), config.toJson())
            writeAll(context, all)
        } catch (e: Exception) {
            Log.e(TAG, "save proxy config failed: ${e.message}")
        }
    }

    fun remove(context: Context, packageName: String) {
        try {
            val all = readAll(context)
            legacyKeys(all, packageName).forEach { all.remove(it) }
            all.remove(AppProxyManager.key(packageName))
            writeAll(context, all)
        } catch (e: Exception) {
            Log.e(TAG, "remove proxy config failed: ${e.message}")
        }
    }

    private fun legacyKeys(all: JSONObject, packageName: String): List<String> {
        val suffix = "_$packageName"
        return all.keys().asSequence().filter { it.endsWith(suffix) }.toList()
    }
}
