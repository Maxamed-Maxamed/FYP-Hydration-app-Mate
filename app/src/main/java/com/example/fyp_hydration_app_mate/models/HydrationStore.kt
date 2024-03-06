package com.example.fyp_hydration_app_mate.models

interface HydrationStore {



    fun findAll(): List<HydrationModel>

    fun create(hydrationModel: HydrationModel)

    fun logAll()

    fun update(hydrationModel: HydrationModel)

    fun delete(hydrationModel: HydrationModel)

//    fun deleteAll(hydrationModel: HydrationModel)


//    fun edit(hydrationModel: HydrationModel)
//
//    fun update(hydrationModel: HydrationModel)
//
//    fun delete(hydrationModel: HydrationModel)





}