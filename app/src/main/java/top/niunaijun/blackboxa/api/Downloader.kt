package top.niunaijun.blackboxa.api

import android.content.Context
import java.io.File
import java.net.HttpURLConnection
import java.net.URL

/** Shared apk download helper for the api endpoints and the task engine. */
object Downloader {

    fun download(context: Context, url: String): File {
        val dir = File(context.filesDir, "api_download").apply { mkdirs() }
        val name = url.substringAfterLast('/').substringBefore('?').ifBlank { "app.apk" }
        val target = File(dir, "${System.currentTimeMillis()}-$name")
        val connection = (URL(url).openConnection() as HttpURLConnection).apply {
            connectTimeout = 15_000
            readTimeout = 120_000
            instanceFollowRedirects = true
        }
        try {
            val code = connection.responseCode
            if (code !in 200..299) {
                throw IllegalStateException("download failed: HTTP $code")
            }
            connection.inputStream.use { input ->
                target.outputStream().use { output -> input.copyTo(output) }
            }
        } finally {
            connection.disconnect()
        }
        return target
    }
}
