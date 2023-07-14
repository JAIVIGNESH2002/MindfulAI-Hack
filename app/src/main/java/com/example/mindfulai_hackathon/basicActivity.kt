package com.example.mindfulai_hackathon

import android.app.Activity
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

open class basicActivity : AppCompatActivity() {
    private lateinit var progressBar: Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic)
    }
    fun showProgress(){
        progressBar  = Dialog(this)
        progressBar.setContentView(R.layout.progress_bar)
        progressBar.setCancelable(false)
        progressBar.show()
    }
    fun hideProgress(){
        progressBar.hide()
    }
    fun showToast(activity: Activity, msg:String){
        Toast.makeText(activity,msg,Toast.LENGTH_SHORT).show()
    }
}