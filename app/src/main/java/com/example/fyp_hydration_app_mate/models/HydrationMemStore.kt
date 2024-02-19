package com.example.fyp_hydration_app_mate.models

import timber.log.Timber
import timber.log.Timber.Forest.i

class HydrationMemStore : HydrationStore {

    private val hydrationList = ArrayList<HydrationModel>()


    override fun findAll(): List<HydrationModel> {
        return hydrationList
    }

    override fun create(hydrationModel: HydrationModel) {
        hydrationList.add(hydrationModel)
        Timber.i("Hydration record added: $hydrationModel")
    }

    override fun logAll() {
       Timber.i("Hydration list: $hydrationList")

    }

    override fun update(hydrationModel: HydrationModel) {
        val foundHydrationModel: HydrationModel? = hydrationList.find { p -> p.id == hydrationModel.id }
        if (foundHydrationModel != null) {
            foundHydrationModel.hydrationGoal = hydrationModel.hydrationGoal
        }
    }

    fun deleteAll() {
        hydrationList.removeAll(hydrationList)
    }
    fun delete(hydrationModel: HydrationModel) {
        hydrationList.remove(hydrationModel)
    }


    














}
