package com.enzhongwen.hanyu3.reading

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import com.enzhongwen.hanyu3.R
import com.enzhongwen.hanyu3.databinding.FragmentReading4Binding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class Reading4Fragment: Fragment() {

    lateinit var binding: FragmentReading4Binding

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
        binding = FragmentReading4Binding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.reading_menu,menu)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.listen.setOnClickListener {
            val a = MediaPlayer.create(requireContext(), R.raw.l4_s)
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
        binding.pinyinS1.text = "Jīntiān de tiānqì zěnme yàng?"
        binding.pinyinS2.text = "Jīntiān de tiānqì fēicháng hǎo."
        binding.pinyinS3.text = "Zhè tiáo qúnzi zěnme yàng?"
        binding.pinyinS4.text = "Zhè tiáo qúnzi hěn hǎokàn."
        binding.pinyinS5.text = "Zhè jiàn yīfú bù dà yě bù xiǎo, hěn héshì."
        binding.pinyinS6.text = "Jīntiān de tiānqì bù lěng yě bù rè, hěn shūfú."
        binding.pinyinHeading.text = "Jīntiān De Tiānqì Zěnme Yàng"
        binding.pinyinPassage.text = "          Yī nián yǒu sì gè jìjié: Chūntiān, xiàtiān, qiūtiān hé dōngtiān. Chūntiān hěn nuǎnhuo, xiàtiān hěn rè, qiūtiān hěn liángkuai, dōngtiān hěn lěng. Xiànzài shì chūntiān, tiānqì hěn hǎo, bù lěng yě bù rè."
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