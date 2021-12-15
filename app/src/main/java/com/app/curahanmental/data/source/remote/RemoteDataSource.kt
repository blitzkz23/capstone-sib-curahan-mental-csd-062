package com.app.curahanmental.data.source.remote

import android.util.Log
import com.app.curahanmental.data.source.remote.entity.ArticleEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource private constructor(private val api: ApiConfig){
    suspend fun getArticles(): Flow<ApiResponses<List<ArticleEntity>>> {
        return flow {
            lateinit var data: List<ArticleEntity>
            try {
                val responses = api.getServices().getArticles()
                data = responses.listOfArticles!!
                emit(ApiResponses.Success(data))
            } catch (e: Exception){
                emit(ApiResponses.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
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