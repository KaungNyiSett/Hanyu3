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
        binding.pinyinS1.text = "Xi??o l??ng: Xi??o hu??, n?? de sh??ngr?? sh?? j?? yu?? j?? h??o?"
        binding.pinyinS2.text = "Xi??o hu??: W?? yu?? li?? h??o."
        binding.pinyinS3.text = "Xi??o l??ng: J??nti??n sh?? w?? yu?? w?? h??o, m??ngti??n ji??sh?? n?? de sh??ngr??!"
        binding.pinyinS4.text = "Xi??o hu??: Sh?? a! Ji??ji??, m??ngti??n h?? w?? y??q?? q?? y??ul?? ch??ng, h??o ma?"
        binding.pinyinS5.text = "Xi??o l??ng: H??o a! J?? di??n q???"
        binding.pinyinS6.text = "Xi??o hu??: Ji?? di??n b??n z??nme y??ng?"
        binding.pinyinS7.text = "Xi??o l??ng: H??o de!"
        binding.pinyinHeading.text = "B??ij??ng De S??j??"
        binding.pinyinPassage.text = "          B??ij??ng sh?? zh??nggu?? de sh??ud??, t?? sh?? y??g?? m??il?? de ch??ngsh??. N??'er y??u s?? g?? j??ji??: S??n yu??, s?? yu??, w?? yu?? sh?? ch??nti??n; li?? yu??, q?? yu??, b?? yu?? sh?? xi??ti??n; ji?? yu??, sh?? yu??, sh??y?? yu?? sh?? qi??ti??n; sh??'??r yu??, y?? yu??, ??r yu?? sh?? d??ngti??n. B??ij??ng de ch??nti??n ch??ngch??ng gu?? f??ng. Xi??ti??n f??ich??ng r??. Qi??ti??n h??n li??ngkuai, b?? l??ng y?? b?? r??. D??ngti??n h??n l??ng, y??u sh??h??u xi?? xu??."
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