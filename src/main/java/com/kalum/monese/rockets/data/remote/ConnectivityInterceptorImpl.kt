package com.kalum.monese.rockets.data.remote

import android.content.Context
import android.net.ConnectivityManager
import com.kalum.monese.rockets.data.remote.internal.NoConnectionException
import okhttp3.Interceptor
import okhttp3.Response

class ConnectivityInterceptorImpl(context: Context) : ConnectivityInterceptor {

    private val appContext = context.applicationContext
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isConnected()) {
            throw NoConnectionException()
        }
        return chain.proceed(chain.request())
    }

    fun isConnected(): Boolean {
        val connManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}