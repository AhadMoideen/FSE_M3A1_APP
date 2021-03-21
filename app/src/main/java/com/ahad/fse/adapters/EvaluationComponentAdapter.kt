package com.ahad.fse.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ahad.fse.R
import com.ahad.fse.interfaces.OnItemClickListener
import com.ahad.fse.models.EvaluationComponent
import kotlinx.android.synthetic.main.course_card_item.view.*
import kotlinx.android.synthetic.main.course_card_item.view.evaluationComponentId
import kotlinx.android.synthetic.main.course_card_item.view.mark
import kotlinx.android.synthetic.main.eval_component_item.view.*

class EvaluationComponentAdapter(private val list: List<EvaluationComponent>) : RecyclerView.Adapter<EvaluationComponentAdapter.EvaluationComponentHolder>() {


    inner class EvaluationComponentHolder(evalCardView: View) :
        RecyclerView.ViewHolder(evalCardView) {
        val evaluationComponentId: TextView = evalCardView.evaluationComponentId
        val mark: TextView = evalCardView.mark
        val noOfQuestions: TextView = evalCardView.noOfQuestions
        val dateTime: TextView = evalCardView.dateTime
        val type: TextView = evalCardView.type

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EvaluationComponentHolder {
        val courseCardView = LayoutInflater.from(parent.context).inflate(
            R.layout.eval_component_item,
            parent, false
        )
        return EvaluationComponentHolder(courseCardView)
    }
    override fun onBindViewHolder(holder: EvaluationComponentHolder, position: Int) {
        val currentCourse = list[position]
        holder.evaluationComponentId.text = currentCourse.evaluationComponentId.toString()
        holder.mark.text = currentCourse.marks.toString()
        holder.noOfQuestions.text = currentCourse.noOfQuestions.toString()
        holder.dateTime.text = currentCourse.dateTime
        holder.type.text = currentCourse.type
    }

    override fun getItemCount() = list.size

}