package com.example.fyp_hydration_app_mate.models

interface HydrationStore {



    fun findAll(): List<HydrationModel>

    fun create(hydrationModel: HydrationModel)

    fun logAll()

    fun update(hydrationModel: HydrationModel)

    fun delete(hydrationModel: HydrationModel)

    fun findHydrationById(id: Long): HydrationModel? {
        return findAll().find { it.id == id }
    }




}