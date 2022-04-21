package bu.ac.kr.anyfeeling.first

import android.content.Intent
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
    ): View {
        mBinding = FragmentFirsttabBinding.inflate(inflater, container, false)

        mBinding.menuHappy.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                val intent = Intent(context, HappyActivity::class.java)
                startActivity(intent)
            }

        })
        mBinding.menuSad.setOnClickListener {
            val intent = Intent(context, HappyActivity::class.java)
            startActivity(intent)
        }


        return mBinding.root
    }




}