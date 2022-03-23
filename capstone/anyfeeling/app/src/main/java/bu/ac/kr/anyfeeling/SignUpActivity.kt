package bu.ac.kr.anyfeeling

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.CallbackManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        auth = Firebase.auth


        val email = findViewById<EditText>(R.id.emailEditText)
        val password = findViewById<EditText>(R.id.passwordEditText)
        val passwordCheck = findViewById<EditText>(R.id.passwordCheck)
        val userNickname = findViewById<EditText>(R.id.userNickname)
        val signupOk = findViewById<Button>(R.id.signupOk)

        signupOk.setOnClickListener {
            createAccount(email.text.toString(),password.text.toString())
        }
        cancel()


        /*signupOk.setOnClickListener{
            if(email.text.toString().isEmpty() || password.text.toString().isEmpty()) {
                Toast.makeText(this, "아이디 혹은 비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show()
            }else{


                        }else{

                        }
                    }
                        if(password.text.toString()!=passwordCheck.text.toString()){
                            Toast.makeText(this, "비밀번호가 일치하지 않습니다.",Toast.LENGTH_SHORT).show()
                        }
                        }


            }*/

    }

    private fun createAccount(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth?.createUserWithEmailAndPassword(email, password)
                ?.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            this,
                            "회원가입에 성공했습니다. 로그인 버튼을눌러 로그인 해주세요.",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    } else {
                        Toast.makeText(this, "이미 가입한 이메일이거나, 회원가입에 실패했습니다.", Toast.LENGTH_SHORT)
                            .show()

                    }
                }
        }
    }


        private fun cancel() {
            val signupCancel = findViewById<Button>(R.id.signupCancel)
            signupCancel.setOnClickListener {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }


        }

    }