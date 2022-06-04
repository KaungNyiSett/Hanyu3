package com.enzhongwen.hanyu3.reading

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import com.enzhongwen.hanyu3.R
import com.enzhongwen.hanyu3.databinding.FragmentReading14Binding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class Reading14Fragment: Fragment() {

    lateinit var binding: FragmentReading14Binding

    private var a = 0
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
                Log.d("TAG", "Ad failed to load")
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                Log.d("TAG", "Ad was loaded.")
                mInterstitialAd = interstitialAd
                    mInterstitialAd?.show(requireActivity())
            }
        })
        binding = FragmentReading14Binding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listen.setOnClickListener {
            val a = MediaPlayer.create(requireContext(), R.raw.l14_s)
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
                    binding.pinyinS1.text = "Māmā zuò huǒchē qù wàipó jiā."
                    binding.pinyinS2.text = "Gēgē zuò fēijī qù xiānggǎng."
                    binding.pinyinS3.text = "Wǒmen yòng kuàizi chīfàn."
                    binding.pinyinS4.text = "Shūshu yòng shǒujī shàngwǎng."
                    binding.pinyinS5.text = "Wǒ bāng lǎoshī cā hēibǎn."
                    binding.pinyinS6.text = "Jiějiě bāng māmā xǐ wǎn."
                    binding.pinyinHeading.text = "Wǒmen Zuòchē Qù Dòngwùyuán"
                    binding.pinyinPassage.text = "          Jīntiān, wǒmen zuòchē qù dòngwùyuán. Dòngwùyuán li yǒu hěnduō zhǒng dòngwù. Dà hóuzi jiào xiǎo hóuzi páshān, lǎohǔ māmā bāng xiǎo lǎohǔ xǐliǎn, dà xiàng yòng cháng bízi xīshuǐ, dà xióngmāo zài cǎodì chī zhúzi. Tóngxuémen dōu hěn xǐhuān tāmen, dàjiā shuō:“Dòngwù shì wǒmen de hǎo péngyǒu.”"
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