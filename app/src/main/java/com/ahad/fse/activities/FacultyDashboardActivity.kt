package com.ahad.fse.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahad.fse.R
import com.ahad.fse.adapters.CourseAdapter
import com.ahad.fse.interfaces.OnItemClickListener
import com.ahad.fse.models.Course
import kotlinx.android.synthetic.main.activity_faculty_dashboard.*

class FacultyDashboardActivity : AppCompatActivity(), OnItemClickListener {
    private val TAG = "FACULTY-DASHBOARD"
    private var courses: Array<Course> = emptyArray()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faculty_dashboard)

        /* Validate requirements for Dashboard */
        validation()

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


    override fun onStart() {
        super.onStart()
        validation()
        /* Get username from shared-preference */
        val sharedPref = getSharedPreferences(FSEConstants.APP_SHARED_PREF, Context.MODE_PRIVATE)
        val username = sharedPref.getString(FSEConstants.USERNAME, "")

        val categories: Array<Course> = dummyCourses(username)
        this.courses = dummyCourses(username)
        recyclerCourses.adapter = CourseAdapter(categories.asList(), this)
        recyclerCourses.layoutManager = LinearLayoutManager(this)
        recyclerCourses.setHasFixedSize(true)
    }

    /**
     * OnItem click for the Course to re-direct to Course-Detail page.
     */
    override fun onItemClick(position: Int) {
        /* Get username from shared-preference */
        Toast.makeText(this, "Course: ${position}", Toast.LENGTH_SHORT).show()
        val clickedCourse = this.courses[position]

        val courseDetailIntent = Intent(this, CourseDetailActivity::class.java)
        courseDetailIntent.putExtra(FSEConstants.COURSE_ID, clickedCourse.courseId);
        startActivity(courseDetailIntent)
    }


    /**
     * All validations can be added to this function
     */
    private fun validation() {
        val sharedPref = getSharedPreferences(FSEConstants.APP_SHARED_PREF, Context.MODE_PRIVATE)
        val token = sharedPref.getString(FSEConstants.TOKEN, "")
        if (token.isNullOrBlank() || token.isNullOrEmpty()) {
            val loginIntend = Intent(this, LoginActivity::class.java)
            startActivityForResult(loginIntend, 1)
        }
    }

    /**
     * Function to get Dummy services for MVP later to be replaced by API call with username.
     */
    private fun dummyCourses(username: String?): Array<Course> {
        val dummyCourses = mutableListOf<Course>()
        val course1 = Course(1, "C1", "C1-Description", username)
        val course2 = Course(2, "C2", "C2-Description", username)
        val course3 = Course(3, "C3", "C3-Description", username)
        dummyCourses.add(course1)
        dummyCourses.add(course2)
        dummyCourses.add(course3)
        return dummyCourses.toTypedArray()
    }
}