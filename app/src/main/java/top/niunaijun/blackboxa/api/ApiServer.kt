package top.niunaijun.blackboxa.api

import android.content.Context
import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import top.niunaijun.blackbox.BlackBoxCore
import top.niunaijun.blackbox.entity.pm.InstallResult
import top.niunaijun.blackboxa.task.JobState
import top.niunaijun.blackboxa.task.TaskCounts
import top.niunaijun.blackboxa.task.TaskEngine
import top.niunaijun.blackboxa.task.TaskJob
import top.niunaijun.blackboxa.task.TaskMode
import top.niunaijun.blackboxa.task.TaskStep
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream
import java.io.OutputStream
import java.net.ServerSocket
import java.net.Socket
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Minimal REST server used to automate the virtual space.
 *
 * All endpoints require the {@code X-Api-Token} header. The token is generated
 * on first use and can be rotated from the settings screen.
 *
 * <pre>
 * GET  /v1/ping
 * GET  /v1/apps
 * POST /v1/apps/install   {"url"|"path"|"package", "config":{...}}   (config optional, same shape as below)
 * POST /v1/apps/launch    {"package"}
 * POST /v1/apps/stop      {"package"}   (package optional, omit to stop every running app)
 * POST /v1/apps/uninstall {"package"}   (also clears proxy/gaid/device stored for the package)
 * GET  /v1/apps/{pkg}/config
 * POST /v1/apps/{pkg}/config  {"proxy":{...}|null,"gaid":{...}|null,"device":{...}|null}
 *
 * POST /v1/tasks              {"steps":[...]} or {"type":...} or {"tasks":[...]}
 * GET  /v1/tasks              ?state=&limit=
 * GET  /v1/tasks/{id}
 * POST /v1/tasks/{id}/cancel
 * POST /v1/tasks/clear        ?state=
 * POST /v1/tasks/stop         cancel queued + running jobs
 * GET  /v1/tasks/log          ?since=
 *
 * GET  /v1/task-mode
 * POST /v1/task-mode          {"enabled":true,"paused":false}
 *
 * The container is single user; a legacy {@code userId} parameter is still
 * accepted for compatibility but ignored.
 * </pre>
 */
object ApiServer {

    private const val TAG = "ApiServer"
    private const val VERSION = 1

    /** The container is single user, every api call uses user 0. */
    private const val USER_ID = 0

    @Volatile
    private var serverSocket: ServerSocket? = null

    @Volatile
    private var appContext: Context? = null

    private var executor: ExecutorService? = null

    @Volatile
    var isRunning: Boolean = false
        private set

    fun start(context: Context, port: Int = ApiSettings.port(context)): Boolean {
        if (isRunning) {
            return true
        }
        return try {
            val socket = ServerSocket(port)
            serverSocket = socket
            appContext = context.applicationContext
            executor = Executors.newCachedThreadPool()
            isRunning = true
            Thread({ acceptLoop(socket) }, "blackbox-api-accept").start()
            Log.i(TAG, "api server listening on 0.0.0.0:$port")
            true
        } catch (e: Throwable) {
            Log.e(TAG, "unable to start api server: ${e.message}")
            isRunning = false
            false
        }
    }

    fun stop() {
        isRunning = false
        try {
            serverSocket?.close()
        } catch (_: Throwable) {
        }
        serverSocket = null
        executor?.shutdownNow()
        executor = null
    }

    private fun acceptLoop(socket: ServerSocket) {
        while (isRunning && !socket.isClosed) {
            val client = try {
                socket.accept()
            } catch (e: Throwable) {
                if (isRunning) {
                    Log.w(TAG, "accept failed: ${e.message}")
                }
                break
            }
            executor?.execute { handleClient(client) }
        }
    }

