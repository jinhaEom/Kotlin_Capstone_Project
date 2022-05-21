package bu.ac.kr.anyfeeling

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import bu.ac.kr.anyfeeling.service.MusicModel
import bu.ac.kr.anyfeeling.service.MusicService.NotificationActionService

class MediaNotificationManager {
    var CHANNEL_ID = "channel1"

    val ACTION_PREVIOUS = "action_previous"
    val ACTION_PLAY = "action_play"
    val ACTION_NEXT = "action_next"

    lateinit var notification: Notification

    fun createNotification(context: Context, track:MusicModel, playButton:Int){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val notificationManagerCompat = NotificationManagerCompat.from(context)
            val mediaSessionCompat = MediaSessionCompat(context, "Player service")
            mediaSessionCompat.setMetadata(MediaMetadataCompat.Builder()
                .putString(MediaMetadataCompat.METADATA_KEY_TITLE,track.track)
                .putString(MediaMetadataCompat.METADATA_KEY_ARTIST,track.artist)
                .putLong(MediaMetadataCompat.METADATA_KEY_DURATION,3L)
                .build())
//            if (track.audioDuration ==null){
//                Toast.makeText(context, "null", Toast.LENGTH_SHORT).show()
//            }else{
//                Toast.makeText(context, track.audioDuration, Toast.LENGTH_SHORT).show()
//            }
//            mediaSessionCompat.setPlaybackState(PlaybackStateCompat.Builder()
//                    .setState(PlaybackStateCompat.STATE_PLAYING,currentPosition,speed)
//                    .setActions(PlaybackStateCompat.ACTION_SEEK_TO)
//                    .build())

            val icon = BitmapFactory.decodeResource(context.resources, R.drawable.appicon)

            val pendingIntentPrevious: PendingIntent?
            val drw_previous: Int?

            val intentPrevious = Intent(context, NotificationActionService::class.java)
                .setAction(ACTION_PREVIOUS)
            pendingIntentPrevious = PendingIntent.getBroadcast(context,
                0,
                intentPrevious,
                PendingIntent.FLAG_UPDATE_CURRENT)
            drw_previous = R.drawable.ic_baseline_skip_previous_24

            val intentPLay = Intent(context,NotificationActionService::class.java)
                .setAction(ACTION_PLAY)
            val pendingIntentPlay = PendingIntent.getBroadcast(context,
                0,
                intentPLay,
                PendingIntent.FLAG_UPDATE_CURRENT)


            val pendingIntentNext: PendingIntent?
            val drw_next: Int?

            val intentNext = Intent(context,NotificationActionService::class.java)
                .setAction(ACTION_NEXT)
            pendingIntentNext = PendingIntent.getBroadcast(context,
                0,
                intentNext,
                PendingIntent.FLAG_UPDATE_CURRENT)
            drw_next = R.drawable.ic_baseline_skip_next_24


            //create notification
            notification = NotificationCompat.Builder(context,CHANNEL_ID)
                .setSmallIcon(R.drawable.appicon)
                .setContentTitle(track.track)
                .setContentText(track.artist)
                .setLargeIcon(icon)
                .setOnlyAlertOnce(true)//show only first time
                .setShowWhen(false)

                //.setOngoing(true)  //making non-removable notification
                .addAction(drw_previous,"Previous",pendingIntentPrevious)
                .addAction(playButton,"Play",pendingIntentPlay)
                .addAction(drw_next,"Next",pendingIntentNext)
                .setProgress(100,20,false)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setStyle(androidx.media.app.NotificationCompat.MediaStyle()
                    .setShowActionsInCompactView(0,1,2)
                    .setMediaSession(mediaSessionCompat.sessionToken))
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build()

            notificationManagerCompat.notify(1,notification)
        }
    }
}