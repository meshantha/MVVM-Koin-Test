@file:Suppress("DEPRECATION")

package com.kalum.monese.rockets

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.kalum.monese.rockets.data.local.RocketsDatabase
import com.kalum.monese.rockets.data.local.dao.RocketItemDao
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@Suppress("DEPRECATION")
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    private lateinit var database: RocketsDatabase
    private lateinit var rocketItemDao: RocketItemDao
    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getTargetContext()
        database = Room.inMemoryDatabaseBuilder(context, RocketsDatabase::class.java).build()
        rocketItemDao = database.rocketItemDao()
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.kalum.monese.rockets", appContext.packageName)
    }
}
