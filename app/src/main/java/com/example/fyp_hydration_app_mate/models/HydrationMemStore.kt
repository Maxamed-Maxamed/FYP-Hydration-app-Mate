package com.example.fyp_hydration_app_mate.models

import com.google.android.material.snackbar.Snackbar
import timber.log.Timber
import timber.log.Timber.Forest.i

class HydrationMemStore : HydrationStore {
    
    private val hydrationList = ArrayList<HydrationModel>()
    var lastId = 0L
    
    internal fun getId(): Long {
        return lastId++
    }

    override fun findAll(): List<HydrationModel> {
        // Function logic here
        return hydrationList
    }

    override fun create(hydrationModel: HydrationModel) {
        hydrationModel.id = getId() // Example of utilizing the getId() function
        hydrationList.add(hydrationModel)
        i("Hydration record added: $hydrationModel")
    }

    override fun delete(hydrationModel: HydrationModel) {
        hydrationList.remove(hydrationModel)
    }

    override fun update(hydrationModel: HydrationModel) {
        val foundHydration = hydrationList.find { it.id == hydrationModel.id }
        if (foundHydration!= null) {
            foundHydration.hydrationGoal = hydrationModel.hydrationGoal
            i("Hydration record updated: $hydrationModel")

            foundHydration.copy(hydrationGoal = hydrationModel.hydrationGoal, id = hydrationModel.id)

            return
        } else {
            i("Hydration record not found")
        }
    }

    override fun logAll() {
        i("Hydration list: $hydrationList")
    }
}

