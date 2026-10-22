package top.niunaijun.blackboxa.bean

import android.content.Context
import android.util.Log
import org.json.JSONObject
import top.niunaijun.blackbox.utils.GaidManager
import java.io.File

/**
 * Per-app Google Advertising ID (GAID) override.
 *
 * The file lives in the host files dir so the virtual app process can pick it
 * up when it binds, matching the proxy configuration mechanism.
 */
data class AppGaidConfig(
    val gaid: String = "",
    val limitAdTracking: Boolean = false
) {

    fun toJson(): String = JSONObject().apply {
        put("gaid", gaid)
        put("limitAdTracking", limitAdTracking)
    }.toString()

    val isValid: Boolean
        get() = gaid.isNotBlank()

    companion object {
        private const val TAG = "AppGaidConfig"

        fun fromJson(raw: String?): AppGaidConfig? {
            if (raw.isNullOrEmpty()) return null
            return try {
                val json = JSONObject(raw)
                val gaid = json.optString("gaid", "")
                if (gaid.isEmpty()) {
                    null
                } else {
                    AppGaidConfig(
                        gaid = gaid,
                        limitAdTracking = json.optBoolean("limitAdTracking", false)
                    )
                }
            } catch (e: Exception) {
                Log.e(TAG, "parse gaid config failed: ${e.message}")
                null
            }
        }
    }
}

object AppGaidStore {

    private const val TAG = "AppGaidStore"

    private fun configFile(context: Context): File =
        File(context.filesDir, GaidManager.CONFIG_FILE)

    private fun readAll(context: Context): JSONObject = try {
        val file = configFile(context)
        if (file.exists()) JSONObject(file.readText()) else JSONObject()
    } catch (e: Exception) {
        Log.e(TAG, "read gaid file failed: ${e.message}")
        JSONObject()
    }

    private fun writeAll(context: Context, all: JSONObject) {
        configFile(context).writeText(all.toString())
    }

    fun load(context: Context, packageName: String): AppGaidConfig? =
        try {
            AppGaidConfig.fromJson(
                GaidManager.readStoredConfig(context.filesDir, packageName)
            )
        } catch (e: Exception) {
            Log.e(TAG, "load gaid config failed: ${e.message}")
            null
        }

    fun save(context: Context, packageName: String, config: AppGaidConfig) {
        try {
            val all = readAll(context)
            // Drop legacy "<userId>_<packageName>" entries, if any.
            val legacyKeys = all.keys().asSequence()
                .filter { it.endsWith("_$packageName") }
                .toList()
            legacyKeys.forEach { all.remove(it) }
            all.put(GaidManager.key(packageName), config.toJson())
            writeAll(context, all)
        } catch (e: Exception) {
            Log.e(TAG, "save gaid config failed: ${e.message}")
        }
    }

    fun remove(context: Context, packageName: String) {
        try {
            val all = readAll(context)
            val legacyKeys = all.keys().asSequence()
                .filter { it.endsWith("_$packageName") }
                .toList()
            legacyKeys.forEach { all.remove(it) }
            all.remove(GaidManager.key(packageName))
            writeAll(context, all)
        } catch (e: Exception) {
            Log.e(TAG, "remove gaid config failed: ${e.message}")
        }
    }
}
