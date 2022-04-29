package bu.ac.kr.anyfeeling.homeFragment


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import bu.ac.kr.anyfeeling.MainActivity
import bu.ac.kr.anyfeeling.R
import bu.ac.kr.anyfeeling.databinding.FragmentHomeBinding
import bu.ac.kr.anyfeeling.spToPx


class HomeFragment : Fragment(R.layout.fragment_home) {


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
            if (binding.scrollView.scrollY > 150) {
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
        with(binding) {
            ViewCompat.setOnApplyWindowInsetsListener(coordinator) { v: View, insets: androidx.core.view.WindowInsetsCompat ->
                val params = v.layoutParams as android.view.ViewGroup.MarginLayoutParams
                params.bottomMargin = insets.systemWindowInsetBottom
                toolbarContainer.layoutParams = (toolbarContainer.layoutParams as ViewGroup.MarginLayoutParams).apply {
                    setMargins(0, insets.systemWindowInsetTop, 0, 0)
                }
                collapsingToolbarContainer.layoutParams = (collapsingToolbarContainer.layoutParams as ViewGroup.MarginLayoutParams).apply {
                    setMargins(0, 0, 0, 0)
                }

                insets.consumeSystemWindowInsets()
            }

        }

        return binding.root

    }




}





