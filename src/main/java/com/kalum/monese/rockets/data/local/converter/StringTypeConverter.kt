package com.kalum.monese.rockets.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StringTypeConverter {

    @TypeConverter
    fun toString(AsString: String?): Array<String> {
        return Gson().fromJson<Array<String>>(AsString!!)
    }

    @TypeConverter
    fun fromString(asArray: Array<String>): String? {
        return Gson().toJson(asArray)
    }

    inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object : TypeToken<T>() {}.type)


}