    private fun handleClient(client: Socket) {
        client.use { socket ->
            try {
                socket.soTimeout = 20_000
                val request = readRequest(socket.getInputStream()) ?: return
                val output = socket.getOutputStream()
                val context = appContext ?: return
                if (request.headers["x-api-token"] != ApiSettings.token(context)) {
                    respond(output, 401, error("invalid token"))
                    return
                }
                val result = try {
                    route(context, request)
                } catch (e: Throwable) {
                    Log.e(TAG, "request failed: ${request.method} ${request.path}", e)
                    ApiResult(500, error(e.message ?: e.javaClass.simpleName))
                }
                respond(output, result.code, result.body)
            } catch (e: Throwable) {
                Log.w(TAG, "client handling failed: ${e.message}")
            }
        }
    }

    private fun route(context: Context, request: HttpRequest): ApiResult {
        val segments = request.path.trim('/').split('/').filter { it.isNotEmpty() }
        if (segments.size < 2 || segments[0] != "v1") {
            return ApiResult(404, error("unknown endpoint"))
        }
        if (segments.size == 2 && segments[1] == "ping") {
            return ApiResult(200, JSONObject().put("ok", true).put("version", VERSION))
        }
        TaskEngine.ensureInit(context)
        when (segments[1]) {
            "task-mode" -> return handleTaskMode(context, request)
            "tasks" -> return handleTasks(request, segments)
        }
        if (segments[1] != "apps") {
            return ApiResult(404, error("unknown endpoint"))
        }
        if (segments.size == 2) {
            if (request.method != "GET") {
                return ApiResult(405, error("method not allowed"))
            }
            return ApiResult(200, listApps())
        }
        if (segments.size == 3) {
            val body = parseBody(request)
            return when (segments[2]) {
                "install" -> if (request.method == "POST") {
                    install(context, body)
                } else {
                    ApiResult(405, error("method not allowed"))
                }
                "launch" -> if (request.method == "POST") {
                    launch(body)
                } else {
                    ApiResult(405, error("method not allowed"))
                }
                "stop" -> if (request.method == "POST") {
                    stop(body)
                } else {
                    ApiResult(405, error("method not allowed"))
                }
                "uninstall" -> if (request.method == "POST") {
                    uninstall(body)
                } else {
                    ApiResult(405, error("method not allowed"))
                }
                else -> ApiResult(404, error("unknown endpoint"))
            }
        }
        if (segments.size == 4 && segments[3] == "config") {
            val packageName = segments[2]
            return when (request.method) {
                "GET" -> ApiResult(200, readConfig(context, packageName))
                "POST", "PUT" -> ApiResult(200, writeConfig(context, packageName, parseBody(request)))
                else -> ApiResult(405, error("method not allowed"))
            }
        }
        return ApiResult(404, error("unknown endpoint"))
    }

    // ------------------------------------------------------------- task mode

    private fun handleTaskMode(context: Context, request: HttpRequest): ApiResult {
        if (request.method == "POST" || request.method == "PUT") {
            val body = parseBody(request)
            if (body.has("enabled")) {
                TaskMode.setEnabled(context, body.optBoolean("enabled", false))
            }
            if (body.has("paused")) {
                TaskMode.setPaused(context, body.optBoolean("paused", false))
            }
        } else if (request.method != "GET") {
            return ApiResult(405, error("method not allowed"))
        }
        return ApiResult(200, taskModeJson(context))
    }

    private fun taskModeJson(context: Context): JSONObject =
        JSONObject()
            .put("enabled", TaskMode.isEnabled(context))
            .put("paused", TaskMode.isPaused(context))
            .put("port", ApiSettings.port(context))
            .put("counts", countsJson(TaskEngine.counts()))

    private fun countsJson(counts: TaskCounts): JSONObject = JSONObject()
        .put("queued", counts.queued)
        .put("running", counts.running)
        .put("succeeded", counts.succeeded)
        .put("failed", counts.failed)

    // ------------------------------------------------------------------ tasks

