package bu.ac.kr.anyfeeling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import bu.ac.kr.anyfeeling.FirstFragment.FirstFragment
import bu.ac.kr.anyfeeling.SecondFragment.SecondFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val FirstFragment= FirstFragment()
        val SecondFragment = SecondFragment()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_menu)

        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId){
                R.id.first_tab -> replaceFragment(FirstFragment)
                R.id.second_tab -> replaceFragment(SecondFragment)
            }
            true
        }

    }
    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .apply {
                replace(R.id.container,fragment)
                commit()
            }
    }
}