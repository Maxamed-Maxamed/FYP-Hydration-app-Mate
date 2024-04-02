package com.example.fyp_hydration_app_mate.main

import android.app.Application
import com.example.fyp_hydration_app_mate.helpers.FileHelper
import com.example.fyp_hydration_app_mate.models.HYDRATION_JSON_FILE
import com.example.fyp_hydration_app_mate.models.HydrationJSONStore
import com.example.fyp_hydration_app_mate.models.HydrationMemStore
import timber.log.Timber

class MainApp : Application() {


    val hydrationModelMain2 = HydrationMemStore()
    lateinit var hydrationStore: HydrationJSONStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Timber.i("Main App Created")



        // Initial checks for file existence and readability
        if (FileHelper.exists(applicationContext, HYDRATION_JSON_FILE)) {
            Timber.i("$HYDRATION_JSON_FILE exists, proceeding with HydrationJSONStore initialization.")
        } else {
            Timber.e("$HYDRATION_JSON_FILE does not exist, check file creation and permissions.")
            // Consider creating the file here if it should exist at app start
        }

        // Continue with hydration store initialization and operations...
         hydrationStore = HydrationJSONStore(applicationContext)
        Timber.i("HydrationJSONStore initialized with context: $applicationContext")
        // Initialize the HydrationJSONStore with the application context







        // Switch to this for in-memory data management


        hydrationModelMain2.findAll().forEach {

            Timber.i("Hydration Model Main List Size findAll: " + it.copy())

        }
        hydrationModelMain2.logAll()
        Timber.i("Hydration Model Main List Size findAll: " + hydrationModelMain2.logAll())

        // Switch to this for persistent data management
        val hydrationStore =
            HydrationJSONStore(applicationContext) // applicationContext is the context of the MainApp

        hydrationStore.findAll().forEach {
            Timber.i("Hydration Model Main List Size findAll: " + it.copy())
        }
        hydrationStore.logAll()
        Timber.i("Hydration Model Main List Size findAll: " + hydrationStore.logAll())



        // Switch to this for in-memory data management

        hydrationModelMain2.logAll() // Log the list of hydration records
        hydrationModelMain2.findAll().forEach { Timber.i("Hydration record: $it") } // Log the list of hydration records
        hydrationModelMain2.findHydrationById(1)?.let { Timber.i("Hydration record: $it") } // Log the list of hydration records

    }

}