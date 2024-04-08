package com.example.fyp_hydration_app_mate.main

import android.app.Application
import com.example.fyp_hydration_app_mate.helpers.FileHelper
import com.example.fyp_hydration_app_mate.models.HYDRATION_JSON_FILE
import com.example.fyp_hydration_app_mate.models.HydrationJSONStore
import com.example.fyp_hydration_app_mate.models.HydrationMemStore
import timber.log.Timber

class MainApp : Application() {


lateinit var hydrationModelMain2: HydrationMemStore
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Timber.i("Main App Created")

        // Switch to this for in-memory data management
        hydrationModelMain2.logAll() // Log the list of hydration records
        hydrationModelMain2.findAll().forEach { Timber.i("Hydration record: $it") } // Log the list of hydration records





    }

    override fun onTerminate() {
        super.onTerminate()
        Timber.i("Main App Terminated")
        hydrationModelMain2.logAll() // Log the list of hydration records
        hydrationModelMain2.findAll().forEach { Timber.i("Hydration record: $it") } // Log the list of hydration records
    }




}