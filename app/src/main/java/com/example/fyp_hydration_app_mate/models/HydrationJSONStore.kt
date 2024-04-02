package com.example.fyp_hydration_app_mate.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.fyp_hydration_app_mate.helpers.FileHelper
import timber.log.Timber
import java.lang.reflect.Type
import java.util.*

const val HYDRATION_JSON_FILE = "hydration_data.json"
private val gson = Gson()
private val listType: Type = object : TypeToken<ArrayList<HydrationModel>>() {}.type

class HydrationJSONStore(private val context: Context) : HydrationStore {

    private var hydrationList: MutableList<HydrationModel> = mutableListOf()

    init {
        if (FileHelper.exists(context, HYDRATION_JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): List<HydrationModel> {
        return hydrationList
    }

    override fun create(hydrationModel: HydrationModel) {
        hydrationModel.id = generateUniqueId()
        hydrationList.add(hydrationModel)
        serialize()
        Timber.i("Created new hydration record: $hydrationModel")
    }

    override fun update(hydrationModel: HydrationModel) {
        val index = hydrationList.indexOfFirst { it.id == hydrationModel.id }
        if (index != -1) {
            hydrationList[index] = hydrationModel
            serialize()
            Timber.i("Updated hydration record: $hydrationModel")
        } else {
            Timber.i("Hydration record not found for update: $hydrationModel")
        }
    }

    override fun delete(hydrationModel: HydrationModel) {
        hydrationList.removeAll { it.id == hydrationModel.id }
        serialize()
        Timber.i("Deleted hydration record: $hydrationModel")
    }

    private fun serialize() {
        try {
            val jsonString = gson.toJson(hydrationList, listType)
            FileHelper.write(context, HYDRATION_JSON_FILE, jsonString)
        } catch (e: Exception) {
            Timber.e(e, "Error serializing hydration data")
        }
    }

    private fun deserialize() {
        try {
            val jsonString = FileHelper.read(context, HYDRATION_JSON_FILE)
            hydrationList = gson.fromJson(jsonString, listType)
        } catch (e: Exception) {
            Timber.e(e, "Error deserializing hydration data")
        }
    }

    private fun generateUniqueId(): Long {
        var uniqueId = Random().nextLong()
        while (hydrationList.any { it.id == uniqueId }) {
            uniqueId = Random().nextLong()
        }
        return uniqueId
    }

    override fun logAll() {
        return HydrationMemStore().logAll() // Log the records from the memory store
    }

    override fun findHydrationById(id: Long): HydrationModel? {
        return hydrationList.find { it.id == id }
    }

    }
