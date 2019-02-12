package com.kalum.monese.rockets.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kalum.monese.rockets.data.local.entity.RocketItem

@Dao
interface RocketItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(rocketItem: List<RocketItem>)

    @Query("SELECT * FROM ${RocketItem.ROCKET_ITEM_TABLE_NAME}")
    fun getAllRockets(): LiveData<List<RocketItem>>


    @Query("SELECT * FROM ${RocketItem.ROCKET_ITEM_TABLE_NAME} WHERE $COLUMN_KEY = :id")
    fun getRocketById(id: String): LiveData<RocketItem>

    companion object {
        const val COLUMN_KEY = "rocketid"
    }
}