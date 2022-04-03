package bu.ac.kr.anyfeeling.homeFragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import bu.ac.kr.anyfeeling.MainActivity
import bu.ac.kr.anyfeeling.R
import bu.ac.kr.anyfeeling.databinding.ActivityMainBinding
import bu.ac.kr.anyfeeling.databinding.FragmentHomeBinding
import bu.ac.kr.anyfeeling.dpToPx

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var isGatheringMotionAnimating: Boolean = false


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            View {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
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
        binding.gatheringDigitalThingsLayout.setTransitionListener(object :
            MotionLayout.TransitionListener {
            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int
            ) {
                isGatheringMotionAnimating = true
            }

            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float
            ) {
            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                isGatheringMotionAnimating = false
            }

            override fun onTransitionTrigger(
                motionLayout: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float
            ) {
            }
        })

        return binding.root

    }





}




