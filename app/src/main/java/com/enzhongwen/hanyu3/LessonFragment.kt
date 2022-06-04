package com.enzhongwen.hanyu3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.enzhongwen.hanyu3.databinding.FragmentLessonBinding


class LessonFragment: Fragment() {

    private lateinit var binding: FragmentLessonBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLessonBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = LessonAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

}