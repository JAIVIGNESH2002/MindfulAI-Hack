package com.example.mindfulai_hackathon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import com.example.mindfulai_hackathon.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.auth.User
import com.google.firebase.ktx.Firebase

class SignupActivity : basicActivity() {
    private var binding:ActivitySignupBinding?=null
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        auth = Firebase.auth
        binding?.tvLoginPage?.setOnClickListener {
            startActivity(Intent(this,SigninActivity::class.java))
            finish()
        }
        binding?.btnSignUp?.setOnClickListener {
            registerUser()
        }
    }

    private fun validateForm(name: String, email: String, password: String?): Boolean {
        return when {
            TextUtils.isEmpty(name)->{
                binding?.tilName?.error = "Enter name"
                false
            }
            TextUtils.isEmpty(email) && !Patterns.EMAIL_ADDRESS.matcher(email).matches()->{
                binding?.tilEmail?.error = "Enter valid email address"
                false
            }
            TextUtils.isEmpty(password)->{
                binding?.tilPassword?.error = "Enter password"
                false
            }
            else -> { true }
        }
    }

    private fun registerUser()
    {
        val name = binding?.etSinUpName?.text.toString()
        val email = binding?.etSinUpEmail?.text.toString()
        val password = binding?.etSinUpPassword?.text.toString()
        if (validateForm(name, email, password))
        {
            showProgress()
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){task ->
                    if (task.isSuccessful)
                    {
//                        val rndInt = (1..12).random()
//                        val image = "profilephoto/profilepic${rndInt}.png"
//                        val firebaseUser: FirebaseUser? = task.result.user
//                        val userInfo = User(firebaseUser?.uid,name,firebaseUser?.email,image)
//                        FireStoreClass().registerUser(userInfo)
                        Toast.makeText(this,"Registration Successful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,MainActivity::class.java))
                        finish()
                    }
                    else
                    {
                        Toast.makeText(this,"Oops! Something went wrong", Toast.LENGTH_SHORT).show()
                    }
                    hideProgress()
                }
        }
    }

}