    private fun handleTasks(request: HttpRequest, segments: List<String>): ApiResult {
        if (segments.size == 2) {
            return when (request.method) {
                "GET" -> ApiResult(200, listTasks(request))
                "POST" -> ApiResult(200, submitTasks(parseBody(request)))
                else -> ApiResult(405, error("method not allowed"))
            }
        }
        if (segments.size == 3) {
            val action = segments[2]
            if (request.method != "POST") {
                return when (action) {
                    "log" -> ApiResult(200, readTaskLog(request))
                    "clear" -> ApiResult(405, error("method not allowed"))
                    else -> {
                        if (request.method == "GET") {
                            val job = TaskEngine.find(action)
                                ?: return ApiResult(404, error("task not found"))
                            return ApiResult(200, job.toJson())
                        }
                        ApiResult(405, error("method not allowed"))
                    }
                }
            }
            return when (action) {
                "clear" -> {
                    val state = parseBody(request).optString("state", "")
                        .ifEmpty { queryParam(request, "state") }
                    ApiResult(200, JSONObject().put("success", true).put("removed", TaskEngine.clearFinished(state)))
                }

                "stop" -> ApiResult(200, JSONObject().put("success", true).put("count", TaskEngine.stopAll()))
                else -> ApiResult(404, error("unknown endpoint"))
            }
        }
        if (segments.size == 4 && segments[3] == "cancel") {
            if (request.method != "POST") {
                return ApiResult(405, error("method not allowed"))
            }
            val success = TaskEngine.cancel(segments[2])
            return ApiResult(if (success) 200 else 404, JSONObject().put("success", success))
        }
        return ApiResult(404, error("unknown endpoint"))
    }

    private fun listTasks(request: HttpRequest): JSONObject {
        val filter = JobState.parse(queryParam(request, "state"))
        val limit = request.queryInt("limit", 50).coerceIn(1, 200)
        val tasks = JSONArray()
        TaskEngine.snapshot()
            .asSequence()
            .filter { filter == null || it.state == filter }
            .take(limit)
            .forEach { tasks.put(it.toJson()) }
        return JSONObject()
            .put("tasks", tasks)
            .put("counts", countsJson(TaskEngine.counts()))
    }

    private fun submitTasks(body: JSONObject): JSONObject {
        val jobs = mutableListOf<TaskJob>()
        when {
            body.has("tasks") -> {
                val array = body.optJSONArray("tasks")
                    ?: throw IllegalArgumentException("tasks must be an array")
                for (i in 0 until array.length()) {
                    val item = array.optJSONObject(i) ?: continue
                    jobs.add(parseJob(item))
                }
            }

            body.has("steps") || body.has("type") -> jobs.add(parseJob(body))
            else -> throw IllegalArgumentException("steps or tasks is required")
        }
        if (jobs.isEmpty()) {
            throw IllegalArgumentException("no task provided")
        }
        jobs.forEach { job ->
            TaskEngine.validate(job)?.let { throw IllegalArgumentException(it) }
        }
        val created = JSONArray()
        jobs.forEach { job ->
            val stored = TaskEngine.submit(job)
                ?: throw IllegalStateException("unable to enqueue task (queue full?)")
            created.put(
                JSONObject()
                    .put("id", stored.id)
                    .put("state", stored.state.name)
                    .put("steps", stored.steps.size)
            )
        }
        return JSONObject().put("tasks", created).put("counts", countsJson(TaskEngine.counts()))
    }

    private fun parseJob(json: JSONObject): TaskJob {
        val steps = mutableListOf<TaskStep>()
        val array = json.optJSONArray("steps")
        if (array != null) {
            for (i in 0 until array.length()) {
                val item = array.optJSONObject(i) ?: continue
                steps.add(TaskStep(type = item.optString("type", ""), params = JSONObject(item.toString())))
            }
        } else if (json.has("type")) {
            steps.add(TaskStep(type = json.optString("type", ""), params = JSONObject(json.toString())))
        }
        return TaskJob(
            id = json.optString("id", ""),
            name = json.optString("name", ""),
            stopOnError = json.optBoolean("stopOnError", true),
            steps = steps
        )
    }

    private fun readTaskLog(request: HttpRequest): JSONObject {
        val since = request.queryInt("since", 0)
        val (last, lines) = TaskEngine.readLogs(since)
        val array = JSONArray()
        lines.forEach { array.put(it) }
        return JSONObject().put("since", last).put("lines", array)
    }

