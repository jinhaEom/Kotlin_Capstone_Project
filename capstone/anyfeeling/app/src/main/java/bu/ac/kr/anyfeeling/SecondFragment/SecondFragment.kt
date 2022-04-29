package bu.ac.kr.anyfeeling.secondFragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import bu.ac.kr.anyfeeling.LoginActivity
import bu.ac.kr.anyfeeling.R
import bu.ac.kr.anyfeeling.databinding.FragmentSecondtabBinding
import com.google.firebase.auth.FirebaseAuth

class SecondFragment: DialogFragment(R.layout.fragment_secondtab)  {
    lateinit var binding: FragmentSecondtabBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondtabBinding.inflate(inflater, container, false)
        binding.logoutButton.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("로그아웃")
                .setMessage("로그아웃 하시겠습니까?")
                .setPositiveButton("확인",
                DialogInterface.OnClickListener{
                    dialog,id ->
                    FirebaseAuth.getInstance().signOut()
                    val intent = Intent(context,LoginActivity::class.java)
                    startActivity(intent)
                })
                .setNegativeButton("취소",
                    DialogInterface.OnClickListener{
                            dialog,id ->
                    })
            builder.show()
        }


        return binding.root
    }

    }


