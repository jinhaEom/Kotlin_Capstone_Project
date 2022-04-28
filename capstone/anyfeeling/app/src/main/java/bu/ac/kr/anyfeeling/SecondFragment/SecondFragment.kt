package bu.ac.kr.anyfeeling.secondFragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AlertDialogLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import bu.ac.kr.anyfeeling.LoginActivity
import bu.ac.kr.anyfeeling.R
import bu.ac.kr.anyfeeling.databinding.DialogLayoutBinding
import bu.ac.kr.anyfeeling.databinding.FragmentFirsttabBinding
import bu.ac.kr.anyfeeling.databinding.FragmentSecondtabBinding

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


