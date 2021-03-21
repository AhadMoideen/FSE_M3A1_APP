package com.ahad.fse.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.ahad.fse.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val TAG = "LOGIN"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        configureFields()
    }

    /**
     * Function to configure each field.
     */
    private fun configureFields() {
        btRegister.setOnClickListener{
            /* Register - Go to register page */
            val registerIntend = Intent(this, RegisterActivity::class.java)
            startActivityForResult(registerIntend, 1)
        }
        // Configure Spinner - User type
        ArrayAdapter.createFromResource(
            this,
            R.array.user_types,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            userType.adapter = adapter
        }

        btLogin.setOnClickListener{
            var username = username.text
            var password = password.text
            var type = userType.selectedItem.toString()

            /* TODO: api call to validate */
            val sharedPref = getSharedPreferences(FSEConstants.APP_SHARED_PREF,Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString(FSEConstants.TOKEN, "TEST-TOKEN")
            editor.putString(FSEConstants.USERNAME,"ahadmoideen@gmail.com")
            val result = editor.commit()

            /* TODO: Re-direction based on the type user will be done in next */
            val loginIntend = Intent(this, FacultyDashboardActivity::class.java)
            startActivityForResult(loginIntend, 1)
            /* TODO: if user is Student-redirect to Student dashboard  */
        }
    }
}