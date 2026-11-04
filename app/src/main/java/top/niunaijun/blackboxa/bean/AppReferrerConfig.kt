package top.niunaijun.blackboxa.bean

import android.content.Context
import android.net.Uri
import android.util.Log
import org.json.JSONObject
import top.niunaijun.blackbox.utils.ReferrerManager
import java.io.File

/**
 * 每个应用的 Play 安装来源（install referrer）。
 *
 * <p>值可以是裸 referrer（`adjust_reftag=cwKyD4chsBr9n`），也可以是完整商店链接
 * （`https://play.google.com/store/apps/details?id=...&referrer=...`），后者会自动
 * 取出并解码 `referrer` 查询参数。</p>
 */
data class AppReferrerConfig(
    val referrer: String = "",
    val clickTimestamp: Long = 0L,
    val installTimestamp: Long = 0L,
    val installVersion: String = ""
) {

    fun toJson(): String = JSONObject().apply {
        put("referrer", referrer)
        put("clickTimestamp", clickTimestamp)
        put("installTimestamp", installTimestamp)
        if (installVersion.isNotEmpty()) {
            put("installVersion", installVersion)
        }
    }.toString()

    val isValid: Boolean
        get() = referrer.isNotBlank()

    companion object {
        private const val TAG = "AppReferrerConfig"

        /** 裸串原样返回；商店链接取 `referrer` 查询参数（Uri 已做 URL 解码）。 */
        fun normalize(raw: String): String {
            val text = raw.trim()
            if (!text.startsWith("http://", true) && !text.startsWith("https://", true)) {
                return text
            }
            return try {
                Uri.parse(text).getQueryParameter("referrer")?.trim().orEmpty()
            } catch (e: Exception) {
                Log.e(TAG, "parse referrer url failed: ${e.message}")
                ""
            }
        }

        fun fromJson(raw: String?): AppReferrerConfig? {
            if (raw.isNullOrEmpty()) return null
            return try {
                val json = JSONObject(raw)
                val referrer = json.optString("referrer", "")
                if (referrer.isEmpty()) {
                    null
                } else {
                    AppReferrerConfig(
                        referrer = referrer,
                        clickTimestamp = json.optLong("clickTimestamp", 0L),
                        installTimestamp = json.optLong("installTimestamp", 0L),
                        installVersion = json.optString("installVersion", "")
                    )
                }
            } catch (e: Exception) {
                Log.e(TAG, "parse referrer config failed: ${e.message}")
                null
            }
        }
    }
}

object AppReferrerStore {

    private const val TAG = "AppReferrerStore"

    private fun configFile(context: Context): File =
        File(context.filesDir, ReferrerManager.CONFIG_FILE)

    private fun readAll(context: Context): JSONObject = try {
        val file = configFile(context)
        if (file.exists()) JSONObject(file.readText()) else JSONObject()
    } catch (e: Exception) {
        Log.e(TAG, "read referrer file failed: ${e.message}")
        JSONObject()
    }

    private fun writeAll(context: Context, all: JSONObject) {
        configFile(context).writeText(all.toString())
    }

    fun load(context: Context, packageName: String): AppReferrerConfig? = try {
        AppReferrerConfig.fromJson(
            ReferrerManager.readStoredConfig(context.filesDir, packageName)
        )
    } catch (e: Exception) {
        Log.e(TAG, "load referrer config failed: ${e.message}")
        null
    }

    fun save(context: Context, packageName: String, config: AppReferrerConfig) {
        try {
            val all = readAll(context)
            all.put(ReferrerManager.key(packageName), config.toJson())
            writeAll(context, all)
        } catch (e: Exception) {
            Log.e(TAG, "save referrer config failed: ${e.message}")
        }
    }

    fun remove(context: Context, packageName: String) {
        try {
            val all = readAll(context)
            all.remove(ReferrerManager.key(packageName))
            writeAll(context, all)
        } catch (e: Exception) {
            Log.e(TAG, "remove referrer config failed: ${e.message}")
        }
    }
}
