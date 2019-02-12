package com.kalum.monese.rockets.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kalum.monese.rockets.data.local.converter.EngineTypeConverter
import com.kalum.monese.rockets.data.local.converter.StringTypeConverter
import com.kalum.monese.rockets.data.local.dao.RocketItemDao
import com.kalum.monese.rockets.data.local.entity.RocketItem

@Database(
        entities = [(RocketItem::class)],
        version = 24,
        exportSchema = false
)
@TypeConverters(EngineTypeConverter::class, StringTypeConverter::class)
abstract class RocketsDatabase : RoomDatabase() {

    abstract fun rocketItemDao(): RocketItemDao

}