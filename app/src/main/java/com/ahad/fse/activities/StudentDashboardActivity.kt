package com.ahad.fse.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ahad.fse.R
import kotlinx.android.synthetic.main.activity_faculty_dashboard.*
import kotlinx.android.synthetic.main.activity_student_dashboard.*
import kotlinx.android.synthetic.main.activity_student_dashboard.btLogout

class StudentDashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_dashboard)

        /* Logout configuration */
        btLogout.setOnClickListener {
            val sharedPref =
                getSharedPreferences(FSEConstants.APP_SHARED_PREF, Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.remove(FSEConstants.TOKEN)
            editor.remove(FSEConstants.USERNAME)
            val result = editor.commit()
            val loginIntend = Intent(this, LoginActivity::class.java)
            startActivityForResult(loginIntend, 1)
        }
    }
}