package com.example.fyp_hydration_app_mate.main

import android.app.Application
import com.example.fyp_hydration_app_mate.models.HydrationMemStore
import com.example.fyp_hydration_app_mate.models.HydrationModel
import timber.log.Timber

class MainApp : Application() {


   val  hydrationModelMain2 = HydrationMemStore()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Timber.i("Main App Created")

        hydrationModelMain2.findAll().forEach {

            Timber.i("Hydration Model Main List Size findAll: " + it.copy())

        }
        hydrationModelMain2.logAll()
        Timber.i("Hydration Model Main List Size findAll: " + hydrationModelMain2.logAll())


    }

}