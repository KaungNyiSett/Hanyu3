package com.enzhongwen.hanyu3.reading

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import com.enzhongwen.hanyu3.R
import com.enzhongwen.hanyu3.databinding.FragmentReading13Binding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class Reading13Fragment: Fragment() {

    lateinit var binding: FragmentReading13Binding

    var mInterstitialAd: InterstitialAd? = null

    var a = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {setHasOptionsMenu(true)

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
        binding = FragmentReading13Binding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listen.setOnClickListener {
            val a = MediaPlayer.create(requireContext(), R.raw.l13_s)
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
                    binding.pinyinS1.text = "W?? q?? d??ngw??yu??n k??n xi??ngm??o."
                    binding.pinyinS2.text = "M??m?? q?? sh??ngdi??n m??i d??ngx??."
                    binding.pinyinS3.text = "Xi??o h??ng l??i w??ji?? k??n di??nsh??."
                    binding.pinyinS4.text = "B??ba l??i xu??xi??o ji?? w??."
                    binding.pinyinS5.text = "T?? d??o y??uj?? q?? b??ogu??."
                    binding.pinyinS6.text = "T?? d??o b??ng??ngsh?? zh??o w??ng l??osh??."
                    binding.pinyinS7.text = "W?? hu?? ji??osh?? n?? y??oshi."
                    binding.pinyinS8.text = "W?? hu?? ji?? hu??n y??f??."
                    binding.pinyinHeading.text = " Y??y?? Q?? G??ngyu??n D?? T??ij?? Qu??n"
                    binding.pinyinPassage.text = "          Y??y?? h?? b??ba d??u h??n x??hu??n du??nli??n sh??nt??. Y??y?? ??i d?? t??ij?? qu??n, z??oshang t?? ch??ngch??ng q?? g??ngyu??n d?? t??ij?? qu??n. B??ba x??hu??n p??ob??, zh??um?? w?? ch??ngch??ng g??n t?? y??q?? d??o h??ibi??n p??ob??. W??men de sh??nt?? d??u h??n ji??nk??ng."
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
                    binding.pinyinHeading.text = null
                    binding.pinyinPassage.text = null
                    item.setIcon(R.drawable.white_a)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }


}