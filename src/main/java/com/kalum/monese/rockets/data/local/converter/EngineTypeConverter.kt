package com.kalum.monese.rockets.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class EngineTypeConverter {

    @TypeConverter
    fun toEngine(engineAsString: String?): Engine {
        return Gson().fromJson<Engine>(engineAsString!!)
    }

    @TypeConverter
    fun fromEngine(engineAsObject: Engine): String? {
        return Gson().toJson(engineAsObject)
    }

    inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object : TypeToken<T>() {}.type)


    data class Engine(
            val number: Int,
            val type: String,
            val version: String,
            val layout: String
    )
}