package com.enzhongwen.hanyu3.reading

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import com.enzhongwen.hanyu3.R
import com.enzhongwen.hanyu3.databinding.FragmentReading3Binding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class Reading3Fragment: Fragment() {

    lateinit var binding: FragmentReading3Binding

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
        binding = FragmentReading3Binding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.reading_menu,menu)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.listen.setOnClickListener {
            val a = MediaPlayer.create(requireContext(), R.raw.l3_s)
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
        binding.pinyinS1.text = "Xi??nz??i j?? di??n?"
        binding.pinyinS2.text = "Xi??nz??i b?? di??n."
        binding.pinyinS3.text = "Xi??nz??i sh??y?? di??n l??ng w?? f??n."
        binding.pinyinS4.text = "Xi??nz??i sh??y?? di??n y?? k??."
        binding.pinyinS5.text = "Xi??nz??i sh??y?? di??n b??n."
        binding.pinyinS6.text = "Xi??nz??i ch?? sh??f??n sh??'??r di??n."
        binding.pinyinS7.text = "N?? j?? di??n x??z??o?"
        binding.pinyinS8.text = "W?? z??oshang q?? di??n sh?? f??n x??z??o."
        binding.pinyinS9.text = "N?? j?? di??n shu??ji??o???"
        binding.pinyinS10.text = "W?? w??nsh??ng b?? di??n s??sh?? shu??ji??o."
        binding.pinyinHeading.text = "Xi??nz??i B?? Di??n B??n"
        binding.pinyinPassage.text = "          W?? z??oshang q?? di??n sh??f??n q??chu??ng, q?? di??n b??n ch?? z??of??n. Ch?? sh?? f??n b?? di??n, w?? h?? d??d?? y??q?? q?? xu??xi??o. W??men b?? di??n b??n k??ish?? sh??ngk??, zh??ngw?? sh??'??r di??n ch?? w??f??n. Xi??w?? s??n di??n, w?? h?? d??d?? y??q?? hu?? ji??."
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
        binding.pinyinS10.text = null
        binding.pinyinHeading.text = null
        binding.pinyinPassage.text = null
        item.setIcon(R.drawable.white_a)
    }
}
}


return super.onOptionsItemSelected(item)
}
}