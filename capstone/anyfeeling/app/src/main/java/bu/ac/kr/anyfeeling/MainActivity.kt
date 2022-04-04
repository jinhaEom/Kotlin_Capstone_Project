package bu.ac.kr.anyfeeling


import android.app.Activity
import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.WindowManager


import androidx.fragment.app.Fragment
import bu.ac.kr.anyfeeling.firstFragment.FirstFragment
import bu.ac.kr.anyfeeling.homeFragment.HomeFragment
import bu.ac.kr.anyfeeling.secondFragment.SecondFragment
import bu.ac.kr.anyfeeling.databinding.ActivityMainBinding
import bu.ac.kr.anyfeeling.databinding.FragmentHomeBinding
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.math.abs


class MainActivity : AppCompatActivity() {

    private val binding by lazy{ ActivityMainBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


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


        private fun replaceFragment(fragment: Fragment) {
            supportFragmentManager.beginTransaction()
                .apply {
                    replace(R.id.container, fragment)
                    commitAllowingStateLoss()
                }
        }






}


fun Float.dpToPx(context: HomeFragment): Float =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,this,context.resources.displayMetrics)

fun Activity.makeStatusBarTransparent(context: HomeFragment){
    window.apply {
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        statusBarColor = Color.TRANSPARENT
    }
}






