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
                    binding.pinyinS1.text = "Jiějiě gěi wàigōng dǎ diànhuà."
                    binding.pinyinS2.text = "Nǎinai gěi wǒ jiǎng gùshì."
                    binding.pinyinS3.text = "Wǒ gēn māmā qù shāngdiàn."
                    binding.pinyinS4.text = "Mèimei gēn jiějiě qù zhíwùyuán."
                    binding.pinyinS5.text = "Xiǎo huá gēn xiǎo hóng yīqǐ xià qí."
                    binding.pinyinS6.text = "Gēgē gēn dìdì yīqǐ dǎ pīngpāng qiú."
                    binding.pinyinHeading.text = "Xiǎohuā Māo Gěi Wàipó Sòng Yú"
                    binding.pinyinPassage.text = "          Māo māmā ràng xiǎohuā māo qù wàipó jiā, gěi wàipó sòng liǎng tiáo yú. Xiǎohuā māo shuō:“Māmā, wǒ bù gǎn zìjǐ qù, nǐ gēn wǒ yīqǐ qù ba.” Māmā shuō:“Nǐ shìgè yǒnggǎn de háizi, nǐ yǐjīng zhǎng dàle, zìjǐ qù ba. Māmā zàijiā xǐ yīfú.” Xiǎohuā māo shuō:“Hǎo ba, māmā, zàijiàn!”"
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