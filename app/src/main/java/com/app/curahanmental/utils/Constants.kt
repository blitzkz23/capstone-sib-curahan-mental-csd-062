package com.app.curahanmental.utils

import com.app.curahanmental.BuildConfig
import java.util.concurrent.Executors

object Constants {
    const val SPLASH_TIMER = 2000L
    const val BASE_URL = BuildConfig.NEWS_API_BASE
    const val API_KEY = BuildConfig.NEWS_API_KEY
    const val PARAMS = BuildConfig.PARAMS
    const val EXTRA_ARTICLE = "extra_article"

    private val SINGLE_EXECUTOR = Executors.newSingleThreadExecutor()

    fun executeThread(f: () -> Unit) {
        SINGLE_EXECUTOR.execute(f)
    }

}