package com.example.fyp_hydration_app_mate.models

import timber.log.Timber
import java.util.*  // Importing the Java utility package
import kotlin.collections.ArrayList

class HydrationMemStore : HydrationStore {

    private val hydrationList = ArrayList<HydrationModel>()
    private var lastId = 0L // Start IDs from 1 for better readability

    private fun generateUniqueId(): Long {
        var uniqueId = lastId++
        while (hydrationList.any { it.id == uniqueId }) {
            uniqueId = lastId++
        }
        return uniqueId
    }

    override fun findAll(): List<HydrationModel> = hydrationList

    override fun create(hydrationModel: HydrationModel) {
        hydrationModel.id = generateUniqueId()
        hydrationList.add(hydrationModel)
        Timber.i("Hydration record added: $hydrationModel")
    }

    override fun delete(hydrationModel: HydrationModel) {
        hydrationList.removeAll { it.id == hydrationModel.id }
        Timber.i("Hydration record deleted: $hydrationModel")
    }

    override fun update(hydrationModel: HydrationModel) {
        val index = hydrationList.indexOfFirst { it.id == hydrationModel.id }
        if (index != -1) {
            hydrationList[index] = hydrationModel.apply {
                Timber.i("Hydration record updated: $this")
                id = generateUniqueId()
                hydrationList.add(hydrationModel)

            }
            Timber.i("Hydration record updated: $hydrationModel")
        } else {
            Timber.i("Hydration record not found with ID: ${hydrationModel.id}")
        }
    }

    override fun logAll() {
        Timber.i("Hydration List:") // Log the list of hydration records

        hydrationList.forEach { Timber.i("Hydration record: $it") }
    }

    override fun findHydrationById(id: Long): HydrationModel? {
        return hydrationList.find { it.id == id } // Return the first record with the matching ID
    }
}
