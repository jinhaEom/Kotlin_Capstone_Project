package bu.ac.kr.anyfeeling


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue

import androidx.fragment.app.Fragment
import bu.ac.kr.anyfeeling.firstFragment.FirstFragment
import bu.ac.kr.anyfeeling.homeFragment.HomeFragment
import bu.ac.kr.anyfeeling.secondFragment.SecondFragment
import bu.ac.kr.anyfeeling.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private val binding by lazy{ ActivityMainBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        /*val FirstFragment= FirstFragment()
        val SecondFragment = SecondFragment()
        val HomeFragment = HomeFragment()*/
        supportFragmentManager.beginTransaction().add(R.id.container,HomeFragment()).commitAllowingStateLoss()


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
                commitAllowingStateLoss()
            }
    }



}

fun Float.dpToPx(context: HomeFragment): Float =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,this,context.resources.displayMetrics)