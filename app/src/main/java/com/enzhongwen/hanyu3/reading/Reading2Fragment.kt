package com.enzhongwen.hanyu3.reading

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import com.enzhongwen.hanyu3.R
import com.enzhongwen.hanyu3.databinding.FragmentReading2Binding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class Reading2Fragment: Fragment() {

    lateinit var binding: FragmentReading2Binding

    var a = 0

    var mInterstitialAd: InterstitialAd? = null

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

        binding = FragmentReading2Binding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.reading_menu,menu)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.listen.setOnClickListener {
            val a = MediaPlayer.create(requireContext(), R.raw.l2_s)
            a.start()
            a.setOnCompletionListener {
                a.release()
            }
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

when(item.itemId){
    R.id.pinyin_on_off ->{
        if(a == 0){
            item.setIcon(R.drawable.white_a_crossed)
            binding.pinyinS1.text = "J??nti??n x??ngq?? j???"
            binding.pinyinS2.text = "M??ngti??n x??ngq?? j???"
            binding.pinyinS3.text = "H??uti??n x??ngq?? j???"
            binding.pinyinS4.text = "J??nti??n x??ngq??s??n."
            binding.pinyinS5.text = "Zu??ti??n x??ngq??\'??r."
            binding.pinyinS6.text = "Qi??nti??n x??ngq?? y??."
            binding.pinyinS7.text = "X??ngq??s??n y??u zh??ng w??n k??."
            binding.pinyinS8.text = "X??ngq??s?? y??u sh??xu?? k??."
            binding.pinyinS9.text = "X??ngq??w?? y??u m??ish?? k??."
            binding.pinyinHeading.text = "M??ngti??n x??ngq?? y??"
            binding.pinyinPassage.text = "          X??ngq??ti??n sh??ngw??, m??m?? w??n xi??om??ng:???M??ngti??n n?? y??u t??y?? k?? ma???? Xi??om??ng shu??:???M??ngti??n sh?? x??ngq?? y??, w??men b??n y??u zh??ng w??n k?? h?? m??ish?? k??, m??iy??u t??y?? k??. H??uti??n x??ngq??'??r, y??u t??y?? k??.???"
            a = 1
        }else{
            a = 0
            binding.pinyinS1.text = null
            binding.pinyinS2.text = null
            binding.pinyinS3.text = null
            binding.pinyinS4.text = null
            binding.pinyinS5.text = null
            binding.pinyinS6.text = null
            binding.pinyinS7.text = null
            binding.pinyinS8.text = null
            binding.pinyinS9.text = null
            binding.pinyinHeading.text = null
            binding.pinyinPassage.text = null
            item.setIcon(R.drawable.white_a)
        }
    }
}


return super.onOptionsItemSelected(item)
}
}