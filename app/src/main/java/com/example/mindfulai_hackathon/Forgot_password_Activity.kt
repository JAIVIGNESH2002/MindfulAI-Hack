package com.example.mindfulai_hackathon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import com.example.mindfulai_hackathon.databinding.ActivityForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Forgot_password_Activity : basicActivity() {
    private var binding:ActivityForgotPasswordBinding?=null
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        auth = Firebase.auth
    }
    private fun resetPassword(){
        val email = binding?.etForgotPasswordEmail?.text.toString()
        if (validateEmail(email))
        {

            auth.sendPasswordResetEmail(email).addOnCompleteListener(this){task->
                if (task.isSuccessful)
                {
                    binding?.tilEmailForgetPassword?.visibility = View.GONE
                    binding?.tvSubmitMsg?.visibility = View.VISIBLE
                    binding?.btnForgotPasswordSubmit?.visibility = View.GONE
                   hideProgress()
                }
                else
                {
                    hideProgress()
                    showToast(this,"Reset password failed, try again latter")
                }
            }
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
    private fun validateEmail(email:String):Boolean
    {
        return if (TextUtils.isEmpty(email) && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding?.tilEmailForgetPassword?.error = "Enter valid email address"
            false
        } else {
            binding?.tilEmailForgetPassword?.error = null
            true
        }
    }
}