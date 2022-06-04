package com.enzhongwen.hanyu3

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.enzhongwen.hanyu3.databinding.FragmentSettingsBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class SettingsFragment: Fragment() {

    private lateinit var myPreference: MyPreference

    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        myPreference = MyPreference(requireContext())

        binding = FragmentSettingsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adView: AdView = view.findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)


        if(binding.eng.text == "English"){
            binding.eng.isChecked = true
        }else if(binding.myan.text == "မြန်မာ"){
            binding.myan.isChecked = true
        }else{
            binding.zawgyi.isChecked = true
        }

        binding.darkMode.isChecked = binding.onOff.text == "On"

        val alertDialog1 = MaterialAlertDialogBuilder(requireContext()).setTitle("Hanyu 2")
            .setCancelable(false)
            .setMessage("You need to restart the app to change app language.")
            .setNegativeButton("cancel"){_,_->
                if(binding.eng.text == "English"){
                    binding.eng.isChecked = true
                }else if(binding.myan.text == "မြန်မာ"){
                    binding.myan.isChecked = true
                }else{
                    binding.zawgyi.isChecked = true
                }
            }

        val alertDialog2 = MaterialAlertDialogBuilder(requireContext()).setTitle("Dark Theme")
            .setCancelable(false)
            .setMessage("You need to restart the app to apply or remove Dark Theme!")
            .setNegativeButton("cancel"){ _,_ ->
                binding.darkMode.isChecked = binding.onOff.text == "On"
            }

        binding.eng.setOnClickListener {
            alertDialog1.setPositiveButton("Restart"){_,_->
                myPreference.setLoginCount("en")
                requireContext().startActivity(Intent(requireContext(),MainActivity::class.java))
                activity?.finish()
            }.show()
        }

        binding.myan.setOnClickListener {
            alertDialog1.setPositiveButton("Restart"){_,_->
                myPreference.setLoginCount("my")
                requireContext().startActivity(Intent(requireContext(),MainActivity::class.java))
                activity?.finish()
            }.show()
        }

        binding.zawgyi.setOnClickListener {
            alertDialog1.setPositiveButton("Restart"){_,_->
                myPreference.setLoginCount("ksf")
                requireContext().startActivity(Intent(requireContext(),MainActivity::class.java))
                activity?.finish()
            }.show()
        }

        binding.darkMode.setOnClickListener{
            if(binding.onOff.text == "Off"){
                alertDialog2.setPositiveButton("Restart"){ _,_ ->
                    requireContext().startActivity(Intent(requireContext(),MainActivity::class.java))
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    activity?.finish()
                }.show()
            }else{
                alertDialog2.setPositiveButton("Restart"){ _,_ ->
                    requireContext().startActivity(Intent(requireContext(),MainActivity::class.java))
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    activity?.finish()
                }.show()
            }
        }

    }
}