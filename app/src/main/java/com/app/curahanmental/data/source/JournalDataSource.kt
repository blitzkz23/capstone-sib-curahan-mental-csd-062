package com.app.curahanmental.data.source

import androidx.lifecycle.LiveData
import com.app.curahanmental.data.source.local.entity.JournalEntity
import com.app.curahanmental.data.source.remote.ApiResponses
import com.app.curahanmental.data.source.remote.entity.ArticleResponses
import kotlinx.coroutines.flow.Flow


interface JournalDataSource {
    fun insertJournal(journalEntity: JournalEntity)
    fun deleteJournal(journalEntity: JournalEntity)
    fun getALlJournal(): LiveData<List<JournalEntity>>
    fun getJournalById(id: Int): LiveData<JournalEntity>
    suspend fun getArticles(): Flow<ApiResponses<ArticleResponses>>
}