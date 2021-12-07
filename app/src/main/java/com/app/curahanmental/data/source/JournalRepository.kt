package com.app.curahanmental.data.source

import androidx.lifecycle.LiveData
import com.app.curahanmental.data.source.local.LocalDataSource
import com.app.curahanmental.data.source.local.entity.JournalEntity
import com.app.curahanmental.data.source.remote.ApiResponses
import com.app.curahanmental.data.source.remote.RemoteDataSource
import com.app.curahanmental.data.source.remote.entity.ArticleResponses
import kotlinx.coroutines.flow.Flow

class JournalRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
): JournalDataSource {
    override fun insertJournal(journalEntity: JournalEntity) {
        localDataSource.insertJournal(journalEntity)
    }

    override fun deleteJournal(journalEntity: JournalEntity) {
        localDataSource.deleteJournal(journalEntity)
    }

    override fun getALlJournal(): LiveData<List<JournalEntity>> {
        return localDataSource.getAllJournal()
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
        fun getInstance(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource): JournalRepository =
            INSTANCE ?: synchronized(this){
                JournalRepository(localDataSource, remoteDataSource).apply {
                    INSTANCE = this
                }
            }
    }
}