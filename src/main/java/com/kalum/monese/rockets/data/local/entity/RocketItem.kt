package com.kalum.monese.rockets.data.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.kalum.monese.rockets.data.local.converter.EngineTypeConverter
import java.io.Serializable

@Entity(tableName = "rockets", indices = [(Index("id"))])
data class RocketItem(

        @SerializedName("rocket_id")
        var rocketid: String,

        @NonNull
        @PrimaryKey
        @SerializedName("id")
        var id: Int? = null,

        @SerializedName("active")
        var active: Boolean = false,

        @SerializedName("country")
        var country: String? = null,

        @SerializedName("rocket_name")
        var rocket_name: String? = null,

        @SerializedName("engines")
        var engines: EngineTypeConverter.Engine? = null,

        @SerializedName("description")
        var description: String? = null,

        @SerializedName("company")
        var company: String? = null,

        @SerializedName("flickr_images")
        var flickr_images: Array<String>? = emptyArray()

)

    : Serializable {
    constructor() : this("", null, false, "", "", null, "", "", null)


    companion object {
        const val ROCKET_ITEM_TABLE_NAME = "rockets"
    }


}