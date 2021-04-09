package com.ahad.fse.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.ahad.fse.R
import com.ahad.fse.api.RetrofitInstance
import com.ahad.fse.models.User
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class LoginActivity : AppCompatActivity() {

    private val TAG = "LOGIN"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        validation()
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
            val user = getUser(username.toString(), password.toString(), type);

        }
    }

    /**
     * Function to call API and do
     */
    private fun getUser(username: String, password: String, userType: String): User {
        val user =  User()
        user.userName = username
        user.userType = userType
        user.password = password
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val loginUser = user
                val response = RetrofitInstance.userApiClient.getCourse(user)
                withContext(Dispatchers.Main){

                val userResponse = response.body();
                if (response.isSuccessful && userResponse != null) {
                    if(loginUser.userType == userResponse.userType){
                        Toast.makeText(
                            this@LoginActivity,
                            "Course: ${response.message()}",
                            Toast.LENGTH_SHORT
                        ).show()
                        val sharedPref = getSharedPreferences(FSEConstants.APP_SHARED_PREF,Context.MODE_PRIVATE)
                        val editor = sharedPref.edit()
                        editor.putString(FSEConstants.TOKEN, userResponse.id.toString())
                        editor.putString(FSEConstants.USERNAME,userResponse.userName)
                        val result = editor.commit()

                        /* Re-direction based on the type user will be done in next */
                        if(userResponse.userType == "FACULTY"){
                            val loginIntend = Intent(this@LoginActivity, FacultyDashboardActivity::class.java)
                            startActivityForResult(loginIntend, 1)
                        }else if(userResponse.userType == "STUDENT"){
                            val loginIntend = Intent(this@LoginActivity, StudentDashboardActivity::class.java)
                            startActivityForResult(loginIntend, 1)
                        }
                    }
                    else{
                        Toast.makeText(
                            this@LoginActivity,
                            "Invalid access: ${loginUser.userType}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "Error: ${response.message()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                 }
            } catch (e: Exception) {
                Toast.makeText(
                    this@LoginActivity,
                    "Error: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        return User()
    }

    /**
     * All validations can be added to this function
     */
    private fun validation() {
        val sharedPref = getSharedPreferences(FSEConstants.APP_SHARED_PREF, Context.MODE_PRIVATE)
        val token = sharedPref.getString(FSEConstants.TOKEN, "")
        if (!token.isNullOrBlank() && !token.isNullOrEmpty()) {
            val loginIntend = Intent(this@LoginActivity, FacultyDashboardActivity::class.java)
            startActivityForResult(loginIntend, 1)
        }
    }
}