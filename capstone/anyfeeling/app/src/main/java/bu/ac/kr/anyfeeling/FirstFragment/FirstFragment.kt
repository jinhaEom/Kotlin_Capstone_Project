package bu.ac.kr.anyfeeling.firstFragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import bu.ac.kr.anyfeeling.R
import bu.ac.kr.anyfeeling.databinding.FragmentFirsttabBinding

class FirstFragment : Fragment(R.layout.fragment_firsttab) {
    private lateinit var mBinding: FragmentFirsttabBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentFirsttabBinding.inflate(inflater, container, false)

        return mBinding.root
    }




}