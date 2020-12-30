package com.sera.betworks.network

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object HttpClient {

    private val API_BASE_URL = "https://jsonplaceholder.typicode.com/"

    /**
     *
     */
    private val retrofit = Retrofit.Builder()
        .baseUrl(API_BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(createOkHttpClient())
        .build()

    /**
     *
     */
    private val apiService = retrofit.create(ApiService::class.java)

    /**
     *
     */
    fun getApiService(): ApiService? {
        return apiService
    }

    /**
     *
     */
    private fun createOkHttpClient(timeout: Long = 10L): OkHttpClient {

        val httpClient: OkHttpClient.Builder = OkHttpClient().newBuilder()
        httpClient.connectTimeout(timeout, TimeUnit.SECONDS)
        httpClient.callTimeout(timeout, TimeUnit.SECONDS)
        httpClient.readTimeout(timeout, TimeUnit.SECONDS)
        httpClient.writeTimeout(timeout, TimeUnit.SECONDS)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        httpClient.addInterceptor(interceptor)
        return httpClient.build()
    }
}