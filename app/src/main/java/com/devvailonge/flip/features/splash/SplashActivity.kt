package com.devvailonge.flip.features.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.devvailonge.flip.databinding.ActivityMainBinding
import com.devvailonge.flip.databinding.ActivitySplashScreenBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}