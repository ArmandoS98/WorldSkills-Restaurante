package com.aesc.restaurantews.ui.activities.splahs

import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aesc.restaurantews.R
import com.aesc.restaurantews.Util.Utils
import com.aesc.restaurantews.extensions.goToActivityF
import com.aesc.restaurantews.extensions.toast
import com.aesc.restaurantews.provider.Preferences.PreferencesKey
import com.aesc.restaurantews.provider.Preferences.PreferencesProvider
import com.aesc.restaurantews.ui.activities.home.MainActivity
import com.aesc.visaappk.provider.services.viewmodel.MainViewModel

class SplashActivity : AppCompatActivity() {
    private lateinit var viewModels: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

     /*   val logo = PreferencesProvider.string(this, PreferencesKey.LOGO_APP)
        if (logo == null) {
            viewModels = ViewModelProvider(this).get(MainViewModel::class.java)
            logo()
        } else {
        }*/

        starSplash(3000)
    }

    private fun starSplash(delay: Long, logo: String ="") {
        //img_logo.loadByURL(logo)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        Handler().postDelayed({
            goToActivityF<MainActivity>()
        }, delay)
    }

    private fun logo() {
        viewModels.responseLogo.observe(this, {
            PreferencesProvider.set(this, PreferencesKey.LOGO_APP, it.url_logo!!)
            starSplash(1000, it.url_logo!!)
        })

        viewModels.errorMessage.observe(this, {
            Utils.logsUtils("ERROR $it")
            toast(it, Toast.LENGTH_LONG)
        })

        viewModels.logo()
    }
}