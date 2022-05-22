package bu.ac.kr.anyfeeling.Notification

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.MediaSession2Service
import androidx.core.app.NotificationCompat
import bu.ac.kr.anyfeeling.MainActivity
import bu.ac.kr.anyfeeling.R
import androidx.media.app.NotificationCompat as MediaNotificationCompat

class MyNotificationBuilder {

    fun build(context: Context, isRunning: Boolean, channelId: String): Notification {
        return NotificationCompat.Builder(context, channelId)
            .setContentTitle("Notification Study")
            .setContentText("Application is active.")
            .setSmallIcon(R.drawable.appicon)
            .setContentIntent(createPendingIntent(context))
            .setTicker("Application is active")
            .addAction(createMyAction(context, isRunning))
            .setStyle(
                MediaNotificationCompat.MediaStyle()
                    .setShowActionsInCompactView(0)
            )
            .build()
    }

    private fun createPendingIntent(context: Context): PendingIntent {
        return Intent(context, MainActivity::class.java).let { notificationIntent ->
            PendingIntent.getActivity(context, 0, notificationIntent, 0)
        }
    }

    private fun createMyAction(context: Context, isRunning: Boolean): NotificationCompat.Action {
        return if (isRunning) {
            val stopIntent = Intent().apply {
                action = MyReceiver.ACTION_STOP
            }
            NotificationCompat.Action(
                R.drawable.ic_baseline_pause_24,
                "Run",
                PendingIntent.getBroadcast(context, 0, stopIntent, 0)
            )
        } else {
            val runIntent = Intent().apply {
                action = MyReceiver.ACTION_RUN
            }
            NotificationCompat.Action(
                R.drawable.ic_baseline_play_arrow_24,
                "Stop",
                PendingIntent.getBroadcast(context, 0, runIntent, 0)
            )
        }
    }
}