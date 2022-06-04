package com.enzhongwen.hanyu3.reading

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import com.enzhongwen.hanyu3.R
import com.enzhongwen.hanyu3.databinding.FragmentReading8Binding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class Reading8Fragment: Fragment() {

    lateinit var binding: FragmentReading8Binding

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
        binding = FragmentReading8Binding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.reading_menu,menu)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.listen.setOnClickListener {
            val a = MediaPlayer.create(requireContext(), R.raw.l8_s)
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
                    binding.pinyinS1.text = "Yéyé jiào xiǎo huá xiě hànzì."
                    binding.pinyinS2.text = "Lǐ lǎoshī jiào wǒmen chànggē."
                    binding.pinyinS3.text = "Xiǎo líng qǐng xiǎo huá chī shuǐguǒ."
                    binding.pinyinS4.text = "Gūgū qǐng wǒmen kàn zájì."
                    binding.pinyinS5.text = "Lǎoshī ràng wǒ huídá wèntí."
                    binding.pinyinS6.text = "Māmā ràng wǒ xǐ wàzi."
                    binding.pinyinS7.text = "Zhù nǐ shēngrì kuàilè!"
                    binding.pinyinS8.text = "Zhù nǐ zhōngqiū jié kuàilè!"
                    binding.pinyinS9.text = "Zhù nǐ xīnnián kuàilè!"
                    binding.pinyinHeading.text = "Nǎinai Qǐng Wǒmen Chī Zhōngguó Cài"
                    binding.pinyinPassage.text = "          Jīntiān zhōngqiū jié, wǒmen hé nǎinai yīqǐguò. Nǎinai fēicháng gāoxìng, tā qǐng wǒmen chī zhōngguó cài. Māmā hé bàba sòng gěi nǎinai yī hé cháyè, wǒ hé dìdì mèimei sòng gěi nǎinai yītiáo wéijīn. Wǒmen yīqǐ zhù tā zhōngqiū jié kuàilè!"
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