    private fun queryParam(request: HttpRequest, name: String): String {
        if (request.query.isEmpty()) {
            return ""
        }
        return request.query.split('&')
            .firstOrNull { it.startsWith("$name=") }
            ?.substringAfter('=')
            .orEmpty()
    }

    private fun listApps(): JSONObject {
        val apps = JSONArray()
        installedPackages().forEach { apps.put(JSONObject().put("package", it)) }
        return JSONObject().put("apps", apps)
    }

    private fun installedPackages(): List<String> =
        try {
            BlackBoxCore.getBPackageManager()
                .getInstalledPackagesAsUser(USER_ID)
                .mapNotNull { it.packageName }
        } catch (e: Throwable) {
            Log.w(TAG, "list installed packages failed: ${e.message}")
            emptyList()
        }

    private fun runningPackages(): List<String> =
        installedPackages().filter { pkg ->
            try {
                val info = BlackBoxCore.getBActivityManager()
                    .getRunningAppProcesses(pkg, USER_ID)
                !info?.mAppProcessInfoList.isNullOrEmpty()
            } catch (e: Throwable) {
                Log.w(TAG, "query running state of $pkg failed: ${e.message}")
                false
            }
        }

    private fun install(context: Context, body: JSONObject): ApiResult {
        val url = body.optString("url", "")
        val path = body.optString("path", "")
        val packageName = body.optString("package", "")
        val result: InstallResult = try {
            when {
                url.isNotEmpty() -> {
                    val file = Downloader.download(context, url)
                    BlackBoxCore.get().installPackageAsUser(file, USER_ID)
                }
                path.isNotEmpty() -> BlackBoxCore.get().installPackageAsUser(File(path), USER_ID)
                packageName.isNotEmpty() -> BlackBoxCore.get().installPackageAsUser(packageName, USER_ID)
                else -> return ApiResult(400, error("one of url, path or package is required"))
            }
        } catch (e: Throwable) {
            return ApiResult(500, error(e.message ?: e.javaClass.simpleName))
        }
        val installedPackage = result.packageName?.takeIf { it.isNotEmpty() } ?: packageName
        val response = JSONObject()
            .put("success", result.success)
            .put("package", installedPackage)
            .put("message", result.msg ?: "")
        if (result.success && installedPackage.isNotEmpty()) {
            val config = body.optJSONObject("config")
            if (config != null) {
                try {
                    response.put("config", writeConfig(context, installedPackage, config))
                } catch (e: Throwable) {
                    Log.w(TAG, "apply config after install failed: ${e.message}")
                    response.put("config", JSONObject.NULL)
                }
            }
        }
        return ApiResult(200, response)
    }

    private fun launch(body: JSONObject): ApiResult {
        val packageName = body.optString("package", "")
        if (packageName.isEmpty()) {
            return ApiResult(400, error("package is required"))
        }
        val success = try {
            BlackBoxCore.get().launchApk(packageName, USER_ID)
        } catch (e: Throwable) {
            return ApiResult(500, error(e.message ?: e.javaClass.simpleName))
        }
        return ApiResult(200, JSONObject().put("success", success).put("package", packageName))
    }

    /**
     * Stops the given package, or every running virtual app when no package is
     * supplied. The container is single user, so no user id is needed.
     */
    private fun stop(body: JSONObject): ApiResult {
        val packageName = body.optString("package", "")
        val targets = if (packageName.isNotEmpty()) listOf(packageName) else runningPackages()
        val stopped = JSONArray()
        targets.forEach { pkg ->
            try {
                BlackBoxCore.get().stopPackage(pkg, USER_ID)
                stopped.put(pkg)
            } catch (e: Throwable) {
                Log.w(TAG, "stop $pkg failed: ${e.message}")
            }
        }
        return ApiResult(200, JSONObject().put("success", true).put("stopped", stopped))
    }

