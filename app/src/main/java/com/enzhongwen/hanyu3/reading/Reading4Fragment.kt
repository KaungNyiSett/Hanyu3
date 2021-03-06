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
        binding.pinyinS1.text = "J??nti??n de ti??nq?? z??nme y??ng?"
        binding.pinyinS2.text = "J??nti??n de ti??nq?? f??ich??ng h??o."
        binding.pinyinS3.text = "Zh?? ti??o q??nzi z??nme y??ng?"
        binding.pinyinS4.text = "Zh?? ti??o q??nzi h??n h??ok??n."
        binding.pinyinS5.text = "Zh?? ji??n y??f?? b?? d?? y?? b?? xi??o, h??n h??sh??."
        binding.pinyinS6.text = "J??nti??n de ti??nq?? b?? l??ng y?? b?? r??, h??n sh??f??."
        binding.pinyinHeading.text = "J??nti??n De Ti??nq?? Z??nme Y??ng"
        binding.pinyinPassage.text = "          Y?? ni??n y??u s?? g?? j??ji??: Ch??nti??n, xi??ti??n, qi??ti??n h?? d??ngti??n. Ch??nti??n h??n nu??nhuo, xi??ti??n h??n r??, qi??ti??n h??n li??ngkuai, d??ngti??n h??n l??ng. Xi??nz??i sh?? ch??nti??n, ti??nq?? h??n h??o, b?? l??ng y?? b?? r??."
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