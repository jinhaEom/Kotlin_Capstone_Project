package bu.ac.kr.anyfeeling

import android.app.Activity
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import bu.ac.kr.anyfeeling.FirstFragment.FirstFragment
import bu.ac.kr.anyfeeling.HomeFragment.HomeFragment
import bu.ac.kr.anyfeeling.SecondFragment.SecondFragment
import bu.ac.kr.anyfeeling.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    val binding by lazy{ ActivityMainBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        val FirstFragment= FirstFragment()
        val SecondFragment = SecondFragment()
        val HomeFragment = HomeFragment()
        supportFragmentManager.beginTransaction().add(R.id.container,HomeFragment()).commit()



        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_menu)





        bottomNavigationView.setOnItemSelectedListener {
            replaceFragment(
                when (it.itemId){
                R.id.first_tab -> FirstFragment()
                    R.id.second_tab -> SecondFragment()
                    else -> HomeFragment()

            }
            )

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

fun Activity.makeStatusBarTransparent() {
    window.apply {
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        statusBarColor = Color.TRANSPARENT
    }
}
