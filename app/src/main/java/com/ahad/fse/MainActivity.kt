package com.ahad.fse

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ahad.fse.activities.FSEConstants
import com.ahad.fse.activities.LoginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


      //  TODO("Check if logged in ? redirect to specific dashboard ")
        val loginIntend = Intent(this, LoginActivity::class.java)
        startActivityForResult(loginIntend, 1)
    }

    override fun onStart() {
        super.onStart()
        Log.d("Main:","Main - on Start")


        //TODO("Check if logged in ? redirect to specific dashboard ")
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        val token = sharedPref.getString(FSEConstants.TOKEN,"").toString()
        if(token != ""){
            /* TODO: Logic to re-direct to dashboard */
        }
        else {
            val loginIntend = Intent(this, LoginActivity::class.java)
            startActivityForResult(loginIntend, 1)
        }

    }
}