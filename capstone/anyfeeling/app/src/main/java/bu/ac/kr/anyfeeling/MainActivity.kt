package bu.ac.kr.anyfeeling


import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import bu.ac.kr.anyfeeling.Notification.MusicPlayerService
import bu.ac.kr.anyfeeling.first.FirstFragment
import bu.ac.kr.anyfeeling.homeFragment.HomeFragment
import bu.ac.kr.anyfeeling.secondFragment.SecondFragment
import bu.ac.kr.anyfeeling.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    /*private val CHANNEL_ID = "testChannel01"   // Channel for notification
    private var notificationManager: NotificationManager? = null*/

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        /*createNotificationChannel(CHANNEL_ID, "channel", "channels description")

        displayNotification()*/
        val intent = Intent(this@MainActivity, MusicPlayerService::class.java)

        startForegroundService(intent)





        makeStatusBarTransparent(context = HomeFragment())

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
    /*private fun displayNotification() {
        val notificationId = 45

        val notification = Notification.Builder(applicationContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.appicon)
            .setContentTitle("Any Feeling")
            .setContentText("앱이 실행 중입니다.")
            .build()

        notificationManager?.notify(notificationId, notification)
    }
    private fun createNotificationChannel(channelId: String, name: String, channelDescription: String) {
        //notification 채널 생성
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT // 중요도 지정
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = channelDescription
            }
            // Register the channel with the system
            notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager?.createNotificationChannel(channel)
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




fun Activity.makeStatusBarTransparent(context: HomeFragment) {
    window.apply {
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        statusBarColor = Color.TRANSPARENT
    }


}


