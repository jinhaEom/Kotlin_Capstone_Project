package bu.ac.kr.anyfeeling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import bu.ac.kr.anyfeeling.fragment.OneFragment
import bu.ac.kr.anyfeeling.fragment.TwoFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val menuBar = findViewById<Button>(R.id.menuBar)
        val settingBar = findViewById<Button>(R.id.settingBar)

        menuBar.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.FrameLayout, OneFragment())
                .commit()
        }
        settingBar.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.FrameLayout, TwoFragment())
                .commit()
        }
    }
}