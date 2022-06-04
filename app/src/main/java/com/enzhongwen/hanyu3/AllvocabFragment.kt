package com.enzhongwen.hanyu3

import android.os.Bundle
import android.view.*
import android.widget.Toast
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.enzhongwen.hanyu3.database.Saved
import com.enzhongwen.hanyu3.database.SavedViewModel
import com.enzhongwen.hanyu3.databinding.FragmentAllvocabBinding

class AllvocabFragment: Fragment() {

    private lateinit var binding: FragmentAllvocabBinding

    private val mSavedViewModel: SavedViewModel by activityViewModels()

    var filtered: List<Saved>? = null

    private var a = 0

    val adapter = VocabularyAdapter(0,a, vocabAll)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = FragmentAllvocabBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mSavedViewModel.readAllData.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.vocab_menu, menu)
        inflater.inflate(R.menu.search_menu, menu)

        val search = menu.findItem(R.id.search)
        val searchView = search.actionView as SearchView
        searchView.queryHint = "Search"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                filtered = vocabAll.filter {
                    it.word.contains(newText!!) || newText.contains(it.word)
                }
                binding.recyclerView.adapter = VocabularyAdapter(0,a, filtered!!)

                return true
            }
        })

        }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.study_mode -> {
                if(a == 0){
                    a = 1
                    item.setIcon(R.drawable.sun_glasses_white_crossed)
                    if(filtered != null){
                        binding.recyclerView.adapter = VocabularyAdapter(0,a, filtered!!)
                    }else{
                        binding.recyclerView.adapter = VocabularyAdapter(0,a, vocabAll)
                    }
                    Toast.makeText(requireContext(),"Study Mode Enabled", Toast.LENGTH_SHORT).show()
                }else{
                    a = 0
                    item.setIcon(R.drawable.sun_glasses_white)
                    if(filtered != null){
                        binding.recyclerView.adapter = VocabularyAdapter(0,a, filtered!!)
                    }else{
                        binding.recyclerView.adapter = VocabularyAdapter(0,a, vocabAll)
                    }
                    Toast.makeText(requireContext(),"Study Mode Disabled", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

}