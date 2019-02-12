@file:Suppress("DEPRECATION")

package com.kalum.monese.rockets

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import com.kalum.monese.rockets.data.local.RocketsDatabase
import com.kalum.monese.rockets.data.local.dao.RocketItemDao
import com.kalum.monese.rockets.data.remote.ConnectivityInterceptor
import com.kalum.monese.rockets.data.remote.ConnectivityInterceptorImpl
import com.kalum.monese.rockets.data.remote.api.SpaceXApiService
import com.kalum.monese.rockets.data.repository.RocketsRepository
import com.kalum.monese.rockets.data.repository.RocketsRepositoryImpl
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext.loadKoinModules
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.standalone.inject
import org.koin.test.KoinTest
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import com.kalum.monese.rockets.util.LiveDataTestUtil


@RunWith(RobolectricTestRunner::class)
class MyUnitTest2 : KoinTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    class ContextService(private val context: Context) {
        fun getString(stringID: Int): String = context.getString(stringID)
    }

    private val contextService: ContextService by inject()

    private val db: RocketsDatabase by inject()

    private val service: SpaceXApiService by inject()

    private val rocketItemDao: RocketItemDao by inject()

    private val repository: RocketsRepository by inject()

    @Before
    fun setUp() {

        val contextModule = module {
            single { RuntimeEnvironment.application }
            factory { ContextService(get()) }
            single(override = true) {
                // In-Memory database config
                Room.inMemoryDatabaseBuilder(get(), RocketsDatabase::class.java)
                    .allowMainThreadQueries()
                    .build()
            }
            single<ConnectivityInterceptor>(override = true) { ConnectivityInterceptorImpl(get()) }
            single(override = true) { SpaceXApiService(get()) }
            single<RocketsRepository>(override = true) { RocketsRepositoryImpl(get(), get()) }
            single(override = true) { get<RocketsDatabase>().rocketItemDao() }
        }
        loadKoinModules(listOf(contextModule))

    }

    @After
    fun tearDown() {
        stopKoin()
    }

    private fun log(msg: Any?) = println(msg)


    @Test
    @Throws(Exception::class)
    fun `testing if the insert all is successful`() {
        val liveData = runBlocking { repository.getAllRockets() }
        Assert.assertNotNull(liveData)

        val list = LiveDataTestUtil.getValue(liveData)
        Assert.assertNotNull(list)

        log(list.size)

    }

}