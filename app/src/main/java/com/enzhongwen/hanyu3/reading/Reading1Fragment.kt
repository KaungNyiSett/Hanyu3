package com.enzhongwen.hanyu3.reading

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import com.enzhongwen.hanyu3.R
import com.enzhongwen.hanyu3.databinding.FragmentReading1Binding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class Reading1Fragment: Fragment() {

    lateinit var binding: FragmentReading1Binding
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
        binding = FragmentReading1Binding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.listen.setOnClickListener {
            val a = MediaPlayer.create(requireContext(), R.raw.l1_s)
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
                    binding.pinyinS1.text = "J??nti??n j?? yu?? j?? r???"
                    binding.pinyinS2.text = "J??nti??n y?? yu?? y?? r??."
                    binding.pinyinS3.text = "J??nti??n sh?? yu??nd??n."
                    binding.pinyinS4.text = "Zu??ti??n sh?? j?? yu?? j?? r???"
                    binding.pinyinS5.text = "Zu??ti??n sh?? sh??'??r yu?? ??rsh??w?? r??."
                    binding.pinyinS6.text = "Zu??ti??n sh?? sh??ngd??n ji??."
                    binding.pinyinS7.text = "M??ngti??n sh?? j?? yu?? j?? r???"
                    binding.pinyinS8.text = "M??ngti??n sh?? sh?? yu?? y?? r??."
                    binding.pinyinS9.text = "M??ngti??n sh?? zh??nggu?? de gu??q??ng ji??."
                    binding.pinyinHeading.text = "J??nti??n J?? Yu?? J?? R??"
                    binding.pinyinPassage.text = "          J??nti??n s?? yu?? li?? r??, ji?? q?? ji??sh??le, xu??xi??o k??ixu??le. Q??ni??n, w?? sh??ng xi??oxu?? y?? ni??nj??. J??nni??n, w?? sh??ng ??r ni??nj??le, d??d?? y?? sh??ngxu??le. X??n xu??q??, w??men f??le h??ndu?? x??nsh??, w?? h?? d??d?? d??u h??n g??ox??ng."
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