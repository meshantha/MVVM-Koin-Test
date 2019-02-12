package com.kalum.monese.rockets.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kalum.monese.rockets.data.local.entity.RocketItem
import com.kalum.monese.rockets.data.remote.api.SpaceXApiService
import com.kalum.monese.rockets.data.remote.internal.NoConnectionException
import com.kalum.monese.rockets.util.TAG

class RocketsDataSourceImpl(private val spaceXApiService: SpaceXApiService) :
        RocketsDataSource {

    private val downloadedCurrentRockets = MutableLiveData<List<RocketItem>>()
    private val requestedRocket = MutableLiveData<RocketItem>()


    override val downloadedRockets: LiveData<List<RocketItem>>
        get() = downloadedCurrentRockets


    override suspend fun fetchRocketById(id: String) {
        try {
            val response = spaceXApiService.getRocketById(id).await()
            requestedRocket.postValue(response)
        } catch (e: NoConnectionException) {
            Log.e(TAG(), "NO internet connection")
            //todo handle it here
        }
    }

    override suspend fun fetchRockets() {
        try {
            val response = spaceXApiService.getRockets().await()
            downloadedCurrentRockets.postValue(response)
        } catch (e: NoConnectionException) {
            Log.e(TAG(), "NO internet connection")
            //todo handle it here
        }
    }
}