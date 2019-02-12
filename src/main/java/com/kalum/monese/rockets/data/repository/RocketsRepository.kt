package com.kalum.monese.rockets.data.repository

import androidx.lifecycle.LiveData
import com.kalum.monese.rockets.data.local.entity.RocketItem

interface RocketsRepository {

    suspend fun getAllRockets(): LiveData<out List<RocketItem>>

    suspend fun getRocketById(id: String): LiveData<out RocketItem>
}