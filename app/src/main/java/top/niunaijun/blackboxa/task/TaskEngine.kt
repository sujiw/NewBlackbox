package top.niunaijun.blackboxa.task

import android.content.Context
import android.os.SystemClock
import android.util.Log
import androidx.lifecycle.MutableLiveData
import org.json.JSONObject
import top.niunaijun.blackbox.BlackBoxCore
import top.niunaijun.blackboxa.api.ConfigOps
import top.niunaijun.blackboxa.api.Downloader
import java.io.File
import java.text.SimpleDateFormat
import java.util.ArrayDeque
import java.util.Date
import java.util.Locale
import java.util.UUID
import java.util.concurrent.Callable
import java.util.concurrent.ExecutionException
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

/**
 * Single consumer queue for automation jobs.
 *
 * The REST api only enqueues; this engine runs one job at a time, persists the
 * queue to disk and survives process restarts (queued jobs are resumed,
 * interrupted ones are marked failed).
 */
object TaskEngine {

    private const val TAG = "TaskEngine"
    private const val USER_ID = 0
    private const val MAX_QUEUE = 500
    private const val MAX_HISTORY = 200
    private const val MAX_LOGS = 200
    private const val DEFAULT_TIMEOUT = 120

    private val knownTypes = setOf("install", "config", "launch", "stop", "uninstall", "wait")

    private val lock = Any()
    private val jobs = mutableListOf<TaskJob>()
    private val logs = ArrayDeque<LogEntry>()
    private var logSeq = 0

