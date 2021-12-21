package com.naufaldystd.curahanmental.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.naufaldystd.curahanmental.data.source.local.LocalDataSource
import com.naufaldystd.curahanmental.data.source.local.entity.ArticlesModel
import com.naufaldystd.curahanmental.data.source.local.entity.JournalEntity
import com.naufaldystd.curahanmental.data.source.remote.ApiResponses
import com.naufaldystd.curahanmental.data.source.remote.RemoteDataSource
import com.naufaldystd.curahanmental.data.source.remote.entity.ArticleEntity
import com.naufaldystd.curahanmental.utils.Constants.executeThread
import com.naufaldystd.curahanmental.utils.DataMapper
import com.naufaldystd.curahanmental.utils.JournalsSortType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class JournalRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): JournalDataSource {
    override fun insertJournal(journal: JournalEntity) = executeThread {
        localDataSource.insertJournal(journal)
    }

    override fun updateJournal(journal: JournalEntity) = executeThread {
        localDataSource.updateJournal(journal)
    }

    override fun deleteJournal(journal: JournalEntity) {
        localDataSource.deleteJournal(journal)
    }

    override fun getALlJournal(): LiveData<List<JournalEntity>> {
        return localDataSource.getAllJournal()
    }

    override fun getJournalWithSorting(sortType: JournalsSortType): LiveData<PagingData<JournalEntity>> {
        return localDataSource.getJournalWithSorting(sortType)
    }

    override fun getJournalById(id: Int): LiveData<JournalEntity> {
        return localDataSource.getJournalById(id)
    }

    override fun getArticles(): Flow<Resource<List<ArticlesModel>>> =
        object : NetworkBoundResource<List<ArticlesModel>, List<ArticleEntity>>() {
            override fun loadFromDB(): Flow<List<ArticlesModel>> {
                return localDataSource.getAllArticles().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<ArticlesModel>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponses<List<ArticleEntity>>> {
                return remoteDataSource.getArticles()
            }

            override suspend fun saveCallResult(data: List<ArticleEntity>) {
                val articleList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertArticles(articleList)
            }

        }.asFlow()


    companion object{
        @Volatile
        private var INSTANCE: JournalRepository? = null
        fun getInstance(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource): JournalRepository =
            INSTANCE ?: synchronized(this){
                JournalRepository(remoteDataSource, localDataSource).apply {
                    INSTANCE = this
                }
            }
    }
}