package com.kalum.monese.rockets

import android.app.Application
import android.content.Context
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.android.startKoin

class RocketsApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        startKoin(this, listOf(appModule))
        appContext = applicationContext
    }

    companion object {

        var appContext: Context? = null
            private set
    }

}