package com.enzhongwen.hanyu3.reading

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import com.enzhongwen.hanyu3.R
import com.enzhongwen.hanyu3.databinding.FragmentReading8Binding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class Reading8Fragment: Fragment() {

    lateinit var binding: FragmentReading8Binding

    private var a = 0

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
        binding = FragmentReading8Binding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.reading_menu,menu)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.listen.setOnClickListener {
            val a = MediaPlayer.create(requireContext(), R.raw.l8_s)
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
                    binding.pinyinS1.text = "Y??y?? ji??o xi??o hu?? xi?? h??nz??."
                    binding.pinyinS2.text = "L?? l??osh?? ji??o w??men ch??ngg??."
                    binding.pinyinS3.text = "Xi??o l??ng q??ng xi??o hu?? ch?? shu??gu??."
                    binding.pinyinS4.text = "G??g?? q??ng w??men k??n z??j??."
                    binding.pinyinS5.text = "L??osh?? r??ng w?? hu??d?? w??nt??."
                    binding.pinyinS6.text = "M??m?? r??ng w?? x?? w??zi."
                    binding.pinyinS7.text = "Zh?? n?? sh??ngr?? ku??il??!"
                    binding.pinyinS8.text = "Zh?? n?? zh??ngqi?? ji?? ku??il??!"
                    binding.pinyinS9.text = "Zh?? n?? x??nni??n ku??il??!"
                    binding.pinyinHeading.text = "N??inai Q??ng W??men Ch?? Zh??nggu?? C??i"
                    binding.pinyinPassage.text = "          J??nti??n zh??ngqi?? ji??, w??men h?? n??inai y??q??gu??. N??inai f??ich??ng g??ox??ng, t?? q??ng w??men ch?? zh??nggu?? c??i. M??m?? h?? b??ba s??ng g??i n??inai y?? h?? ch??y??, w?? h?? d??d?? m??imei s??ng g??i n??inai y??ti??o w??ij??n. W??men y??q?? zh?? t?? zh??ngqi?? ji?? ku??il??!"
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
                    binding.pinyinHeading.text = null
                    binding.pinyinPassage.text = null
                    item.setIcon(R.drawable.white_a)
                }
            }
        }


        return super.onOptionsItemSelected(item)
    }


}