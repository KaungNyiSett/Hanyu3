package com.enzhongwen.hanyu3.reading

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import com.enzhongwen.hanyu3.R
import com.enzhongwen.hanyu3.databinding.FragmentReading13Binding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class Reading13Fragment: Fragment() {

    lateinit var binding: FragmentReading13Binding

    var mInterstitialAd: InterstitialAd? = null

    var a = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {setHasOptionsMenu(true)

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
        binding = FragmentReading13Binding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listen.setOnClickListener {
            val a = MediaPlayer.create(requireContext(), R.raw.l13_s)
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
                    binding.pinyinS1.text = "Wǒ qù dòngwùyuán kàn xióngmāo."
                    binding.pinyinS2.text = "Māmā qù shāngdiàn mǎi dōngxī."
                    binding.pinyinS3.text = "Xiǎo hóng lái wǒjiā kàn diànshì."
                    binding.pinyinS4.text = "Bàba lái xuéxiào jiē wǒ."
                    binding.pinyinS5.text = "Tā dào yóujú qǔ bāoguǒ."
                    binding.pinyinS6.text = "Tā dào bàngōngshì zhǎo wáng lǎoshī."
                    binding.pinyinS7.text = "Wǒ huí jiàoshì ná yàoshi."
                    binding.pinyinS8.text = "Wǒ huí jiā huàn yīfú."
                    binding.pinyinHeading.text = " Yéyé Qù Gōngyuán Dǎ Tàijí Quán"
                    binding.pinyinPassage.text = "          Yéyé hé bàba dōu hěn xǐhuān duànliàn shēntǐ. Yéyé ài dǎ tàijí quán, zǎoshang tā chángcháng qù gōngyuán dǎ tàijí quán. Bàba xǐhuān pǎobù, zhōumò wǒ chángcháng gēn tā yīqǐ dào hǎibiān pǎobù. Wǒmen de shēntǐ dōu hěn jiànkāng."
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