package com.enzhongwen.hanyu3.reading

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import com.enzhongwen.hanyu3.R
import com.enzhongwen.hanyu3.databinding.FragmentReading10Binding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class Reading10Fragment: Fragment() {

    lateinit var binding: FragmentReading10Binding

    var mInterstitialAd: InterstitialAd? = null

    private var a = 0

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
        binding = FragmentReading10Binding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.reading_menu,menu)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.listen.setOnClickListener {
            val a = MediaPlayer.create(requireContext(), R.raw.l10_s)
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
                    binding.pinyinS1.text = "Xi??o sh??ny??ng: J??nti??n sh?? w?? de sh??ngr??, w?? q??ng p??ngy??umen ch??f??n."
                    binding.pinyinS2.text = "Xi??o m??o: Xi??o sh??ny??ng, xi??o sh??ny??ng, w?? l??ile! W?? s??ng g??i n?? y?? l??n q??ngc??i."
                    binding.pinyinS3.text = "Xi??o sh??ny??ng: A, q??ngc??i! W?? ??i ch?? q??ngc??i, xi??xi??!"
                    binding.pinyinS4.text = "Xi??o t??z??: Xi??o sh??ny??ng, xi??o sh??ny??ng, w?? l??ile! W?? s??ng g??i n?? y??g?? h??lu??bo!"
                    binding.pinyinS5.text = "Xi??o sh??ny??ng: A, h??lu??bo! W?? y?? ??i ch?? h??lu??bo, xi??xi?? n??!"
                    binding.pinyinS6.text = "Xi??o m??o, xi??o t??z??: Zh?? n?? sh??ngr?? ku??il??!"
                    binding.pinyinHeading.text = "Zh?? N?? Sh??ngr?? Ku??il??"
                    binding.pinyinPassage.text = "           Xi??o m??o, xi??o t??z?? h?? xi??o sh??ny??ng sh?? h??o p??ngy??u. J??nti??n sh?? xi??o sh??ny??ng de sh??ngr??, t?? q??ng p??ngy??umen ch??f??n. Xi??o m??o ??i ch?? y??, xi??o sh??ny??ng g??i t?? zh??nb??ile y??ti??o y??. Xi??o t??z?? ??i ch?? h??lu??bo, xi??o sh??ny??ng g??i t?? zh??nb??ile y??g?? h??lu??bo. Xi??o m??o h?? xi??o t??z?? s??ng g??i xi??o sh??ny??ng y?? l??n q??ngc??i, t??men y??q?? du?? xi??o sh??ny??ng shu??: ???Zh?? n?? sh??ngr?? ku??il??!???"
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