package com.enzhongwen.hanyu3

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.enzhongwen.hanyu3.databinding.FragmentMainBinding
import com.google.android.gms.ads.MobileAds

class MainFragment: Fragment() {

    lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        MobileAds.initialize(requireContext()) {}

        binding.learnButton.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToLessonFragment())
        }
    }
}