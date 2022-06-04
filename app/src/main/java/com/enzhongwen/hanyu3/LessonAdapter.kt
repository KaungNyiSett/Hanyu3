package com.enzhongwen.hanyu3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class LessonAdapter: RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {

    lateinit var context: Context

    class LessonViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val lesson: TextView = view.findViewById(R.id.lesson)
        val heading: TextView = view.findViewById(R.id.heading)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val actionLayout = LayoutInflater.from(parent.context).inflate(R.layout.lesson_list,parent,false)
        return LessonViewHolder(actionLayout)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        val item = data[position]
        context = holder.view.context
        holder.lesson.text = context.resources.getString(item.lesson)
        holder.heading.text = item.heading

        holder.view.setOnClickListener {
            holder.view.findNavController().navigate(LessonFragmentDirections.actionLessonFragmentToVocabularyFragment(position+1))
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

data class DataLesson(@StringRes val lesson: Int, val heading: String)

val data: List<DataLesson> = listOf(
    DataLesson(R.string.lesson_1,"今天几月几日"),
    DataLesson(R.string.lesson_2,"明天星期一"),
    DataLesson(R.string.lesson_3,"现在八点半"),
    DataLesson(R.string.lesson_4,"今天的天气怎么样"),
    DataLesson(R.string.lesson_5,"北京的四季"),
    DataLesson(R.string.lesson_6,"陈老师教我们中文课"),
    DataLesson(R.string.lesson_7,"我给妈妈一件礼物"),
    DataLesson(R.string.lesson_8,"奶奶请我们吃中国菜"),
    DataLesson(R.string.lesson_9,"小红喜欢看电影"),
    DataLesson(R.string.lesson_10,"祝你生日快乐"),
    DataLesson(R.string.lesson_11,"我们在海边玩沙子"),
    DataLesson(R.string.lesson_12,"小花猫给外婆送鱼"),
    DataLesson(R.string.lesson_13,"爷爷去公园打太极拳"),
    DataLesson(R.string.lesson_14,"我们坐车去动物园"),
    DataLesson(R.string.lesson_15,"大家去郊游"),
)