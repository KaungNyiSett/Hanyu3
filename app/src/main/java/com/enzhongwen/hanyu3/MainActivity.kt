package com.enzhongwen.hanyu3

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.navigateUp
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.enzhongwen.hanyu3.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var myPreference: MyPreference

    private lateinit var navController: NavController

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this)

        val adView: AdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        drawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        setupActionBarWithNavController(navController,drawerLayout)

        navView.let{
            setupWithNavController(it,navController)
        }

    }

    override fun attachBaseContext(newBase: Context?) {
        myPreference = MyPreference(newBase!!)
        val lang = myPreference.getLoginCount()
        super.attachBaseContext(MyContextWrapper.wrap(newBase,lang!!))
    }

    override fun onSupportNavigateUp(): Boolean {
        return navigateUp(navController,drawerLayout)
    }

}