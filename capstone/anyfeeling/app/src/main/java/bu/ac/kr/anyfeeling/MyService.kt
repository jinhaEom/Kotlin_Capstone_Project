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
import android.util.Log
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


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e(TAG, "Action Received = ${intent?.action}")
        // intent가 시스템에 의해 재생성되었을때 null값이므로 Java에서는 null check 필수
        when (intent?.action) {
            Actions.START_FOREGROUND -> {
                Log.e(TAG, "Start Foreground 인텐트를 받음")
                startForegroundService()
            }
            Actions.STOP_FOREGROUND -> {
                Log.e(TAG, "Stop Foreground 인텐트를 받음")
                stopForegroundService()
            }
            Actions.PREV -> Log.e(TAG, "Clicked = 이전")
            Actions.PLAY -> Log.e(TAG, "Clicked = 재생")
            Actions.NEXT -> Log.e(TAG, "Clicked = 다음")
        }
        return START_STICKY
    }

    private fun startForegroundService() {
        val notification = MusicNotification.createNotification(this)
        startForeground(NOTIFICATION_ID, notification)
    }

    private fun stopForegroundService() {
        stopForeground(true)
        stopSelf()
    }

    override fun onBind(intent: Intent?): IBinder? {
        // bound service가 아니므로 null
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Log.e(TAG, "onCreate()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy()")
    }

    companion object {
        const val TAG = "[MusicPlayerService]"
        const val NOTIFICATION_ID = 20
    }


}