package com.example.fyp_hydration_app_mate.models

import timber.log.Timber
import timber.log.Timber.Forest.i

class HydrationMemStore : HydrationStore {

    private val hydrationList = ArrayList<HydrationModel>()

//    override fun findAll(): List<HydrationModel> {
//        return hydrationList.toList()
//    }
//
//    override fun create(hydrationModel: HydrationModel) {
//      hydrationList.add(hydrationModel)
//        return hydrationList.forEach{ i("${it}") }
//    }
//
//
//    fun logAll() {
//        i("Hydration List")
//        return hydrationList.forEach{ i("${it}") }
//
//
//    }

    override fun findAll(): List<HydrationModel> {
        return hydrationList
    }

    override fun create(hydrationModel: HydrationModel) {
        hydrationList.add(hydrationModel)
        Timber.i("Hydration record added: $hydrationModel")
    }

    fun logAll() {
        hydrationList.forEach { Timber.i("${it}") }
    }

    fun deleteAll() {
        hydrationList.clear()
    }














}
