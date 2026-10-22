package top.niunaijun.blackboxa.bean

import android.content.Context
import android.util.Log
import org.json.JSONObject
import java.io.File

/**
 * Per-app device fingerprint override.
 *
 * Stored in the host files dir as {@code app_device.json} keyed by package name,
 * so the virtual app process can pick it up when it binds.
 *
 * The profile is kept as **raw json**: whatever the caller submits (for example
 * a full device profile produced by the emu generator: androidId / imei1 / mcc /
 * timezone / buildName / displayWidth …) is stored untouched and handed to the
 * guest process. The typed fields below are only the normalized view used by the
 * settings screen, they do not limit what can be stored.
 */
data class AppDeviceConfig(
    val brand: String = "",
    val model: String = "",
    val manufacturer: String = "",
    val device: String = "",
    val product: String = "",
    val width: Int = 0,
    val height: Int = 0,
    val dpi: Int = 0,
    /** 完整原始 JSON（emu 档案的全部字段都留在这里）。 */
    val raw: String = ""
) {

    fun toJson(): String {
        val json = rawJson()
        json.put("brand", brand)
        json.put("model", model)
        json.put("manufacturer", manufacturer)
        json.put("device", device)
        json.put("product", product)
        json.put("width", width)
        json.put("height", height)
        json.put("dpi", dpi)
        return json.toString()
    }

    private fun rawJson(): JSONObject = try {
        if (raw.isEmpty()) JSONObject() else JSONObject(raw)
    } catch (e: Exception) {
        Log.e(TAG, "parse raw device json failed: ${e.message}")
        JSONObject()
    }

    val isEmpty: Boolean
        get() = brand.isBlank() && model.isBlank() && manufacturer.isBlank() &&
                device.isBlank() && product.isBlank() &&
                width <= 0 && height <= 0 && dpi <= 0 &&
                !hasRawContent()

    private fun hasRawContent(): Boolean {
        val json = rawJson()
        val keys = json.keys()
        while (keys.hasNext()) {
            val value = json.opt(keys.next())
            when (value) {
                null, JSONObject.NULL -> continue
                is String -> if (value.isNotBlank()) return true
                is Number -> if (value.toDouble() != 0.0) return true
                is Boolean -> if (value) return true
                else -> return true
            }
        }
        return false
    }

    companion object {
        private const val TAG = "AppDeviceConfig"

        fun fromJson(raw: String?): AppDeviceConfig? {
            if (raw.isNullOrEmpty()) return null
            return try {
                val json = JSONObject(raw)
                val config = AppDeviceConfig(
                    brand = json.optString("brand", ""),
                    model = json.optString("model", ""),
                    manufacturer = json.optString("manufacturer", ""),
                    device = firstText(json, "device", "name"),
                    product = firstText(json, "product", "hardwareName"),
                    width = firstInt(json, "width", "displayWidth"),
                    height = firstInt(json, "height", "displayHeight"),
                    dpi = firstInt(json, "dpi", "screenDensityNumber"),
                    raw = json.toString()
                )
                if (config.isEmpty) null else config
            } catch (e: Exception) {
                Log.e(TAG, "parse device config failed: ${e.message}")
                null
            }
        }

        private fun firstText(json: JSONObject, vararg keys: String): String {
            for (key in keys) {
                val value = json.optString(key, "")
                if (value.isNotBlank()) return value
            }
            return ""
        }

        private fun firstInt(json: JSONObject, vararg keys: String): Int {
            for (key in keys) {
                val value = json.optInt(key, 0)
                if (value > 0) return value
                val text = json.optString(key, "").trim()
                if (text.isNotEmpty()) {
                    text.toIntOrNull()?.let { if (it > 0) return it }
                }
            }
            return 0
        }
    }
}

object AppDeviceStore {

    const val CONFIG_FILE = "app_device.json"

    private const val TAG = "AppDeviceStore"

    private fun configFile(context: Context): File =
        File(context.filesDir, CONFIG_FILE)

    private fun readAll(context: Context): JSONObject = try {
        val file = configFile(context)
        if (file.exists()) JSONObject(file.readText()) else JSONObject()
    } catch (e: Exception) {
        Log.e(TAG, "read device file failed: ${e.message}")
        JSONObject()
    }

    private fun writeAll(context: Context, all: JSONObject) {
        configFile(context).writeText(all.toString())
    }

    fun load(context: Context, packageName: String): AppDeviceConfig? = try {
        AppDeviceConfig.fromJson(readAll(context).optString(packageName, null))
    } catch (e: Exception) {
        Log.e(TAG, "load device config failed: ${e.message}")
        null
    }

    fun raw(context: Context, packageName: String): String? = try {
        readAll(context).optString(packageName, null)
    } catch (e: Exception) {
        null
    }

    fun save(context: Context, packageName: String, config: AppDeviceConfig) {
        try {
            val all = readAll(context)
            if (config.isEmpty) {
                all.remove(packageName)
            } else {
                all.put(packageName, config.toJson())
            }
            writeAll(context, all)
        } catch (e: Exception) {
            Log.e(TAG, "save device config failed: ${e.message}")
        }
    }

    /**
     * Stores an already-normalized raw json profile (device info forwarded from
     * the api / task engine). Nothing is filtered out.
     */
    fun saveRaw(context: Context, packageName: String, profile: String) {
        try {
            val config = AppDeviceConfig.fromJson(profile) ?: run {
                remove(context, packageName)
                return
            }
            save(context, packageName, config)
        } catch (e: Exception) {
            Log.e(TAG, "save raw device config failed: ${e.message}")
        }
    }

    fun remove(context: Context, packageName: String) {
        try {
            val all = readAll(context)
            all.remove(packageName)
            writeAll(context, all)
        } catch (e: Exception) {
            Log.e(TAG, "remove device config failed: ${e.message}")
        }
    }
}
