package com.example.fyp_hydration_app_mate.models

import timber.log.Timber
import java.util.ArrayList
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.fyp_hydration_app_mate.helpers.FileHelper
import java.lang.reflect.Type
import java.util.*

const val HYDRATION_JSON_FILE = "hydration_data.json"
private val gson = Gson()
private val listType = object : TypeToken<ArrayList<HydrationModel>>() {}.type // TypeToken for Gson

fun generateRandomId(): Long {
    return Random().nextLong()
}

class HydrationJSONStore(private val context: Context) : HydrationStore {

    private var hydrationList = ArrayList<HydrationModel>()

    init {
        if (FileHelper.exists(context, HYDRATION_JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): List<HydrationModel> {
        return hydrationList
    }

    override fun create(hydrationModel: HydrationModel) {
        hydrationModel.id = generateRandomId()
        hydrationList.add(hydrationModel)
        serialize()
    }

    override fun delete(hydrationModel: HydrationModel) {
        hydrationList.remove(hydrationModel)
        serialize()
    }

    override fun update(hydrationModel: HydrationModel) {
        val foundHydration = hydrationList.find { it.id == hydrationModel.id }
        if (foundHydration != null) {
            foundHydration.hydrationGoal = hydrationModel.hydrationGoal
            serialize()
            return
        } else {
            Timber.i("Hydration record not found")
        }
    }

    override fun logAll() {
        Timber.i("Hydration list: $hydrationList")
    }


    private fun serialize() {
        val jsonString = gson.toJson(hydrationList, listType)
        FileHelper.write(context, HYDRATION_JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = FileHelper.read(context, HYDRATION_JSON_FILE)
        hydrationList = gson.fromJson(jsonString, listType)
    }
}