    private val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.US)

    private val worker: ExecutorService = Executors.newSingleThreadExecutor { runnable ->
        Thread(runnable, "blackbox-task").apply { isDaemon = true }
    }

    private val stepExecutor: ExecutorService = Executors.newCachedThreadPool { runnable ->
        Thread(runnable, "blackbox-task-step").apply { isDaemon = true }
    }

    private var appContext: Context? = null

    @Volatile
    private var cancelRequested = false

    val jobsLiveData = MutableLiveData<List<TaskJob>>()
    val countsLiveData = MutableLiveData(TaskCounts())

    private data class LogEntry(val seq: Int, val text: String)

    // ------------------------------------------------------------------ setup

    fun ensureInit(context: Context) {
        if (appContext != null) {
            return
        }
        synchronized(lock) {
            if (appContext != null) {
                return
            }
            val ctx = context.applicationContext
            appContext = ctx
            jobs.clear()
            jobs.addAll(TaskStore.load(ctx))
            var recovered = false
            jobs.forEach { job ->
                if (job.state == JobState.RUNNING) {
                    job.state = JobState.FAILED
                    job.error = "interrupted by restart"
                    job.finishedAt = System.currentTimeMillis()
                    job.currentStep = -1
                    job.steps.forEach { step ->
                        if (step.state == StepState.RUNNING) {
                            step.state = StepState.SKIPPED
                            step.error = "interrupted by restart"
                        }
                    }
                    recovered = true
                }
            }
            if (recovered) {
                TaskStore.save(ctx, jobs.toList())
            }
        }
        log("engine ready, ${queuedCount()} queued")
        postState()
        drain()
    }

    fun onModeChanged() {
        val paused = appContext?.let { TaskMode.isPaused(it) } ?: false
        if (!paused) {
            drain()
        }
    }

    // ------------------------------------------------------------------- api

    /** Returns null when the job is invalid, otherwise the stored job (or existing one for a known id). */
    fun submit(job: TaskJob): TaskJob? {
        val ctx = appContext ?: return null
        val validation = validate(job)
        if (validation != null) {
            log("rejected job: $validation")
            return null
        }
        synchronized(lock) {
            if (job.id.isNotEmpty()) {
                jobs.firstOrNull { it.id == job.id }?.let { return it }
            }
            if (jobs.count { it.state == JobState.QUEUED || it.state == JobState.RUNNING } >= MAX_QUEUE) {
                return null
            }
            if (job.id.isEmpty()) {
                job.id = UUID.randomUUID().toString().substring(0, 8)
            }
            job.name = job.name.ifEmpty { job.steps.first().type }
            job.state = JobState.QUEUED
            job.createdAt = System.currentTimeMillis()
            job.startedAt = 0
            job.finishedAt = 0
            job.currentStep = -1
            job.error = null
            job.steps.forEach {
                it.state = StepState.PENDING
                it.error = null
                it.costMs = 0
            }
            jobs.add(job)
            TaskStore.save(ctx, jobs.toList())
        }
        log("job ${job.id} queued (${job.steps.size} steps)")
        postState()
        drain()
        return job
    }

    fun validate(job: TaskJob): String? {
        if (job.steps.isEmpty()) {
            return "steps is required"
        }
        if (job.steps.size > 50) {
            return "too many steps (max 50)"
        }
        job.steps.forEachIndexed { index, step ->
            val type = step.type.lowercase()
            if (type !in knownTypes) {
                return "step ${index + 1}: unknown type ${step.type}"
            }
            val needsPackage = type == "config" || type == "launch" || type == "stop" ||
                    type == "uninstall"
            if (needsPackage && step.params.optString("package", "").isEmpty()) {
                return "step ${index + 1} ($type): package is required"
            }
            if (type == "install") {
                val hasSource = step.params.optString("url", "").isNotEmpty() ||
                        step.params.optString("path", "").isNotEmpty() ||
                        step.params.optString("package", "").isNotEmpty()
                if (!hasSource) {
                    return "step ${index + 1} (install): url, path or package is required"
                }
            }
            if (type == "wait" && step.params.optInt("seconds", -1) < 0) {
                return "step ${index + 1} (wait): seconds is required"
            }
        }
        return null
    }

    fun cancel(id: String): Boolean {
        val ctx = appContext ?: return false
        var canceledRunning = false
        synchronized(lock) {
            val job = jobs.firstOrNull { it.id == id } ?: return false
            if (job.state.isFinished) {
                return false
            }
            if (job.state == JobState.RUNNING) {
                cancelRequested = true
                canceledRunning = true
            } else {
                job.state = JobState.CANCELED
                job.error = "canceled"
                job.finishedAt = System.currentTimeMillis()
                job.steps.forEach {
                    if (it.state == StepState.PENDING) {
                        it.state = StepState.CANCELED
                        it.error = "canceled"
                    }
                }
            }
            TaskStore.save(ctx, jobs.toList())
        }
        log("job $id cancel requested")
        postState()
        if (!canceledRunning) {
            drain()
        }
        return true
    }

    fun stopAll(): Int {
        val ctx = appContext ?: return 0
        var count = 0
        synchronized(lock) {
            jobs.forEach { job ->
                when (job.state) {
                    JobState.QUEUED -> {
                        job.state = JobState.CANCELED
                        job.error = "stopped"
                        job.finishedAt = System.currentTimeMillis()
                        job.steps.forEach {
                            if (it.state == StepState.PENDING) {
                                it.state = StepState.CANCELED
                                it.error = "stopped"
                            }
                        }
                        count++
                    }

                    JobState.RUNNING -> {
                        cancelRequested = true
                        count++
                    }

                    else -> Unit
                }
            }
            TaskStore.save(ctx, jobs.toList())
        }
        log("stop all requested ($count)")
        postState()
        return count
    }

    fun clearFinished(state: String? = null): Int {
        val ctx = appContext ?: return 0
        val filter = JobState.parse(state)
        var removed = 0
        synchronized(lock) {
            val iterator = jobs.iterator()
            while (iterator.hasNext()) {
                val job = iterator.next()
                if (!job.state.isFinished) {
                    continue
                }
                if (filter != null && job.state != filter) {
                    continue
                }
                iterator.remove()
                removed++
            }
            if (removed > 0) {
                TaskStore.save(ctx, jobs.toList())
            }
        }
        if (removed > 0) {
            log("cleared $removed finished jobs")
            postState()
        }
        return removed
    }

    fun find(id: String): TaskJob? = synchronized(lock) {
        jobs.firstOrNull { job -> job.id == id }?.let { job ->
            job.copy(steps = job.steps.map { step -> step.copy() }.toMutableList())
        }
    }

    fun snapshot(): List<TaskJob> = synchronized(lock) {
        ordered(jobs.map { it.copy(steps = it.steps.map { s -> s.copy() }.toMutableList()) })
    }

    fun counts(): TaskCounts = synchronized(lock) { countsLocked() }

    fun readLogs(since: Int): Pair<Int, List<String>> = synchronized(lock) {
        val lines = logs.filter { it.seq > since }.map { it.text }
        Pair(logs.lastOrNull()?.seq ?: since, lines)
    }

    // --------------------------------------------------------------- internal

    private fun queuedCount(): Int = synchronized(lock) {
        jobs.count { it.state == JobState.QUEUED }
    }

    private fun countsLocked(): TaskCounts = TaskCounts(
        queued = jobs.count { it.state == JobState.QUEUED },
        running = jobs.count { it.state == JobState.RUNNING },
        succeeded = jobs.count { it.state == JobState.SUCCEEDED },
        failed = jobs.count { it.state == JobState.FAILED }
    )

    private fun ordered(source: List<TaskJob>): List<TaskJob> {
        val running = source.filter { it.state == JobState.RUNNING }
        val queued = source.filter { it.state == JobState.QUEUED }.sortedBy { it.createdAt }
        val finished = source.filter { it.state.isFinished }
            .sortedByDescending { it.finishedAt.coerceAtLeast(it.createdAt) }
        return running + queued + finished
    }

    private fun postState() {
        val snapshot: List<TaskJob>
        val counts: TaskCounts
        synchronized(lock) {
            snapshot = ordered(jobs.map { job ->
                job.copy(steps = job.steps.map { step -> step.copy() }.toMutableList())
            })
            counts = countsLocked()
        }
        jobsLiveData.postValue(snapshot)
        countsLiveData.postValue(counts)
    }

    private fun log(text: String) {
        synchronized(lock) {
            logSeq++
            logs.addLast(LogEntry(logSeq, "${timeFormat.format(Date())} $text"))
            while (logs.size > MAX_LOGS) {
                logs.removeFirst()
            }
        }
        Log.d(TAG, text)
    }

    private fun drain() {
        val ctx = appContext ?: return
        if (TaskMode.isPaused(ctx)) {
            return
        }
        val job = synchronized(lock) {
            if (jobs.any { it.state == JobState.RUNNING }) {
                return
            }
            val next = jobs.firstOrNull { it.state == JobState.QUEUED } ?: return
            next.state = JobState.RUNNING
            next.startedAt = System.currentTimeMillis()
            next.currentStep = -1
            cancelRequested = false
            next
        }
        log("job ${job.id} started")
        postState()
        worker.execute { runJob(job) }
    }

    private fun runJob(job: TaskJob) {
        val ctx = appContext ?: return
        var failure: String? = null
        var canceled = false

        for (index in job.steps.indices) {
            if (cancelRequested) {
                canceled = true
                break
            }
            val step = job.steps[index]
            step.state = StepState.RUNNING
            job.currentStep = index
            postState()
            log("job ${job.id} step ${index + 1}/${job.steps.size} ${step.type}")

            val startedAt = SystemClock.elapsedRealtime()
            val error = try {
                executeStep(step)
            } catch (e: Throwable) {
                e.message ?: e.javaClass.simpleName
            }
            step.costMs = SystemClock.elapsedRealtime() - startedAt

            if (error == null) {
                step.state = StepState.SUCCEEDED
                log("job ${job.id} step ${index + 1} ok (${step.costMs}ms)")
            } else {
                step.error = error
                if (cancelRequested) {
                    step.state = StepState.CANCELED
                    canceled = true
                } else {
                    step.state = StepState.FAILED
                    failure = error
                    log("job ${job.id} step ${index + 1} failed: $error")
                }
                if (job.stopOnError || canceled) {
                    break
                }
            }
            postState()
        }

        synchronized(lock) {
            job.finishedAt = System.currentTimeMillis()
            job.currentStep = -1
            when {
                canceled -> {
                    job.state = JobState.CANCELED
                    job.error = "canceled"
                }

                failure == null -> job.state = JobState.SUCCEEDED

                else -> {
                    job.state = JobState.FAILED
                    job.error = failure
                }
            }
            job.steps.forEach { step ->
                if (step.state == StepState.PENDING || step.state == StepState.RUNNING) {
                    step.state = StepState.SKIPPED
                    step.error = step.error ?: "skipped"
                }
            }
            cancelRequested = false
            trimHistoryLocked()
            TaskStore.save(ctx, jobs.toList())
        }
        log("job ${job.id} ${job.state.name.lowercase()}")
        postState()
        drain()
    }

    private fun trimHistoryLocked() {
        val finished = jobs.filter { it.state.isFinished }
        if (finished.size <= MAX_HISTORY) {
            return
        }
        val removable = finished.sortedBy { it.finishedAt }.take(finished.size - MAX_HISTORY).toSet()
        jobs.removeAll(removable)
    }

    private fun executeStep(step: TaskStep): String? {
        val timeout = step.timeoutSeconds(defaultTimeout(step.type))
        val future = stepExecutor.submit(Callable { doStep(step) })
        return try {
            future.get(timeout.toLong(), TimeUnit.SECONDS)
        } catch (e: TimeoutException) {
            future.cancel(true)
            "step timeout after ${timeout}s"
        } catch (e: ExecutionException) {
            e.cause?.message ?: e.message ?: "step failed"
        } catch (e: Throwable) {
            e.message ?: "step failed"
        }
    }

    private fun defaultTimeout(type: String): Int = when (type.lowercase()) {
        "install" -> 600
        "wait" -> 3600
        else -> DEFAULT_TIMEOUT
    }

    private fun doStep(step: TaskStep): String? {
        val ctx = appContext ?: return "engine not initialized"
        return when (step.type.lowercase()) {
            "install" -> doInstall(ctx, step)
            "config" -> doConfig(ctx, step)
            "launch" -> doLaunch(step)
            "stop" -> doStop(step)
            "uninstall" -> doUninstall(ctx, step)
            "wait" -> doWait(step)
            else -> "unknown step type: ${step.type}"
        }
    }

    private fun doInstall(ctx: Context, step: TaskStep): String? {
        val url = step.params.optString("url", "")
        val path = step.params.optString("path", "")
        val packageName = step.params.optString("package", "")
        return try {
            val result = when {
                url.isNotEmpty() -> {
                    val file = Downloader.download(ctx, url)
                    BlackBoxCore.get().installPackageAsUser(file, USER_ID)
                }

                path.isNotEmpty() -> BlackBoxCore.get().installPackageAsUser(File(path), USER_ID)
                else -> BlackBoxCore.get().installPackageAsUser(packageName, USER_ID)
            }
            if (!result.success) {
                return result.msg?.ifEmpty { "install failed" } ?: "install failed"
            }
            val config = step.params.optJSONObject("config")
            if (config != null) {
                val installed = result.packageName?.takeIf { it.isNotEmpty() } ?: packageName
                if (installed.isNotEmpty()) {
                    ConfigOps.apply(ctx, installed, config)
                }
            }
            null
        } catch (e: Throwable) {
            e.message ?: "install failed"
        }
    }

    private fun doConfig(ctx: Context, step: TaskStep): String? {
        val packageName = step.packageName()
        val body = JSONObject(step.params.toString())
        body.remove("type")
        body.remove("timeout")
        body.remove("package")
        if (!body.has("proxy") && !body.has("gaid") && !body.has("device")) {
            return "config requires proxy, gaid or device"
        }
        return try {
            ConfigOps.apply(ctx, packageName, body)
            null
        } catch (e: Throwable) {
            e.message ?: "config failed"
        }
    }

    private fun doLaunch(step: TaskStep): String? {
        val packageName = step.packageName()
        requireInstalled(packageName)?.let { return it }
        return try {
            if (BlackBoxCore.get().launchApk(packageName, USER_ID)) {
                null
            } else {
                "launch returned false"
            }
        } catch (e: Throwable) {
            e.message ?: "launch failed"
        }
    }

    private fun doStop(step: TaskStep): String? {
        val packageName = step.packageName()
        return try {
            BlackBoxCore.get().stopPackage(packageName, USER_ID)
            null
        } catch (e: Throwable) {
            e.message ?: "stop failed"
        }
    }

    private fun doUninstall(ctx: Context, step: TaskStep): String? {
        val packageName = step.packageName()
        requireInstalled(packageName)?.let { return it }
        return try {
            try {
                BlackBoxCore.get().stopPackage(packageName, USER_ID)
            } catch (_: Throwable) {
            }
            BlackBoxCore.get().uninstallPackageAsUser(packageName, USER_ID)
            ConfigOps.clear(ctx, packageName)
            null
        } catch (e: Throwable) {
            e.message ?: "uninstall failed"
        }
    }

    private fun doWait(step: TaskStep): String? {
        val seconds = step.params.optInt("seconds", 0).coerceIn(0, 3600)
        val deadline = SystemClock.elapsedRealtime() + seconds * 1000L
        while (SystemClock.elapsedRealtime() < deadline) {
            if (cancelRequested) {
                return "canceled"
            }
            val remaining = deadline - SystemClock.elapsedRealtime()
            try {
                Thread.sleep(remaining.coerceAtMost(200))
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
                return "canceled"
            }
        }
        return null
    }

    private fun requireInstalled(packageName: String): String? {
        if (packageName.isEmpty()) {
            return "package is required"
        }
        return if (BlackBoxCore.get().isInstalled(packageName, USER_ID)) {
            null
        } else {
            "package not installed: $packageName"
        }
    }
}
