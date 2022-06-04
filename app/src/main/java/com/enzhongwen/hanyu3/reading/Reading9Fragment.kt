package com.enzhongwen.hanyu3.reading

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import com.enzhongwen.hanyu3.R
import com.enzhongwen.hanyu3.databinding.FragmentReading9Binding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class Reading9Fragment: Fragment() {

    var a = 0

    lateinit var binding: FragmentReading9Binding

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
        binding = FragmentReading9Binding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.reading_menu,menu)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.listen.setOnClickListener {
            val a = MediaPlayer.create(requireContext(), R.raw.l9_s)
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
                    binding.pinyinS1.text = "Nǎinai xǐhuān tīng jīngjù."
                    binding.pinyinS2.text = "Xiǎoqiáng xǐhuān kàn diànyǐng."
                    binding.pinyinS3.text = "Jiějiě ài chànggē."
                    binding.pinyinS4.text = "Dìdì ài chī táng."
                    binding.pinyinS5.text = "Mèimei ài huà huà er, bù ài xiězì."
                    binding.pinyinS6.text = "Yéyé xǐhuān hē chá, bù xǐhuān hē kělè."
                    binding.pinyinHeading.text = "Xiǎohóng Xǐhuān Kàn Diànyǐng"
                    binding.pinyinPassage.text = "          Xiǎo hóng xǐhuān kàn diànyǐng, tā tèbié ài kàn zhòng guó diànyǐng, xiǎo huá yě xǐhuān kàn. Jīntiān diànyǐngyuàn yǎn zhōngguó diànyǐng, tīng shuō hěn yǒuyìsi. Xiǎo hóng hěn gāoxìng, tā dǎsuàn qǐng xiǎo huá yīqǐ qù kàn diànyǐng."
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