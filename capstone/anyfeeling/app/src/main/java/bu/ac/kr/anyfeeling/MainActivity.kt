package bu.ac.kr.anyfeeling


import android.annotation.SuppressLint
import android.app.Activity
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
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
import androidx.core.content.ContextCompat


import androidx.fragment.app.Fragment
import androidx.media.session.MediaButtonReceiver
import bu.ac.kr.anyfeeling.adapter.PlayListAdapter
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        /*val builder = NotificationCompat.Builder(this,"MY_CHANNEL")
            .setSmallIcon(R.drawable.appicon)
            .setContentTitle("Any Feeling")
            .setContentText("앱이 실행중입니다.")

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
        //O 는 Oreo 버전의 약자로 Oreo버전 이후에는 channel을 설정해줘야함.
            val channel_id = "MY_CHANNEL"
            val channel_name= "채널 이름"
            val descriptionText="설명글"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channel_id,channel_name,importance).apply {
                description = descriptionText
            }

            val notificationManager : NotificationManager = getSystemService(
                NOTIFICATION_SERVICE)as NotificationManager
            notificationManager.createNotificationChannel(channel)
            notificationManager.notify(1002,builder.build())

        }*/
        val intent = Intent(this,MyService::class.java)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            this.startForegroundService(
                intent
            )
        }else{
            this.startService(intent)
        }











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








