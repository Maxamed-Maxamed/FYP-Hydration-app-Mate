package com.example.fyp_hydration_app_mate.models

import com.google.android.material.snackbar.Snackbar
import timber.log.Timber
import timber.log.Timber.Forest.i

class HydrationMemStore : HydrationStore {

    private val hydrationList = ArrayList<HydrationModel>()


    override fun findAll(): List<HydrationModel> {
        return hydrationList
    }

    override fun create(hydrationModel: HydrationModel) {
        hydrationList.add(hydrationModel)
        i("Hydration record added: $hydrationModel")

    }


//    override fun update(hydrationModel: HydrationModel) {
//        val foundHydrationModel: HydrationModel? = hydrationList.find { p -> p.id == hydrationModel.id }
//        if (foundHydrationModel != null) {
//            foundHydrationModel.hydrationGoal = hydrationModel.hydrationGoal
//        }
//        Timber.i("Hydration record updated: $hydrationModel")
//    }



    // Implement delete and update
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


    // Log hydrations after change
    override fun logAll() {
        i("Hydration list: $hydrationList")
    }


    














}
