package com.enzhongwen.hanyu3.reading

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import com.enzhongwen.hanyu3.R
import com.enzhongwen.hanyu3.databinding.FragmentReading11Binding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class Reading11Fragment: Fragment() {

    lateinit var binding: FragmentReading11Binding

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
        binding = FragmentReading11Binding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listen.setOnClickListener {
            val a = MediaPlayer.create(requireContext(), R.raw.l11_s)
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
                    binding.pinyinS1.text = "W??men z??i h??ibi??n w??n sh??zi."
                    binding.pinyinS2.text = "G??g?? z??i zh??nggu?? xu?? h??ny??."
                    binding.pinyinS3.text = "N?? b??ba z??i n??'er g??ngzu???"
                    binding.pinyinS4.text = "N?? m??imei z??i n??'er sh??ngxu???"
                    binding.pinyinS5.text = "Zh??nggu?? c??i h??o ch?? j??le!"
                    binding.pinyinS6.text = "Zh?? f?? hu?? er h??ok??n j??le!"
                    binding.pinyinS7.text = "B??ba b??z??i g??ngs?? g??ngzu??."
                    binding.pinyinS8.text = "J??nti??n w?? b?? z??iji?? ch?? w??nf??n."
                    binding.pinyinHeading.text = "W??men Z??i H??ibi??n W??n Sh??zi"
                    binding.pinyinPassage.text = "Zu??ti??n sh?? x??ngq??ti??n, b??ba m??m?? b?? sh??ngb??n. W??men qu??nji?? y?? q?? q?? h??ibi??n w??n. H??ibi??n de f??ngj??ng m??i j??le! Ti??n h??n l??n, h??i y?? h??n l??n, h??ndu?? r??n z??i h??il?? y??uy??ng. B??ba h?? m??m?? z??i h??ibi??n d?? p??iqi??, w?? h?? d??d?? z??i h??ibi??n w??n sh??zi, m??imei z??i h??ibi??n sh??i t??iy??ng. Zh?? y??ti??n h??ow??n j??le!"
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