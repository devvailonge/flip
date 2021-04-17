package com.devvailonge.flip

import android.app.Application

class FlipApplication: Application() {

    companion object{
        lateinit var instance: FlipApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}