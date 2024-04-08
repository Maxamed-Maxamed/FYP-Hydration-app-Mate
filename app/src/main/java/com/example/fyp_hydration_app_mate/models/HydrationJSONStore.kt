package com.example.fyp_hydration_app_mate.models

import android.content.Context
import android.net.Uri
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.fyp_hydration_app_mate.helpers.FileHelper
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import timber.log.Timber
import java.lang.reflect.Type
import java.util.*

const val  HYDRATION_JSON_FILE = "hydrations.json" // Filename for the JSON file to store the hydration records
val gsonBuilder: Gson = GsonBuilder().setPrettyPrinting()
    .registerTypeAdapter(Uri::class.java, UriParser()).create()

val listType: Type = object : TypeToken<ArrayList<HydrationModel>>() {}.type
fun generateUniqueId(): Long {
    return Random().nextLong()
}


class HydrationJSONStore(private val context: Context) : HydrationStore {

//    private val gson = Gson()
//    private val type = TypeToken.getParameterized(List::class.java, HydrationModel::class.java).type
//    private val filename = HYDRATION_JSON_FILE  // Filename for the JSON file to store the hydration records
//


    val hydrationS = mutableListOf<HydrationModel>()
    init {
        if (FileHelper.exists(context, HYDRATION_JSON_FILE)) {
            deserialize()
        }
    }


    override fun findAll(): List<HydrationModel> {
      logAll()
        return hydrationS
    }

    override fun create(hydrationModel: HydrationModel) {
        hydrationModel.id = generateUniqueId()
        hydrationS.add(hydrationModel)
        serialize()
    }

    override fun logAll() {
        hydrationS.forEach { Timber.i("Hydration record: $it") }
    }

    override fun update(hydrationModel: HydrationModel) {
        val index = hydrationS.indexOfFirst { it.id == hydrationModel.id }
        if (index != -1) {
            hydrationS[index] = hydrationModel
            serialize()
        }

    }

    override fun delete(hydrationModel: HydrationModel) {
        hydrationS.remove(hydrationModel)
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(hydrationS, listType)
        FileHelper.write(context, HYDRATION_JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = FileHelper.read(context, HYDRATION_JSON_FILE)
        hydrationS.clear()
        hydrationS.addAll(gsonBuilder.fromJson(jsonString, listType))
    }

}

 class UriParser : JsonDeserializer<Uri>, JsonSerializer<Uri> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Uri {
        return Uri.parse(json?.asString)
    }
    override fun serialize(
        src: Uri?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src.toString())
    }
}