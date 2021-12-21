package com.naufaldystd.curahanmental.data.source

import com.naufaldystd.curahanmental.data.source.remote.ApiResponses
import kotlinx.coroutines.flow.*


abstract class NetworkBoundResource<ResultType, RequestType> {
    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        val dbSource = loadFromDB().first()
        if (shouldFetch(dbSource)){
            emit(Resource.Loading())
            when(val apiResponses = createCall().first()){
                is ApiResponses.Success -> {
                    saveCallResult(apiResponses.data)
                    emitAll(loadFromDB().map { Resource.Success(it) })
                }
                is ApiResponses.Empty -> {
                    emitAll(loadFromDB().map { Resource.Success(it) })
                }
                is ApiResponses.Error -> {
                    onFetchFailed()
                    emit(Resource.Error(apiResponses.errorMessage))
                }
            }
        } else{
            emitAll(loadFromDB().map { Resource.Success(it) })
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponses<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<Resource<ResultType>> = result
}