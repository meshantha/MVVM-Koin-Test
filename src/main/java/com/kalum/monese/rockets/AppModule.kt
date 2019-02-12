package com.kalum.monese.rockets

import androidx.room.Room
import com.kalum.monese.rockets.ui.view.feature.detail.RocketDetailsViewModel
import com.kalum.monese.rockets.ui.view.feature.list.RocketListViewModel
import com.kalum.monese.rockets.data.local.RocketsDatabase
import com.kalum.monese.rockets.data.remote.ConnectivityInterceptor
import com.kalum.monese.rockets.data.remote.ConnectivityInterceptorImpl
import com.kalum.monese.rockets.data.remote.RocketsDataSource
import com.kalum.monese.rockets.data.remote.RocketsDataSourceImpl
import com.kalum.monese.rockets.data.remote.api.SpaceXApiService
import com.kalum.monese.rockets.data.repository.RocketsRepository
import com.kalum.monese.rockets.data.repository.RocketsRepositoryImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.experimental.builder.viewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import org.koin.experimental.builder.singleBy

const val DATABASE_NAME = "rockets-db"

val appModule = module {

    single {

        Room.databaseBuilder(androidApplication(), RocketsDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<RocketsDatabase>().rocketItemDao() }

    singleBy<ConnectivityInterceptor, ConnectivityInterceptorImpl>(createOnStart = true)

    single { SpaceXApiService(get()) }

    singleBy<RocketsDataSource, RocketsDataSourceImpl>(createOnStart = true)

    singleBy<RocketsRepository, RocketsRepositoryImpl>(createOnStart = true)

    viewModel<RocketListViewModel>()

    viewModel { (key: String) -> RocketDetailsViewModel(key, get()) }

}

