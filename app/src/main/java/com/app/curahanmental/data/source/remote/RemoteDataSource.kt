package com.app.curahanmental.data.source.remote

import com.app.curahanmental.data.source.remote.entity.ArticleResponses
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource private constructor(private val api: ApiConfig){
    suspend fun getArticles(): Flow<ApiResponses<ArticleResponses>>{
        return flow {
            try {
                val responses = api.getServices().getArticles()
                emit(ApiResponses.success(responses))
            } catch (e: Exception){
                e.printStackTrace()
            }
        }.flowOn(Dispatchers.IO)
    }

    companion object{
        @Volatile
        private var INSTANCE: RemoteDataSource? = null
        fun getInstance(api: ApiConfig): RemoteDataSource =
            INSTANCE ?: synchronized(this){
                RemoteDataSource(api).apply { INSTANCE = this }
            }
    }
}