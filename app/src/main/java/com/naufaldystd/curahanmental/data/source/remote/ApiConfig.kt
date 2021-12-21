package com.naufaldystd.curahanmental.data.source.remote

import com.naufaldystd.curahanmental.BuildConfig
import com.naufaldystd.curahanmental.utils.Constants.API_KEY
import com.naufaldystd.curahanmental.utils.Constants.BASE_URL
import com.naufaldystd.curahanmental.utils.Constants.PARAMS
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    private fun getInterceptor(): OkHttpClient {
        val loggingInterceptor =
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            } else {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
            }
        return OkHttpClient.Builder()
            .addInterceptor{ chain ->
                var request = chain.request()
                val url = request.url
                    .newBuilder()
                    .addQueryParameter("q", PARAMS)
                    .addQueryParameter("apiKey", API_KEY)
                    .build()
                request = request.newBuilder().url(url).addHeader("Accept", "application/json").build()
                chain.proceed(request)
            }
            .addInterceptor(loggingInterceptor)
            .build()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getServices(): ApiServices = getRetrofit().create(ApiServices::class.java)
}