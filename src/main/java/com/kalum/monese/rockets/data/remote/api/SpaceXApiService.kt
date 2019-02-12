package com.kalum.monese.rockets.data.remote.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.kalum.monese.rockets.data.local.entity.RocketItem
import com.kalum.monese.rockets.data.remote.ConnectivityInterceptor
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit


interface SpaceXApiService {

    @GET("/v3/rockets")
    fun getRockets(
    ): Deferred<List<RocketItem>>


    @GET("/v3/rockets/{rocketId}")
    fun getRocketById(@Path("rocketId") rocketId: String): Deferred<RocketItem>

    companion object {
        operator fun invoke(
                connectivityInterceptor: ConnectivityInterceptor
        ): SpaceXApiService {

            val okHttpClient = OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .addInterceptor(connectivityInterceptor)
                    .build()

            return Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(SpaceXApiService::class.java)
        }

        private const val BASE_URL = "https://api.spacexdata.com"

    }

}