package com.example.xogamesapp

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
//    lateinit var container: AppContainer

    companion object {
        const val TAG = "MainApplication"
//        lateinit var database: GameDatabase
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")
//        container = AppDataContainer(this)
//        database = Room.databaseBuilder(
//            applicationContext,
//            GameDatabase::class.java,
//            "game_history_database"
//        ).build()

    }

}