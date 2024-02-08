package com.example.fyp_hydration_app_mate.main

import android.app.Application
import com.example.fyp_hydration_app_mate.models.HydrationMemStore
import com.example.fyp_hydration_app_mate.models.HydrationModel
import timber.log.Timber

class MainApp : Application() {

//    val hydrationModelMain = arrayListOf<HydrationModel>()


//    val hydrationModelMain2 = arrayListOf<HydrationMemStore>()

   val  hydrationModelMain2 = HydrationMemStore()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Timber.i("Main App Created")
//        hydrationModelMain.add(HydrationModel(200, 200))
//        Timber.i("Hydration Model Main List Size: " + hydrationModelMain.size)
    }

}