package com.naufaldystd.curahanmental.data.source.remote

import com.naufaldystd.curahanmental.data.source.remote.entity.ArticleResponses
import retrofit2.http.GET

interface ApiServices {
    @GET("everything")
    suspend fun getArticles(): ArticleResponses
}