    private fun uninstall(body: JSONObject): ApiResult {
        val packageName = body.optString("package", "")
        if (packageName.isEmpty()) {
            return ApiResult(400, error("package is required"))
        }
        val context = appContext
        return try {
            // Stop first so a foreground task does not keep a dead proxy activity.
            try {
                BlackBoxCore.get().stopPackage(packageName, USER_ID)
            } catch (e: Throwable) {
                Log.w(TAG, "stop before uninstall failed: ${e.message}")
            }
            BlackBoxCore.get().uninstallPackageAsUser(packageName, USER_ID)
            if (context != null) {
                ConfigOps.clear(context, packageName)
            }
            ApiResult(200, JSONObject().put("success", true).put("package", packageName))
        } catch (e: Throwable) {
            ApiResult(500, error(e.message ?: e.javaClass.simpleName))
        }
    }

    private fun readConfig(context: Context, packageName: String): JSONObject =
        ConfigOps.read(context, packageName)

    private fun writeConfig(
        context: Context,
        packageName: String,
        body: JSONObject
    ): JSONObject = ConfigOps.apply(context, packageName, body)

    private fun parseBody(request: HttpRequest): JSONObject {
        if (request.body.isEmpty()) {
            return JSONObject()
        }
        return try {
            JSONObject(String(request.body, Charsets.UTF_8))
        } catch (e: Throwable) {
            JSONObject()
        }
    }

    private fun error(message: String): JSONObject =
        JSONObject().put("success", false).put("error", message)

    private fun respond(output: OutputStream, code: Int, body: JSONObject) {
        val payload = body.toString().toByteArray(Charsets.UTF_8)
        val header = "HTTP/1.1 $code ${reason(code)}\r\n" +
                "Content-Type: application/json; charset=utf-8\r\n" +
                "Content-Length: ${payload.size}\r\n" +
                "Connection: close\r\n\r\n"
        output.write(header.toByteArray(Charsets.US_ASCII))
        output.write(payload)
        output.flush()
    }

    private fun reason(code: Int): String = when (code) {
        200 -> "OK"
        400 -> "Bad Request"
        401 -> "Unauthorized"
        404 -> "Not Found"
        405 -> "Method Not Allowed"
        else -> "Internal Server Error"
    }

    private class ApiResult(val code: Int, val body: JSONObject)

    private class HttpRequest(
        val method: String,
        val path: String,
        val query: String,
        val headers: Map<String, String>,
        val body: ByteArray
    ) {
        fun queryInt(name: String, defaultValue: Int): Int {
            if (query.isEmpty()) return defaultValue
            return query.split('&').firstOrNull { it.startsWith("$name=") }
                ?.substringAfter('=')
                ?.toIntOrNull()
                ?: defaultValue
        }
    }

    private fun readRequest(input: InputStream): HttpRequest? {
        val head = ByteArrayOutputStream()
        var state = 0
        while (true) {
            val b = input.read()
            if (b < 0) return null
            head.write(b)
            state = when {
                state == 0 && b == 13 -> 1
                state == 1 && b == 10 -> 2
                state == 2 && b == 13 -> 3
                state == 3 && b == 10 -> 4
                else -> 0
            }
            if (state == 4) break
            if (head.size() > 64 * 1024) return null
        }
        val lines = head.toString("UTF-8").split("\r\n")
        val requestLine = lines.firstOrNull()?.split(" ") ?: return null
        if (requestLine.size < 2) return null
        val method = requestLine[0].uppercase()
        val rawPath = requestLine[1]
        val path = rawPath.substringBefore('?')
        val query = rawPath.substringAfter('?', "")
        val headers = HashMap<String, String>()
        var contentLength = 0
        for (i in 1 until lines.size) {
            val line = lines[i]
            val index = line.indexOf(':')
            if (index <= 0) continue
            val name = line.substring(0, index).trim().lowercase()
            val value = line.substring(index + 1).trim()
            headers[name] = value
            if (name == "content-length") {
                contentLength = value.toIntOrNull() ?: 0
            }
        }
        val body = if (contentLength > 0) {
            if (contentLength > 8 * 1024 * 1024) return null
            val buffer = ByteArray(contentLength)
            var read = 0
            while (read < contentLength) {
                val count = input.read(buffer, read, contentLength - read)
                if (count < 0) break
                read += count
            }
            buffer.copyOf(read)
        } else {
            ByteArray(0)
        }
        return HttpRequest(method, path, query, headers, body)
    }
}
