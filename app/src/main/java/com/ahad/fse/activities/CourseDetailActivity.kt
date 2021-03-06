package com.ahad.fse.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahad.fse.R
import com.ahad.fse.adapters.EvaluationComponentAdapter
import com.ahad.fse.models.CourseDetail
import com.ahad.fse.models.EvaluationComponent
import com.ahad.fse.models.Module
import com.ahad.fse.models.Student
import kotlinx.android.synthetic.main.activity_course_detail.*

class CourseDetailActivity : AppCompatActivity() {

    private val TAG = "COURSE-DETAIL"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_detail)

        val bundle: Bundle? = intent.extras
        /* TODO: API call to get Course details */
        var courseDetail = getDummyCourseDetail()
        populateDetails(courseDetail)
    }

    /**
     * Populate fields
     */
    private fun populateDetails(courseDetail: CourseDetail) {
        courseNameValue.text = courseDetail.courseName
        descriptionValue.text = courseDetail.description
        faculty.text = courseDetail.faculty

        if(!courseDetail.evaluationComponents.isNullOrEmpty()){
            recyclerEvalComponents.adapter =
                EvaluationComponentAdapter(courseDetail.evaluationComponents!!.toList())
            recyclerEvalComponents.layoutManager = LinearLayoutManager(this)
            recyclerEvalComponents.setHasFixedSize(true)
            evalHeading.visibility = ConstraintLayout.VISIBLE

        }
        else {
            evalHeading.visibility = ConstraintLayout.GONE
        }

    }


    /**
     * Dummy function to get dummy course details.
     */
    private fun getDummyCourseDetail(): CourseDetail {

        var s1 = Student(
            3, "Student 1", "student1@gmail.com",
            "2021-01-26T10:51:35.915000Z", "STUDENT"
        )
        var s2 = Student(
            4, "Student 2", "student2@gmail.com",
            "2021-01-26T10:51:35.915000Z", "STUDENT"
        )

        var eval1 = EvaluationComponent(
            1, 10, 15,
            "Sat Feb 06 2021 09:26:36 GMT+0400 (Gulf Standard Time)", "QUIZ"
        )
        var eval2 = EvaluationComponent(
            2, 10, 20,
            "Sat Feb 07 2021 09:26:36 GMT+0400 (Gulf Standard Time)", "QUIZ"
        )

        var m1 = Module(1, "M1-Walk", "First time walking")
        var m2 = Module(2, "M2-Walk", "Second time walking")

        val courseDetail = CourseDetail(
            3, "Run-Intro",
            "Fast running by flash",
            "flash@tripworld.com",
            arrayOf(m1, m2),
            arrayOf(eval1, eval2),
            arrayOf(s1, s2)
        )
        return courseDetail
    }

}