package top.niunaijun.blackboxa.task

import org.json.JSONArray
import org.json.JSONObject

enum class JobState {
    QUEUED,
    RUNNING,
    SUCCEEDED,
    FAILED,
    CANCELED,
    SKIPPED;

    val isFinished: Boolean
        get() = this == SUCCEEDED || this == FAILED || this == CANCELED || this == SKIPPED

    companion object {
        fun parse(value: String?): JobState? =
            entries.firstOrNull { it.name.equals(value, ignoreCase = true) }
    }
}

enum class StepState {
    PENDING,
    RUNNING,
    SUCCEEDED,
    FAILED,
    SKIPPED,
    CANCELED;

    companion object {
        fun parse(value: String?): StepState =
            entries.firstOrNull { it.name.equals(value, ignoreCase = true) } ?: PENDING
    }
}

/** A single action inside a job, [params] keeps the raw json for forward compatibility. */
data class TaskStep(
    var type: String = "",
    var params: JSONObject = JSONObject(),
    var state: StepState = StepState.PENDING,
    var error: String? = null,
    var costMs: Long = 0
) {
    fun packageName(): String = params.optString("package", "")

    fun timeoutSeconds(default: Int): Int {
        val value = params.optInt("timeout", 0)
        return if (value in 1..3600) value else default
    }

    fun toJson(): JSONObject {
        val json = JSONObject(params.toString())
        json.put("type", type)
        json.put("state", state.name)
        json.put("costMs", costMs)
        if (error != null) {
            json.put("error", error)
        }
        return json
    }

    companion object {
        fun fromJson(json: JSONObject): TaskStep = TaskStep(
            type = json.optString("type", ""),
            params = JSONObject(json.toString()),
            state = StepState.parse(json.optString("state", null)),
            error = json.optString("error", "").ifEmpty { null },
            costMs = json.optLong("costMs", 0)
        )
    }
}

/** A queued unit of work: an ordered list of steps executed sequentially. */
data class TaskJob(
    var id: String = "",
    var name: String = "",
    var stopOnError: Boolean = true,
    var state: JobState = JobState.QUEUED,
    var steps: MutableList<TaskStep> = mutableListOf(),
    var createdAt: Long = 0,
    var startedAt: Long = 0,
    var finishedAt: Long = 0,
    var error: String? = null,
    var currentStep: Int = -1
) {
    fun progress(): Int = steps.count { it.state == StepState.SUCCEEDED }

    fun runningStep(): TaskStep? =
        steps.getOrNull(currentStep)?.takeIf { it.state == StepState.RUNNING }

    fun toJson(): JSONObject {
        val stepArray = JSONArray()
        steps.forEach { stepArray.put(it.toJson()) }
        return JSONObject()
            .put("id", id)
            .put("name", name)
            .put("stopOnError", stopOnError)
            .put("state", state.name)
            .put("steps", stepArray)
            .put("createdAt", createdAt)
            .put("startedAt", startedAt)
            .put("finishedAt", finishedAt)
            .put("currentStep", currentStep)
            .apply { if (error != null) put("error", error) }
    }

    companion object {
        fun fromJson(json: JSONObject): TaskJob {
            val steps = mutableListOf<TaskStep>()
            val array = json.optJSONArray("steps")
            if (array != null) {
                for (i in 0 until array.length()) {
                    val item = array.optJSONObject(i) ?: continue
                    steps.add(TaskStep.fromJson(item))
                }
            }
            return TaskJob(
                id = json.optString("id", ""),
                name = json.optString("name", ""),
                stopOnError = json.optBoolean("stopOnError", true),
                state = JobState.parse(json.optString("state", null)) ?: JobState.QUEUED,
                steps = steps,
                createdAt = json.optLong("createdAt", 0),
                startedAt = json.optLong("startedAt", 0),
                finishedAt = json.optLong("finishedAt", 0),
                error = json.optString("error", "").ifEmpty { null },
                currentStep = json.optInt("currentStep", -1)
            )
        }
    }
}

/** Aggregated counters for the task console. */
data class TaskCounts(
    val queued: Int = 0,
    val running: Int = 0,
    val succeeded: Int = 0,
    val failed: Int = 0
)
