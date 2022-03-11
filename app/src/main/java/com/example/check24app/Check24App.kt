package com.example.check24app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Check24App : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}