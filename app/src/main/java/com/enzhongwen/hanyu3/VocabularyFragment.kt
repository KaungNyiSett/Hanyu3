package com.enzhongwen.hanyu3

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.enzhongwen.hanyu3.database.SavedViewModel
import com.enzhongwen.hanyu3.databinding.FragmentVocabularyBinding

class VocabularyFragment: Fragment() {

    private var a = 0

    private val mSavedViewModel: SavedViewModel by activityViewModels()

    lateinit var binding: FragmentVocabularyBinding

    private var lessonNo: Int? = 0

    lateinit var adapter: VocabularyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments.let {
            lessonNo = it?.getInt("lessonNo")
        }

        adapter = VocabularyAdapter(lessonNo, a, vocabAll)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = FragmentVocabularyBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mSavedViewModel.readAllData.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })

        binding.next.setOnClickListener {

            when(lessonNo){
                1 -> findNavController().navigate(VocabularyFragmentDirections.actionVocabularyFragmentToReading1Fragment())
                2 -> findNavController().navigate(VocabularyFragmentDirections.actionVocabularyFragmentToReading2Fragment())
                3 -> findNavController().navigate(VocabularyFragmentDirections.actionVocabularyFragmentToReading3Fragment())
                4 -> findNavController().navigate(VocabularyFragmentDirections.actionVocabularyFragmentToReading4Fragment())
                5 -> findNavController().navigate(VocabularyFragmentDirections.actionVocabularyFragmentToReading5Fragment())
                6 -> findNavController().navigate(VocabularyFragmentDirections.actionVocabularyFragmentToReading6Fragment())
                7 -> findNavController().navigate(VocabularyFragmentDirections.actionVocabularyFragmentToReading7Fragment())
                8 -> findNavController().navigate(VocabularyFragmentDirections.actionVocabularyFragmentToReading8Fragment())
                9 -> findNavController().navigate(VocabularyFragmentDirections.actionVocabularyFragmentToReading9Fragment())
                10 -> findNavController().navigate(VocabularyFragmentDirections.actionVocabularyFragmentToReading10Fragment())
                11 -> findNavController().navigate(VocabularyFragmentDirections.actionVocabularyFragmentToReading11Fragment())
                12 -> findNavController().navigate(VocabularyFragmentDirections.actionVocabularyFragmentToReading12Fragment())
                13 -> findNavController().navigate(VocabularyFragmentDirections.actionVocabularyFragmentToReading13Fragment())
                14 -> findNavController().navigate(VocabularyFragmentDirections.actionVocabularyFragmentToReading14Fragment())
                15 -> findNavController().navigate(VocabularyFragmentDirections.actionVocabularyFragmentToReading15Fragment())
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.vocab_menu,menu)
        inflater.inflate(R.menu.star_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.study_mode -> {
                if(a == 0){
                    a = 1
                    item.setIcon(R.drawable.sun_glasses_white_crossed)
                    binding.recyclerView.adapter = VocabularyAdapter(lessonNo,a, vocabAll)
                    Toast.makeText(requireContext(),"Study Mode Enabled",Toast.LENGTH_SHORT).show()
                }else{
                    a = 0
                    item.setIcon(R.drawable.sun_glasses_white)
                    binding.recyclerView.adapter = VocabularyAdapter(lessonNo,a, vocabAll)
                    Toast.makeText(requireContext(),"Study Mode Disabled",Toast.LENGTH_SHORT).show()
                }
            }
            R.id.star -> {
                findNavController().navigate(VocabularyFragmentDirections.actionVocabularyFragmentToSavedFragment())
            }
        }
        return super.onOptionsItemSelected(item)
    }

}