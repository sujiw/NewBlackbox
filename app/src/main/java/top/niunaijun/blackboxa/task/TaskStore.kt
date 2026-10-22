package top.niunaijun.blackboxa.task

import android.content.Context
import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import java.io.File

/**
 * Simple json backed store for the task queue. Writes are atomic (tmp + rename)
 * so a crash cannot leave a half written queue behind.
 */
object TaskStore {

    private const val TAG = "TaskStore"
    private const val FILE_NAME = "task_jobs.json"

    private fun file(context: Context) = File(context.filesDir, FILE_NAME)

    fun load(context: Context): List<TaskJob> {
        val file = file(context)
        if (!file.exists()) {
            return emptyList()
        }
        return try {
            val json = JSONObject(file.readText())
            val array = json.optJSONArray("jobs") ?: return emptyList()
            val jobs = mutableListOf<TaskJob>()
            for (i in 0 until array.length()) {
                val item = array.optJSONObject(i) ?: continue
                jobs.add(TaskJob.fromJson(item))
            }
            jobs
        } catch (e: Throwable) {
            Log.w(TAG, "load tasks failed: ${e.message}")
            emptyList()
        }
    }

    fun save(context: Context, jobs: List<TaskJob>) {
        val file = file(context)
        val tmp = File(file.parentFile, "$FILE_NAME.tmp")
        try {
            val array = JSONArray()
            jobs.forEach { array.put(it.toJson()) }
            tmp.writeText(JSONObject().put("jobs", array).toString())
            if (!tmp.renameTo(file)) {
                file.writeText(tmp.readText())
                tmp.delete()
            }
        } catch (e: Throwable) {
            Log.w(TAG, "save tasks failed: ${e.message}")
            tmp.delete()
        }
    }
}
