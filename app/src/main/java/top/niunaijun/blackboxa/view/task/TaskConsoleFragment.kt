package top.niunaijun.blackboxa.view.task

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import top.niunaijun.blackboxa.R
import top.niunaijun.blackboxa.api.ApiSettings
import top.niunaijun.blackboxa.api.NetworkUtils
import top.niunaijun.blackboxa.databinding.FragmentTaskConsoleBinding
import top.niunaijun.blackboxa.task.TaskCounts
import top.niunaijun.blackboxa.task.TaskEngine
import top.niunaijun.blackboxa.task.TaskJob
import top.niunaijun.blackboxa.task.TaskMode
import top.niunaijun.blackboxa.util.inflate
import top.niunaijun.blackboxa.util.toast
import androidx.fragment.app.Fragment

/**
 * Console shown while task mode is enabled: counters, queue and controls.
 */
class TaskConsoleFragment : Fragment() {

    companion object {
        private const val TAG = "TaskConsole"
    }

    private val viewBinding: FragmentTaskConsoleBinding by inflate()

    private lateinit var adapter: TaskAdapter

    override fun onCreateView(
        inflater: android.view.LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        try {
            TaskEngine.ensureInit(requireContext())

            adapter = TaskAdapter { job -> TaskEngine.cancel(job.id) }
            viewBinding.taskRecycler.layoutManager = LinearLayoutManager(requireContext())
            viewBinding.taskRecycler.adapter = adapter

            viewBinding.apiHint.text = getString(
                R.string.task_api_hint,
                NetworkUtils.localAddress(),
                ApiSettings.port(requireContext())
            )

            viewBinding.pauseButton.setOnClickListener {
                val paused = !TaskMode.isPaused(requireContext())
                TaskMode.setPaused(requireContext(), paused)
            }

            viewBinding.stopAllButton.setOnClickListener {
                val count = TaskEngine.stopAll()
                requireContext().toast(getString(R.string.task_stopped_count, count))
            }

            viewBinding.clearButton.setOnClickListener {
                val removed = TaskEngine.clearFinished()
                if (removed > 0) {
                    requireContext().toast(getString(R.string.task_cleared_count, removed))
                }
            }

            observe()
        } catch (e: Exception) {
            Log.e(TAG, "onCreateView failed: ${e.message}")
        }
        return viewBinding.root
    }

    private fun observe() {
        TaskEngine.jobsLiveData.observe(viewLifecycleOwner) { render(it) }
        TaskEngine.countsLiveData.observe(viewLifecycleOwner) { renderCounts(it) }
        TaskMode.paused.observe(viewLifecycleOwner) { paused ->
            viewBinding.pauseButton.setText(
                if (paused) R.string.task_resume else R.string.task_pause
            )
        }
    }

    private fun render(jobs: List<TaskJob>) {
        try {
            adapter.submit(jobs)
            val empty = jobs.isEmpty()
            viewBinding.emptyHint.visibility = if (empty) View.VISIBLE else View.GONE
            viewBinding.taskRecycler.visibility = if (empty) View.GONE else View.VISIBLE
        } catch (e: Exception) {
            Log.e(TAG, "render failed: ${e.message}")
        }
    }

    private fun renderCounts(counts: TaskCounts) {
        viewBinding.countQueued.text = counts.queued.toString()
        viewBinding.countRunning.text = counts.running.toString()
        viewBinding.countSucceeded.text = counts.succeeded.toString()
        viewBinding.countFailed.text = counts.failed.toString()
    }
}
