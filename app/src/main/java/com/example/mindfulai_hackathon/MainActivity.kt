package com.example.mindfulai_hackathon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mindfulai_hackathon.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private var binding:ActivityMainBinding?=null
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        auth = Firebase.auth
        binding?.logOut?.setOnClickListener {
            Toast.makeText(this,"iExist",Toast.LENGTH_SHORT).show();
            if(auth.currentUser!=null){
                Toast.makeText(this,"Exist",Toast.LENGTH_SHORT).show();
                auth.signOut()
                startActivity(Intent(this,getStarted::class.java))
                finish()
            }else{
                Toast.makeText(this,"Already",Toast.LENGTH_SHORT).show();
            }
        }
    }
}