package bu.ac.kr.anyfeeling

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.IBinder
import android.widget.Adapter
import androidx.core.app.NotificationCompat
import bu.ac.kr.anyfeeling.adapter.PlayListAdapter
import bu.ac.kr.anyfeeling.service.MusicDto
import bu.ac.kr.anyfeeling.service.MusicEntity
import bu.ac.kr.anyfeeling.service.MusicModel
import bu.ac.kr.anyfeeling.service.MusicService.MusicService
import com.google.android.exoplayer2.Player
import retrofit2.http.Url
import java.net.URI
import java.net.URL


class MyService : Service() {

    private lateinit var mp: MediaPlayer

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onCreate() {
        if (Build.VERSION.SDK_INT >= 26) {
            val CHANNEL_ID = "my_channel_01"
            val channel = NotificationChannel(CHANNEL_ID,
                "Channel human readable title", NotificationManager.IMPORTANCE_DEFAULT)

            (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(channel)

            //foreground 설정을 위해선 notification이 필요하다
            val notification = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("")
                .setContentText("").build()

            startForeground(111, notification)

            mp.start()

        }

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mp.start()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        mp.stop()
    }
}