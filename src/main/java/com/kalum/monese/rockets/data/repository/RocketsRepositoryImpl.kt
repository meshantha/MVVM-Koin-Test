package com.kalum.monese.rockets.data.repository

import androidx.lifecycle.LiveData
import com.kalum.monese.rockets.data.local.entity.RocketItem
import com.kalum.monese.rockets.data.local.dao.RocketItemDao
import com.kalum.monese.rockets.data.remote.RocketsDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime

class RocketsRepositoryImpl(
        private val rocketItemDao: RocketItemDao,
        private val rocketsDataSource: RocketsDataSource
) : RocketsRepository {

    override suspend fun getRocketById(
            id: String
    ): LiveData<out RocketItem> {
        return withContext(Dispatchers.IO) {
            fetchRocketById(id)
            return@withContext rocketItemDao.getRocketById(id)
        }
    }

    init {
        rocketsDataSource.downloadedRockets.observeForever { currentCourses ->
            storeFetchedRockets(currentCourses)
        }
    }

    override suspend fun getAllRockets(): LiveData<out List<RocketItem>> {
        return withContext(Dispatchers.IO) {
            refreshRocketData()
            return@withContext rocketItemDao.getAllRockets()
        }
    }

    private fun storeFetchedRockets(rocketList: List<RocketItem>) {
        GlobalScope.launch(Dispatchers.IO) {
            rocketItemDao.insertAll(rocketList)
        }
    }

    private fun isFetchNeeded(lastFetchedTime: ZonedDateTime) = with(lastFetchedTime) {
        isBefore(ZonedDateTime.now().minusMinutes(1))
    }

    private suspend fun refreshRocketData() {
        fetchAllCourses()
    }

    private suspend fun fetchAllCourses() {
        rocketsDataSource.fetchRockets()
    }

    private suspend fun fetchRocketById(id: String) {
        rocketsDataSource.fetchRocketById(id)
    }

}