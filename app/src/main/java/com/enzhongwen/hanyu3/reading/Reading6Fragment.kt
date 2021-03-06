package com.enzhongwen.hanyu3.reading

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import com.enzhongwen.hanyu3.R
import com.enzhongwen.hanyu3.databinding.FragmentReading6Binding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class Reading6Fragment: Fragment() {

    lateinit var binding: FragmentReading6Binding

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
        binding = FragmentReading6Binding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.reading_menu,menu)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.listen.setOnClickListener {
            val a = MediaPlayer.create(requireContext(), R.raw.l6_s)
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
        binding.pinyinS1.text = "B??ba ji??o d??d?? sh??f??."
        binding.pinyinS2.text = "M??m?? ji??o m??imei zh??ngw??n g??."
        binding.pinyinS3.text = "T??y?? l??osh?? ji??o w??men zh??nggu?? g??ngf??."
        binding.pinyinS4.text = "Sh??shu ch??ng sh??ngw??ng, h??i ch??ng k??n di??nsh??."
        binding.pinyinS5.text = "Xi??ngzi li y??u y??xi?? y??f??, h??i y??u y??xi?? r??y??ngp??n."
        binding.pinyinS6.text = "M??imei x??hu??n ti??n de, h??i x??hu??n su??n de."
        binding.pinyinHeading.text = "Ch??n L??osh?? Ji??o W??men Zh??ngw??n K??"
        binding.pinyinPassage.text = "          Zh?? xu??q??, ch??n l??osh?? ji??o w??men zh??ngw??n k??, l?? l??osh?? ji??o w??men y??nyu?? k??. J??nti??n x??ngq?? y??, sh??ngw?? w??men b??n y??u zh??ng w??n k??. Ch??n l??osh?? ji??o w??men sh??ngc??, h??i ji??o w??men j??zi h?? k??w??n. Xi??w?? w??men y??u y??nyu?? k??, l?? l??osh?? ji??o w??men zh??ngw??n g??. D??ji?? d??u h??n k??ix??n."
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