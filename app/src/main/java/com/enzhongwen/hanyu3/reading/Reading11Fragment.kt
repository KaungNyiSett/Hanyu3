package com.enzhongwen.hanyu3.reading

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import com.enzhongwen.hanyu3.R
import com.enzhongwen.hanyu3.databinding.FragmentReading11Binding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class Reading11Fragment: Fragment() {

    lateinit var binding: FragmentReading11Binding

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
        binding = FragmentReading11Binding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listen.setOnClickListener {
            val a = MediaPlayer.create(requireContext(), R.raw.l11_s)
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
                    binding.pinyinS1.text = "Wǒmen zài hǎibiān wán shāzi."
                    binding.pinyinS2.text = "Gēgē zài zhōngguó xué hànyǔ."
                    binding.pinyinS3.text = "Nǐ bàba zài nǎ'er gōngzuò?"
                    binding.pinyinS4.text = "Nǐ mèimei zài nǎ'er shàngxué?"
                    binding.pinyinS5.text = "Zhōngguó cài hào chī jíle!"
                    binding.pinyinS6.text = "Zhè fú huà er hǎokàn jíle!"
                    binding.pinyinS7.text = "Bàba bùzài gōngsī gōngzuò."
                    binding.pinyinS8.text = "Jīntiān wǒ bù zàijiā chī wǎnfàn."
                    binding.pinyinHeading.text = "Wǒmen Zài Hǎibiān Wán Shāzi"
                    binding.pinyinPassage.text = "Zuótiān shì xīngqítiān, bàba māmā bù shàngbān. Wǒmen quánjiā yì qǐ qù hǎibiān wán. Hǎibiān de fēngjǐng měi jíle! Tiān hěn lán, hǎi yě hěn lán, hěnduō rén zài hǎilǐ yóuyǒng. Bàba hé māmā zài hǎibiān dǎ páiqiú, wǒ hé dìdì zài hǎibiān wán shāzi, mèimei zài hǎibiān shài tàiyáng. Zhè yītiān hǎowán jíle!"
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
                    binding.pinyinHeading.text = null
                    binding.pinyinPassage.text = null
                    item.setIcon(R.drawable.white_a)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

}