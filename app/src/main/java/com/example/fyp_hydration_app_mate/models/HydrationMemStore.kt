package com.example.fyp_hydration_app_mate.models

import timber.log.Timber
import java.util.ArrayList

class HydrationMemStore : HydrationStore {

    private val hydrationList = ArrayList<HydrationModel>()
   private var lastId = 0L

    private  fun getId(): Long {
        return lastId++
    }

    override fun findAll(): List<HydrationModel> {
        // Function logic here
        return hydrationList
    }

    override fun create(hydrationModel: HydrationModel) {
        hydrationModel.id = getId() // Example of utilizing the getId() function
        hydrationList.add(hydrationModel)
        Timber.i("Hydration record added: $hydrationModel") // Corrected logging
    }

    override fun delete(hydrationModel: HydrationModel) {
        hydrationList.remove(hydrationModel)
    }

    override fun update(hydrationModel: HydrationModel) {
        val foundHydration = hydrationList.find { it.id == hydrationModel.id }
        if (foundHydration != null) {
            foundHydration.hydrationGoal = hydrationModel.hydrationGoal
            Timber.i("Hydration record updated: $hydrationModel") // Corrected logging
            // Removed the redundant .copy() call
            return
        } else {
            Timber.i("Hydration record not found") // Corrected logging
        }
    }

    override fun logAll() {
        Timber.i("Hydration list: $hydrationList") // Corrected logging
    }
}