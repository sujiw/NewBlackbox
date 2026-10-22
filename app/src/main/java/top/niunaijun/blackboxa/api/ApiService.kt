package top.niunaijun.blackboxa.api

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import top.niunaijun.blackboxa.R

/**
 * Keeps [ApiServer] alive while the automation api is enabled.
 */
class ApiService : Service() {

    companion object {

        private const val CHANNEL_ID = "blackbox_api"
        private const val NOTIFICATION_ID = 1001

        private const val ACTION_START = "top.niunaijun.blackboxa.api.START"
        private const val ACTION_STOP = "top.niunaijun.blackboxa.api.STOP"

        fun start(context: Context) {
            val intent = Intent(context, ApiService::class.java).setAction(ACTION_START)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(intent)
            } else {
                context.startService(intent)
            }
        }

        fun stop(context: Context) {
            context.stopService(Intent(context, ApiService::class.java))
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent?.action == ACTION_STOP) {
            stopSelf()
            return START_NOT_STICKY
        }
        createChannel()
        try {
            startForeground(NOTIFICATION_ID, buildNotification())
        } catch (e: Throwable) {
            Log.e("ApiService", "startForeground failed: ${e.message}")
        }
        ApiServer.start(this)
        return START_STICKY
    }

    override fun onDestroy() {
        ApiServer.stop()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun createChannel() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return
        }
        val manager = getSystemService(NotificationManager::class.java) ?: return
        if (manager.getNotificationChannel(CHANNEL_ID) != null) {
            return
        }
        val channel = NotificationChannel(
            CHANNEL_ID,
            getString(R.string.api_service),
            NotificationManager.IMPORTANCE_LOW
        )
        channel.setShowBadge(false)
        manager.createNotificationChannel(channel)
    }

    private fun buildNotification(): Notification {
        val builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder(this, CHANNEL_ID)
        } else {
            @Suppress("DEPRECATION")
            Notification.Builder(this)
        }
        return builder
            .setSmallIcon(android.R.drawable.stat_notify_sync)
            .setContentTitle(getString(R.string.api_service))
            .setContentText(getString(R.string.api_running_at, ApiSettings.port(this)))
            .setOngoing(true)
            .build()
    }
}
