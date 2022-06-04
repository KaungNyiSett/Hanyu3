package com.enzhongwen.hanyu3.reading

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import com.enzhongwen.hanyu3.R
import com.enzhongwen.hanyu3.databinding.FragmentReading10Binding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class Reading10Fragment: Fragment() {

    lateinit var binding: FragmentReading10Binding

    var mInterstitialAd: InterstitialAd? = null

    private var a = 0

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
        binding = FragmentReading10Binding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.reading_menu,menu)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.listen.setOnClickListener {
            val a = MediaPlayer.create(requireContext(), R.raw.l10_s)
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
                    binding.pinyinS1.text = "Xiǎo shānyáng: Jīntiān shì wǒ de shēngrì, wǒ qǐng péngyǒumen chīfàn."
                    binding.pinyinS2.text = "Xiǎo māo: Xiǎo shānyáng, xiǎo shānyáng, wǒ láile! Wǒ sòng gěi nǐ yī lán qīngcài."
                    binding.pinyinS3.text = "Xiǎo shānyáng: A, qīngcài! Wǒ ài chī qīngcài, xièxiè!"
                    binding.pinyinS4.text = "Xiǎo tùzǐ: Xiǎo shānyáng, xiǎo shānyáng, wǒ láile! Wǒ sòng gěi nǐ yīgè húluóbo!"
                    binding.pinyinS5.text = "Xiǎo shānyáng: A, húluóbo! Wǒ yě ài chī húluóbo, xièxiè nǐ!"
                    binding.pinyinS6.text = "Xiǎo māo, xiǎo tùzǐ: Zhù nǐ shēngrì kuàilè!"
                    binding.pinyinHeading.text = "Zhù Nǐ Shēngrì Kuàilè"
                    binding.pinyinPassage.text = "           Xiǎo māo, xiǎo tùzǐ hé xiǎo shānyáng shì hǎo péngyǒu. Jīntiān shì xiǎo shānyáng de shēngrì, tā qǐng péngyǒumen chīfàn. Xiǎo māo ài chī yú, xiǎo shānyáng gěi tā zhǔnbèile yītiáo yú. Xiǎo tùzǐ ài chī húluóbo, xiǎo shānyáng gěi tā zhǔnbèile yīgè húluóbo. Xiǎo māo hé xiǎo tùzǐ sòng gěi xiǎo shānyáng yī lán qīngcài, tāmen yīqǐ duì xiǎo shānyáng shuō: “Zhù nǐ shēngrì kuàilè!”"
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