package com.example.xogamesapp

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG,"onCreate")
    }
    companion object {
        const val TAG = "MainApplication"
    }
}