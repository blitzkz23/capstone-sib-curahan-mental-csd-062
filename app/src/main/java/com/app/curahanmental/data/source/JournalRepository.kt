package com.app.curahanmental.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.app.curahanmental.data.source.local.LocalDataSource
import com.app.curahanmental.data.source.local.entity.JournalEntity
import com.app.curahanmental.data.source.remote.ApiResponses
import com.app.curahanmental.data.source.remote.RemoteDataSource
import com.app.curahanmental.data.source.remote.entity.ArticleResponses
import com.app.curahanmental.utils.Constants.executeThread
import com.app.curahanmental.utils.JournalsSortType
import kotlinx.coroutines.flow.Flow

class JournalRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): JournalDataSource {
    override fun insertJournal(journalEntity: JournalEntity) = executeThread {
        localDataSource.insertJournal(journalEntity)
    }

    override fun deleteJournal(journalEntity: JournalEntity) {
        localDataSource.deleteJournal(journalEntity)
    }

    override fun getALlJournal(): LiveData<List<JournalEntity>> {
        return localDataSource.getAllJournal()
    }

    override fun getJournalWithFilter(filter: JournalsSortType): LiveData<PagedList<JournalEntity>> {
        return localDataSource.getJournalWithFilter(filter)
    }

    override fun getJournalById(id: Int): LiveData<JournalEntity> {
        return localDataSource.getJournalById(id)
    }

    override suspend fun getArticles(): Flow<ApiResponses<ArticleResponses>> {
        return remoteDataSource.getArticles()
    }

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