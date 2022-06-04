package com.enzhongwen.hanyu3

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.enzhongwen.hanyu3.database.SavedViewModel
import com.enzhongwen.hanyu3.databinding.FragmentSavedBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class SavedFragment : Fragment() {

    private lateinit var binding: FragmentSavedBinding

    private var a = 0

    private val mSavedViewModel: SavedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        binding = FragmentSavedBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = VocabularyAdapter(16,a, currentList)
        binding.recyclerView3.adapter = adapter
        binding.recyclerView3.layoutManager = LinearLayoutManager(context)

        mSavedViewModel.readAllData.observe(viewLifecycleOwner, Observer { saved ->
            adapter.setData(saved)
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.saved_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete -> {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Delete All")
                    .setMessage("You are gonna delete all items in this saved list! Do you want to Continue?")
                    .setNegativeButton("No"){_,_->

                    }
                    .setPositiveButton("Delete"){_,_->
                        mSavedViewModel.deleteAll()
                    }
                    .setIcon(R.drawable.ic_delete_red)
                    .setCancelable(false)
                    .show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}