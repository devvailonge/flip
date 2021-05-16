package com.devvailonge.flip.features.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.devvailonge.flip.HostActivity
import com.devvailonge.flip.databinding.ActivitySplashScreenBinding
import com.devvailonge.flip.features.categories.storage.SharedPreferencesWrapper
import com.devvailonge.flip.features.onboarding.OnboardingActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private val sharedPreferencesWrapper by lazy { SharedPreferencesWrapper.create(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Fix
        Handler().postDelayed({
            if(sharedPreferencesWrapper.getBoolean(SharedPreferencesWrapper.KEY_SHOW_ONBOARDING, true)){
                sharedPreferencesWrapper.setBoolean(SharedPreferencesWrapper.KEY_SHOW_ONBOARDING, false)
                finish()
                startActivity(Intent(this, OnboardingActivity::class.java))
            }else{
                finish()
                startActivity(Intent(this, HostActivity::class.java))
            }
        }, 1000)
    }
}