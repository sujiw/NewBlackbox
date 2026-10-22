package top.niunaijun.blackboxa.view.task

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import top.niunaijun.blackboxa.databinding.ItemTaskJobBinding
import top.niunaijun.blackboxa.task.JobState
import top.niunaijun.blackboxa.task.TaskJob
import top.niunaijun.blackboxa.task.TaskStep

/**
 * Renders the automation queue: one row per job with state dot, progress and
 * the current/failed step.
 */
class TaskAdapter(
    private val onCancel: (TaskJob) -> Unit
) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    private val items = mutableListOf<TaskJob>()

    fun submit(jobs: List<TaskJob>) {
        items.clear()
        items.addAll(jobs)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTaskJobBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: ItemTaskJobBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(job: TaskJob) {
            binding.jobName.text = job.name.ifEmpty { job.id }
            binding.jobState.text = job.state.name
            binding.jobState.setTextColor(stateColor(job.state))
            binding.stateDot.backgroundTintList = ColorStateList.valueOf(stateColor(job.state))

            binding.jobDetail.text = detailOf(job)

            val cancellable = job.state == JobState.QUEUED || job.state == JobState.RUNNING
            binding.cancelButton.visibility = if (cancellable) View.VISIBLE else View.GONE
            binding.cancelButton.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onCancel(items[position])
                }
            }
        }

        private fun detailOf(job: TaskJob): String {
            val total = job.steps.size
            val done = job.progress()
            if (job.error != null) {
                return "$done/$total · ${job.error}"
            }
            val running = job.runningStep()
            if (running != null) {
                return "$done/$total · ${running.type} ${running.packageName()}".trim()
            }
            val last = lastDoneStep(job)
            return if (last != null) {
                "$done/$total · ${last.type} ${last.packageName()}".trim()
            } else {
                "$done/$total"
            }
        }

        private fun lastDoneStep(job: TaskJob): TaskStep? =
            job.steps.lastOrNull { it.state == top.niunaijun.blackboxa.task.StepState.SUCCEEDED }

        private fun stateColor(state: JobState): Int = when (state) {
            JobState.RUNNING -> Color.parseColor("#FF9800")
            JobState.QUEUED -> Color.parseColor("#9E9E9E")
            JobState.SUCCEEDED -> Color.parseColor("#4CAF50")
            JobState.FAILED -> Color.parseColor("#F44336")
            JobState.CANCELED -> Color.parseColor("#607D8B")
            JobState.SKIPPED -> Color.parseColor("#795548")
        }
    }
}
