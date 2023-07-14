package com.example.mindfulai_hackathon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import com.example.mindfulai_hackathon.databinding.ActivitySigninBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SigninActivity : basicActivity() {
    private var binding:ActivitySigninBinding?=null
    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        auth = Firebase.auth
        binding?.tvRegister?.setOnClickListener {
            startActivity(Intent(this,SignupActivity::class.java))
            finish()
        }
        binding?.tvForgotPassword?.setOnClickListener{
            startActivity(Intent(this,Forgot_password_Activity::class.java))

        }
        binding?.btnSignIn?.setOnClickListener {
            signInUser()
        }
    }
    private fun signInUser(){
        val email = binding?.etSinInEmail?.text.toString()
        val password = binding?.etSinInPassword?.text.toString()
        if(validateForm(email,password)){
            auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this){task->
                    if (task.isSuccessful)
                    {
                        startActivity(Intent(this,MainActivity::class.java))
                        finish()
                    }
                    else
                    {
                        binding?.btnSignIn?.text = "Login"
                        Toast.makeText(this,"Oops! Something went wrong", Toast.LENGTH_SHORT).show()
                    }
                    hideProgress()
                }
        }
    }
    private fun validateForm(email:String,password:String):Boolean
    {
        return when {
            TextUtils.isEmpty(email) && !Patterns.EMAIL_ADDRESS.matcher(email).matches()->{
                binding?.tilEmail?.error = "Enter valid email address"
                false
            }
            TextUtils.isEmpty(password)->{
                binding?.tilPassword?.error = "Enter password"
                binding?.tilEmail?.error = null
                false
            }
            else -> {
                binding?.tilEmail?.error = null
                binding?.tilPassword?.error = null
                true }
        }
    }

}