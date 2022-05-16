package bu.ac.kr.anyfeeling

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import bu.ac.kr.anyfeeling.homeFragment.HomeFragment
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.common.collect.Lists.asList
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.kakao.sdk.common.util.Utility
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var callbackManager: CallbackManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)


        val keyHash = Utility.getKeyHash(this)//onCreate 안에 입력해주자
        Log.d("Hash", keyHash)
        auth = Firebase.auth
        callbackManager = CallbackManager.Factory.create()


        val signUpButton = findViewById<Button>(R.id.signUpButton)
        // 회원가입 창으로
        signUpButton.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }


        // 로그인 버튼
        val loginButton = findViewById<Button>(R.id.loginButton)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        loginButton.setOnClickListener {
            signIn(emailEditText.text.toString(), passwordEditText.text.toString())
        }



    }

    // 로그아웃하지 않을 시 자동 로그인 , 회원가입시 바로 로그인 됨
    public override fun onStart() {
        super.onStart()
        moveMainPage(auth?.currentUser)

    }


    // 로그인
    private fun signIn(email: String, password: String) {

        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth?.signInWithEmailAndPassword(email, password)
                ?.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            baseContext, "로그인에 성공 하였습니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                        moveMainPage(auth?.currentUser)
                    } else {
                        Toast.makeText(
                            baseContext, "로그인에 실패 하였습니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }



    // 유저정보 넘겨주고 메인 액티비티 호출
    private fun moveMainPage(user: FirebaseUser?) {
        if (user != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }









}