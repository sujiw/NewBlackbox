package top.niunaijun.blackboxa.api

import android.content.Context
import org.json.JSONObject
import top.niunaijun.blackboxa.bean.AppDeviceStore
import top.niunaijun.blackboxa.bean.AppGaidConfig
import top.niunaijun.blackboxa.bean.AppGaidStore
import top.niunaijun.blackboxa.bean.AppProxyConfig
import top.niunaijun.blackboxa.bean.AppProxyStore

/**
 * Shared writer/reader for the per-app overrides, used by both the REST api and
 * the task engine so the json shape stays identical.
 */
object ConfigOps {

    fun read(context: Context, packageName: String): JSONObject {
        val proxy = AppProxyStore.load(context, packageName)
        val gaid = AppGaidStore.load(context, packageName)
        val device = AppDeviceStore.load(context, packageName)
        return JSONObject()
            .put("package", packageName)
            .put("proxy", proxy?.let { JSONObject(it.toJson()) } ?: JSONObject.NULL)
            .put("gaid", gaid?.let { JSONObject(it.toJson()) } ?: JSONObject.NULL)
            .put("device", device?.let { JSONObject(it.toJson()) } ?: JSONObject.NULL)
    }

    /**
     * Applies the blocks present in [body]; missing blocks are left untouched,
     * explicit {@code null} removes the stored value.
     */
    fun apply(context: Context, packageName: String, body: JSONObject): JSONObject {
        if (body.has("proxy")) {
            val proxy = body.optJSONObject("proxy")
            if (proxy == null) {
                AppProxyStore.remove(context, packageName)
            } else {
                AppProxyStore.save(
                    context, packageName,
                    AppProxyConfig(
                        enabled = proxy.optBoolean("enabled", true),
                        type = proxy.optString("type", AppProxyConfig.TYPE_HTTP),
                        host = proxy.optString("host", ""),
                        port = proxy.optInt("port", 0),
                        username = proxy.optString("username", ""),
                        password = proxy.optString("password", ""),
                        bypass = proxy.optString("bypass", "localhost,127.0.0.1")
                    )
                )
            }
        }
        if (body.has("gaid")) {
            val gaid = body.optJSONObject("gaid")
            val id = gaid?.optString("id", "")?.ifEmpty {
                gaid.optString("gaid", "")
            }.orEmpty()
            if (gaid == null || id.isEmpty()) {
                AppGaidStore.remove(context, packageName)
            } else {
                AppGaidStore.save(
                    context, packageName,
                    AppGaidConfig(
                        gaid = id,
                        limitAdTracking = gaid.optBoolean("limitAdTracking", false)
                    )
                )
            }
        }
        if (body.has("device")) {
            val device = body.optJSONObject("device")
            if (device == null) {
                AppDeviceStore.remove(context, packageName)
            } else {
                // 整块设备信息透传：emu 生成的档案（androidId / imei1 / mcc /
                // buildName / displayWidth …）全部保留，不做字段白名单过滤。
                AppDeviceStore.saveRaw(context, packageName, device.toString())
            }
        }
        return read(context, packageName)
    }

    fun clear(context: Context, packageName: String) {
        AppProxyStore.remove(context, packageName)
        AppGaidStore.remove(context, packageName)
        AppDeviceStore.remove(context, packageName)
    }
}
