package com.enzhongwen.hanyu3.reading

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import com.enzhongwen.hanyu3.R
import com.enzhongwen.hanyu3.databinding.FragmentReading12Binding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class Reading12Fragment: Fragment() {

    private var a = 0

    var mInterstitialAd: InterstitialAd? = null

    lateinit var binding: FragmentReading12Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        var adRequest = AdRequest.Builder().build()
        InterstitialAd.load(requireContext(),"ca-app-pub-5467193675789833/1145218253", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                Log.d("TAG", adError?.message)
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                Log.d("TAG", "Ad was loaded.")
                mInterstitialAd = interstitialAd
                if (mInterstitialAd != null) {
                    mInterstitialAd?.show(requireActivity())
                } else {
                    Log.d("TAG", "The interstitial ad wasn't ready yet.")
                }
            }
        })
        binding = FragmentReading12Binding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listen.setOnClickListener {
            val a = MediaPlayer.create(requireContext(), R.raw.l12_s)
            a.start()
            a.setOnCompletionListener {
                a.release()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.reading_menu,menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.pinyin_on_off ->{
                if(a == 0){
                    item.setIcon(R.drawable.white_a_crossed)
                    binding.pinyinS1.text = "Ji??ji?? g??i w??ig??ng d?? di??nhu??."
                    binding.pinyinS2.text = "N??inai g??i w?? ji??ng g??sh??."
                    binding.pinyinS3.text = "W?? g??n m??m?? q?? sh??ngdi??n."
                    binding.pinyinS4.text = "M??imei g??n ji??ji?? q?? zh??w??yu??n."
                    binding.pinyinS5.text = "Xi??o hu?? g??n xi??o h??ng y??q?? xi?? q??."
                    binding.pinyinS6.text = "G??g?? g??n d??d?? y??q?? d?? p??ngp??ng qi??."
                    binding.pinyinHeading.text = "Xi??ohu?? M??o G??i W??ip?? S??ng Y??"
                    binding.pinyinPassage.text = "          M??o m??m?? r??ng xi??ohu?? m??o q?? w??ip?? ji??, g??i w??ip?? s??ng li??ng ti??o y??. Xi??ohu?? m??o shu??:???M??m??, w?? b?? g??n z??j?? q??, n?? g??n w?? y??q?? q?? ba.??? M??m?? shu??:???N?? sh??g?? y??ngg??n de h??izi, n?? y??j??ng zh??ng d??le, z??j?? q?? ba. M??m?? z??iji?? x?? y??f??.??? Xi??ohu?? m??o shu??:???H??o ba, m??m??, z??iji??n!???"
                    a = 1
                }else{
                    a = 0
                    binding.pinyinS1.text = null
                    binding.pinyinS2.text = null
                    binding.pinyinS3.text = null
                    binding.pinyinS4.text = null
                    binding.pinyinS5.text = null
                    binding.pinyinS6.text = null
                    binding.pinyinHeading.text = null
                    binding.pinyinPassage.text = null
                    item.setIcon(R.drawable.white_a)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

}