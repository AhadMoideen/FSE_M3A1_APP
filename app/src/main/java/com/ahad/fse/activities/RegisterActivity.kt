package com.ahad.fse.activities

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ahad.fse.R
import com.ahad.fse.api.RetrofitInstance
import com.ahad.fse.models.User
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.util.*

class RegisterActivity : AppCompatActivity() {

    var cal = Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        // Configure Spinner - User type
        ArrayAdapter.createFromResource(
            this,
            R.array.user_types,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            userType.adapter = adapter
        }
        dateText.text = "--/--/----"
        // create an OnDateSetListener
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        dateText.setOnClickListener {
                DatePickerDialog(this@RegisterActivity,
                    dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()

        }

        btRegister.setOnClickListener {
            val fullName = fullName.text
            val username = username.text
            val password = findViewById<EditText>(R.id.password).text
            val confirmPassword = confirmPassword.text
            var type = findViewById<Spinner>(R.id.userType).selectedItem.toString()

             val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS")

            /* API for registration */
            val unregisteredUser = User(
                Int.MIN_VALUE,
                fullName.toString(),
                username.toString(),
                dateFormat.format(cal.getTime()),
                type,
                password.toString()
            )
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response = RetrofitInstance.userApiClient.register(unregisteredUser)
                    withContext(Dispatchers.Main){

                        val user = response.body();
                        if (response.isSuccessful && user != null) {
                            Toast.makeText(
                                this@RegisterActivity,
                                "Course: ${response.message()}",
                                Toast.LENGTH_SHORT
                            ).show()

                            /* TODO: if success redirect to Login */
                            val loginIntend = Intent(this@RegisterActivity, LoginActivity::class.java)
                            startActivityForResult(loginIntend, 1)

                        } else {
                            Toast.makeText(
                                this@RegisterActivity,
                                "Error: ${response.message()}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } catch (e: Exception) {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Error: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    /**
     * Function to update Date change.
     */
    private fun updateDateInView() {
        val myFormat = "MM/dd/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        Log.d("Register",sdf.format(cal.getTime()))
        dateText.text = sdf.format(cal.getTime())
    }
}