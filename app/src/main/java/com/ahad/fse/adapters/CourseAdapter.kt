package com.ahad.fse.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ahad.fse.R
import com.ahad.fse.interfaces.OnItemClickListener
import com.ahad.fse.models.Course
import kotlinx.android.synthetic.main.course_card_item.view.*

class CourseAdapter(
    private val list: List<Course>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<CourseAdapter.CourseHolder>() {


    inner class CourseHolder(courseCardView: View) : RecyclerView.ViewHolder(courseCardView),
        View.OnClickListener {
        val courseId: TextView = courseCardView.evaluationComponentId
        val courseName: TextView = courseCardView.mark
        val courseDescription: TextView = courseCardView.description

        init {
            courseCardView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseHolder {
        val courseCardView = LayoutInflater.from(parent.context).inflate(
            R.layout.course_card_item,
            parent, false
        )
        return CourseHolder(courseCardView)
    }

    override fun onBindViewHolder(holder: CourseHolder, position: Int) {
        val currentCourse = list[position]
        holder.courseId.text = currentCourse.courseId.toString()
        holder.courseName.text = currentCourse.courseName
        holder.courseDescription.text = currentCourse.description
    }

    override fun getItemCount() = list.size

}