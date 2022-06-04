package com.enzhongwen.hanyu3

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.enzhongwen.hanyu3.databinding.FragmentStrokeBinding

class StrokeFragment : Fragment() {

    lateinit var binding: FragmentStrokeBinding

    private var item: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments.let {
            item = it!!.getInt("id")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStrokeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.word.text = vocabAll[item - 312].word
        binding.pinyin.text = vocabAll[item - 312].pinyin
        binding.definition.text = getString(vocabAll[item - 312].definition)
        binding.stroke1.setImageResource(vocabAll[item - 312].stroke1)
        vocabAll[item - 312].stroke2?.let { binding.stroke2.setImageResource(it) }
        vocabAll[item - 312].stroke3?.let { binding.stroke3.setImageResource(it) }
        binding.sound.setOnClickListener {
            val a = MediaPlayer.create(context, vocabAll[item - 312].sound)
            a.start()
            a.setOnCompletionListener {
                a.release()
            }
        }
    }
}