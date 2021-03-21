package com.ahad.fse.activities

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import com.ahad.fse.R
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.userType
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

            /* TODO: Call API for registration */

            /* TODO: if success redirect to Login */
            val loginIntend = Intent(this, LoginActivity::class.java)
            startActivityForResult(loginIntend, 1)
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