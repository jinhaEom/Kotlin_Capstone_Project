package bu.ac.kr.anyfeeling.firstFragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentController
import androidx.navigation.NavController
import androidx.navigation.Navigation
import bu.ac.kr.anyfeeling.R
import bu.ac.kr.anyfeeling.databinding.FragmentFirsttabBinding

class FirstFragment : Fragment(R.layout.fragment_firsttab)/*, View.OnClickListener*/ {
    private lateinit var navController: NavController
    private lateinit var mBinding: FragmentFirsttabBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentFirsttabBinding.inflate(inflater, container, false)
        mBinding.menuHappy.setOnClickListener {
            val intent = Intent(requireContext(),HappyActivity::class.java)
            startActivity(intent)
        }

        return mBinding.root
    }




}