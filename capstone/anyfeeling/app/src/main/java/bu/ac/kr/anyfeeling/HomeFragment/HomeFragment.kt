package bu.ac.kr.anyfeeling.homeFragment

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import bu.ac.kr.anyfeeling.MainActivity
import bu.ac.kr.anyfeeling.R
import bu.ac.kr.anyfeeling.databinding.ActivityMainBinding
import bu.ac.kr.anyfeeling.databinding.FragmentHomeBinding
import bu.ac.kr.anyfeeling.dpToPx
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs

class HomeFragment : Fragment(R.layout.fragment_home){

    private var isGatheringMotionAnimating: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):

            View {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)


        binding.gatheringDigitalThingsLayout.setTransitionListener(object :
            MotionLayout.TransitionListener {
            override fun onTransitionStarted(motionLayout: MotionLayout?, startId: Int, endId: Int) {
                isGatheringMotionAnimating = true
            }
            override fun onTransitionChange(motionLayout: MotionLayout?, startId: Int, endId: Int, progress: Float) {}
            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                isGatheringMotionAnimating = false
            }
            override fun onTransitionTrigger(motionLayout: MotionLayout?, triggerId: Int, positive: Boolean, progress: Float) {}


        })


        binding.scrollView.viewTreeObserver.addOnScrollChangedListener {
            if (binding.scrollView.scrollY > 150f.dpToPx(this).toInt()) {
                if (isGatheringMotionAnimating.not()) {
                    binding.gatheringDigitalThingsLayout.transitionToEnd()
                    binding.textViewShownMotionLayout.transitionToEnd()
                }
            } else {
                if (isGatheringMotionAnimating.not()) {
                    binding.gatheringDigitalThingsLayout.transitionToStart()
                    binding.textViewShownMotionLayout.transitionToStart()

                }
            }
        }




        return binding.root

    }








}





