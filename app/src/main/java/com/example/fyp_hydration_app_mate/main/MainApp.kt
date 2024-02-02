package com.example.fyp_hydration_app_mate.main

import android.app.Application
import com.example.fyp_hydration_app_mate.models.HydrationModel
import timber.log.Timber

class MainApp : Application() {

    val hydrationModelMain = arrayListOf<HydrationModel>()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Timber.i("Main App Created")
//        hydrationModelMain.add(HydrationModel(200, 200))
//        Timber.i("Hydration Model Main List Size: " + hydrationModelMain.size)
    }

}