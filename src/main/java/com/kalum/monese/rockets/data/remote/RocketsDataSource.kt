package com.kalum.monese.rockets.data.remote

import androidx.lifecycle.LiveData
import com.kalum.monese.rockets.data.local.entity.RocketItem

interface RocketsDataSource {

    val downloadedRockets: LiveData<List<RocketItem>>

    suspend fun fetchRockets()

    suspend fun fetchRocketById(id:String)
}