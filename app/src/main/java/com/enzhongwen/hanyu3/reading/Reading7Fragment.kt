package com.enzhongwen.hanyu3.reading

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import com.enzhongwen.hanyu3.R
import com.enzhongwen.hanyu3.databinding.FragmentReading7Binding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class Reading7Fragment: Fragment() {

    var a = 0

    var mInterstitialAd: InterstitialAd? = null

    lateinit var binding: FragmentReading7Binding

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
        binding = FragmentReading7Binding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.reading_menu,menu)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.listen.setOnClickListener {
            val a = MediaPlayer.create(requireContext(), R.raw.l7_s)
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
                    binding.pinyinS1.text = "B??ba g??i m??m?? y?? ji??n l??w??."
                    binding.pinyinS2.text = "W??men g??i l??osh?? y?? sh?? xi??nhu??."
                    binding.pinyinS3.text = "W?? w??n l??osh?? y??g?? w??nt??."
                    binding.pinyinS4.text = "T?? w??n w?? j??nti??n x??ngq?? j??."
                    binding.pinyinS5.text = "T?? g??os?? w?? y??g?? m??m??."
                    binding.pinyinS6.text = "G??g?? g??os?? w?? y?? ji??n sh??q??ng."
                    binding.pinyinHeading.text = "W?? G??i M??m?? Y?? Ji??n L??w??"
                    binding.pinyinPassage.text = "          J??nti??n y??u zh??ng w??n k??, l??osh?? ji??o t??ngxu??men s??n g?? h??nz??. W??nsh??ng, xi??om??ng g??i m??m?? y?? f??ng x??n. T?? du?? m??m?? shu??: ???M??m??, zh?? sh?? w?? g??i n??n de l??w??!??? X??n sh??ng y??us??n g?? h??nz??: W?? ??i n??!"
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