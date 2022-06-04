package com.enzhongwen.hanyu3.reading

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import com.enzhongwen.hanyu3.R
import com.enzhongwen.hanyu3.databinding.FragmentReading5Binding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class Reading5Fragment: Fragment() {

    lateinit var binding: FragmentReading5Binding

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
        binding = FragmentReading5Binding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.reading_menu,menu)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.listen.setOnClickListener {
            val a = MediaPlayer.create(requireContext(), R.raw.l5_s)
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
        binding.pinyinS1.text = "Xiǎo líng: Xiǎo huá, nǐ de shēngrì shì jǐ yuè jǐ hào?"
        binding.pinyinS2.text = "Xiǎo huá: Wǔ yuè liù hào."
        binding.pinyinS3.text = "Xiǎo líng: Jīntiān shì wǔ yuè wǔ hào, míngtiān jiùshì nǐ de shēngrì!"
        binding.pinyinS4.text = "Xiǎo huá: Shì a! Jiějiě, míngtiān hé wǒ yīqǐ qù yóulè chǎng, hǎo ma?"
        binding.pinyinS5.text = "Xiǎo líng: Hǎo a! Jǐ diǎn qù?"
        binding.pinyinS6.text = "Xiǎo huá: Jiǔ diǎn bàn zěnme yàng?"
        binding.pinyinS7.text = "Xiǎo líng: Hǎo de!"
        binding.pinyinHeading.text = "Běijīng De Sìjì"
        binding.pinyinPassage.text = "          Běijīng shì zhōngguó de shǒudū, tā shì yīgè měilì de chéngshì. Nà'er yǒu sì gè jìjié: Sān yuè, sì yuè, wǔ yuè shì chūntiān; liù yuè, qī yuè, bā yuè shì xiàtiān; jiǔ yuè, shí yuè, shíyī yuè shì qiūtiān; shí'èr yuè, yī yuè, èr yuè shì dōngtiān. Běijīng de chūntiān chángcháng guā fēng. Xiàtiān fēicháng rè. Qiūtiān hěn liángkuai, bù lěng yě bù rè. Dōngtiān hěn lěng, yǒu shíhòu xià xuě."
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
        binding.pinyinHeading.text = null
        binding.pinyinPassage.text = null
        item.setIcon(R.drawable.white_a)
    }
}
}


return super.onOptionsItemSelected(item)
}
}