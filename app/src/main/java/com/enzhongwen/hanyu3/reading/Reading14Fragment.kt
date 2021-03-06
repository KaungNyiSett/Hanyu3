package com.enzhongwen.hanyu3.reading

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import com.enzhongwen.hanyu3.R
import com.enzhongwen.hanyu3.databinding.FragmentReading14Binding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class Reading14Fragment: Fragment() {

    lateinit var binding: FragmentReading14Binding

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
                Log.d("TAG", "Ad failed to load")
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                Log.d("TAG", "Ad was loaded.")
                mInterstitialAd = interstitialAd
                    mInterstitialAd?.show(requireActivity())
            }
        })
        binding = FragmentReading14Binding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listen.setOnClickListener {
            val a = MediaPlayer.create(requireContext(), R.raw.l14_s)
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
                    binding.pinyinS1.text = "M??m?? zu?? hu??ch?? q?? w??ip?? ji??."
                    binding.pinyinS2.text = "G??g?? zu?? f??ij?? q?? xi??ngg??ng."
                    binding.pinyinS3.text = "W??men y??ng ku??izi ch??f??n."
                    binding.pinyinS4.text = "Sh??shu y??ng sh??uj?? sh??ngw??ng."
                    binding.pinyinS5.text = "W?? b??ng l??osh?? c?? h??ib??n."
                    binding.pinyinS6.text = "Ji??ji?? b??ng m??m?? x?? w??n."
                    binding.pinyinHeading.text = "W??men Zu??ch?? Q?? D??ngw??yu??n"
                    binding.pinyinPassage.text = "          J??nti??n, w??men zu??ch?? q?? d??ngw??yu??n. D??ngw??yu??n li y??u h??ndu?? zh??ng d??ngw??. D?? h??uzi ji??o xi??o h??uzi p??sh??n, l??oh?? m??m?? b??ng xi??o l??oh?? x??li??n, d?? xi??ng y??ng ch??ng b??zi x??shu??, d?? xi??ngm??o z??i c??od?? ch?? zh??zi. T??ngxu??men d??u h??n x??hu??n t??men, d??ji?? shu??:???D??ngw?? sh?? w??men de h??o p??ngy??u.???"
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