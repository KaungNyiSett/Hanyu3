package com.enzhongwen.hanyu3

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.enzhongwen.hanyu3.database.Saved
import com.enzhongwen.hanyu3.database.SavedDatabase
import com.enzhongwen.hanyu3.database.SavedRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VocabularyAdapter(val lessonNo: Int?, val a: Int, val filtered: List<Saved>) :
    RecyclerView.Adapter<VocabularyAdapter.VocabularyViewHolder>() {

    private lateinit var context: Context

    val scope = CoroutineScope(Dispatchers.IO)

    private val getLesson: List<Saved> = when (lessonNo) {
        1 -> vocab_lesson1
        2 -> vocab_lesson2
        3 -> vocab_lesson3
        4 -> vocab_lesson4
        5 -> vocab_lesson5
        6 -> vocab_lesson6
        7 -> vocab_lesson7
        8 -> vocab_lesson8
        9 -> vocab_lesson9
        10 -> vocab_lesson10
        11 -> vocab_lesson11
        12 -> vocab_lesson12
        13 -> vocab_lesson13
        14 -> vocab_lesson14
        15 -> vocab_lesson15
        0 -> filtered
        else -> currentList
    }

    class VocabularyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val word: TextView = view.findViewById(R.id.word)
        val pinyin: TextView = view.findViewById(R.id.pinyin)
        val definition: TextView = view.findViewById(R.id.definition)
        val sound: ImageButton = view.findViewById(R.id.sound)
        val save: ImageButton = view.findViewById(R.id.save)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VocabularyViewHolder {
        val actionLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.vocab_list, parent, false)
        return VocabularyViewHolder(actionLayout)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: VocabularyViewHolder, position: Int) {
        val item = if (lessonNo != 16) {
            getLesson[position]
        } else {
            currentList[position]
        }

        context = holder.view.context

        item.saved = false
        for (i in currentList) {
            if (i.id1 == item.id1) {
                item.saved = true
            }
        }

        if (!item.saved) {
            holder.save.setImageResource(R.drawable.ic_star_border)
        } else {
            holder.save.setImageResource(R.drawable.ic_star_filled)
        }

        holder.word.text = item.word
        if (a == 0) {
            holder.pinyin.text = item.pinyin
            holder.definition.text = context.resources.getString(item.definition)
        } else {
            holder.pinyin.text = null
            holder.definition.text = null
        }

        holder.sound.setOnClickListener {
            val a1 = MediaPlayer.create(context, item.sound)
            a1.start()
            a1.setOnCompletionListener {
                a1.release()
            }
        }

            holder.view.setOnClickListener {

                when (lessonNo) {
                    0 -> holder.view.findNavController().navigate(
                        AllvocabFragmentDirections.actionAllvocabFragmentToStrokeFragment(item.id1)
                    )
                    16 -> holder.view.findNavController().navigate(
                        SavedFragmentDirections.actionSavedFragmentToStrokeFragment(item.id1)
                    )
                    else -> holder.view.findNavController().navigate(
                        VocabularyFragmentDirections.actionVocabularyFragmentToStrokeFragment(item.id1)
                    )
                }

            }

            holder.save.setOnClickListener {
                if (!item.saved) {
                    item.saved = true
                    scope.launch {
                        SavedRepository(
                            SavedDatabase.getDatabase(context).savedDao()
                        ).save(item)
                    }
                    holder.save.setImageResource(R.drawable.ic_star_filled)
                    Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
                } else {
                    scope.launch {
                        SavedRepository(
                            SavedDatabase.getDatabase(context).savedDao()
                        ).deleteItem(item)
                    }
                    item.saved = false
                    holder.save.setImageResource(R.drawable.ic_star_border)
                    Toast.makeText(context, "Unsaved", Toast.LENGTH_SHORT).show()
                }

            }

        }


    override fun getItemCount(): Int {
        return if(lessonNo != 16){
            getLesson.size
        }else{
            currentList.size
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(saved: MutableList<Saved>){
        currentList = saved
        notifyDataSetChanged()
    }

}
