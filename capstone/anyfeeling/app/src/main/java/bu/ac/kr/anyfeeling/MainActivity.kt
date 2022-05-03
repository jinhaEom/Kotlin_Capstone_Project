package bu.ac.kr.anyfeeling


import android.annotation.SuppressLint
import android.app.Activity
import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.drm.DrmStore
import android.graphics.Color
import android.media.MediaMetadata
import android.media.MediaSession2
import android.media.session.MediaSession
import android.media.session.PlaybackState
import android.os.Build
import android.os.Build.VERSION_CODES.O
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.System.getString
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import android.util.TypedValue
import android.view.View
import android.view.WindowManager
import androidx.core.app.NotificationCompat


import androidx.fragment.app.Fragment
import androidx.media.session.MediaButtonReceiver
import bu.ac.kr.anyfeeling.first.FirstFragment
import bu.ac.kr.anyfeeling.homeFragment.HomeFragment
import bu.ac.kr.anyfeeling.secondFragment.SecondFragment
import bu.ac.kr.anyfeeling.databinding.ActivityMainBinding
import com.bumptech.glide.Priority
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.util.NotificationUtil.createNotificationChannel
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var playpause : Notification.Action
    private lateinit var mediaSession: MediaSessionCompat
    private lateinit var exoPlayer : SimpleExoPlayer
    var btnActions: Array<Notification.Action?> = arrayOfNulls<Notification.Action>(3)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        gc






        makeStatusBarTransparent(context = HomeFragment())
        /*val FirstFragment= FirstFragment()
    val SecondFragment = SecondFragment()
    val HomeFragment = HomeFragment()*/
        supportFragmentManager.beginTransaction().add(R.id.container, HomeFragment())
            .commitAllowingStateLoss()


        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_menu)

        bottomNavigationView.setOnItemSelectedListener {
            replaceFragment(
                when (it.itemId) {
                    R.id.first_tab -> FirstFragment()
                    R.id.second_tab -> SecondFragment()
                    else -> HomeFragment()

                }
            )

            true
        }
    }

    /*private fun fragmenthome3(binding: FragmentHomeBinding){
        binding.appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            val topPadding = 300f.spToPx(context = HomeFragment())
            val realAlphaScrollHeight = appBarLayout.measuredHeight - appBarLayout.totalScrollRange
            val abstractOffset = abs(verticalOffset)

            val realAlphaVerticalOffset = if (abstractOffset - topPadding < 0) 0f else abstractOffset - topPadding

            if (abstractOffset < topPadding) {
                binding.toolbarBackgroundView.alpha = 0f  // 액션바 투명
                return@OnOffsetChangedListener
            }
            val percentage = realAlphaVerticalOffset / realAlphaScrollHeight
            binding.toolbarBackgroundView.alpha = 1 - (if (1 - percentage * 2 < 0) 0f else 1 - percentage * 2)
        }

        )
        with(binding){  //ActionBar 커스터마이징
            toolbar.navigationIcon = null
            toolbar.setContentInsetsAbsolute(0,0)
            setSupportActionBar(binding.toolbar)
            supportActionBar?.let{
                it.setHomeButtonEnabled(false)
                it.setDisplayHomeAsUpEnabled(false)
                it.setDisplayShowHomeEnabled(false)
            }

        }
    }*/


    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .apply {
                replace(R.id.container, fragment)
                commitAllowingStateLoss()
            }
    }
    @SuppressLint("WrongConstant")
    private fun getNotification(playbackState: Int):NotificationCompat.Builder{

        val controller = mediaSession.controller
        val mediaMetadata = controller.metadata
        val description = mediaMetadata.description
        val CHANNEL_ID = "AnyFeelingNoti"



        if(playbackState == PlaybackStateCompat.STATE_PLAYING){
            addAction(
                NotificationCompat(
                    R.drawable.ic_baseline_pause_24,
                    getString("미미"),
                    MediaButtonReceiver.buildMediaButtonPendingIntent(
                        applicationContext, PlaybackStateCompat.ACTION_PLAY_PAUSE
                    )
                )
            )
        }else{

        }
        val previous =
            Notification.Action.Builder(
                R.drawable.ic_baseline_skip_previous_24,
                getString(R.string.app_name),
                MediaButtonReceiver.buildMediaButtonPendingIntent(
                    this, PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS
                )
            ).build()
        val next =
            Notification.Action.Builder(
                R.drawable.ic_baseline_skip_next_24,
                getString(R.string.app_name),
                MediaButtonReceiver.buildMediaButtonPendingIntent(
                    this, PlaybackStateCompat.ACTION_SKIP_TO_NEXT
                )
            ).build()
        if(playbackState == PlaybackStateCompat.STATE_PLAYING){
            playpause =
                Notification.Action.Builder(
                    R.drawable.ic_baseline_play_arrow_24,
                    getString(R.string.app_name),
                    MediaButtonReceiver.buildMediaButtonPendingIntent(
                        this,PlaybackStateCompat.ACTION_PLAY_PAUSE
                    )
                ).build()
        }else{
            playpause =
                Notification.Action.Builder(
                    R.drawable.ic_baseline_pause_24,
                    getString(R.string.app_name),
                    MediaButtonReceiver.buildMediaButtonPendingIntent(
                        this,PlaybackStateCompat.ACTION_PLAY_PAUSE
                    )
                ).build()
        }


    }
}


fun Float.spToPx(context: Context): Float =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, context.resources.displayMetrics)

fun Activity.makeStatusBarTransparent(context: HomeFragment) {
    window.apply {
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        statusBarColor = Color.TRANSPARENT
    }
}








