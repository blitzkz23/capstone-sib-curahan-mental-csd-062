package com.app.curahanmental.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.app.curahanmental.data.source.local.entity.ArticlesModel
import com.app.curahanmental.data.source.local.entity.JournalEntity
import com.app.curahanmental.utils.JournalsSortType
import kotlinx.coroutines.flow.Flow


interface JournalDataSource {
    fun insertJournal(journalEntity: JournalEntity)
    fun deleteJournal(journalEntity: JournalEntity)
    fun getALlJournal(): LiveData<List<JournalEntity>>
    fun getJournalWithSorting(sortType: JournalsSortType): LiveData<PagingData<JournalEntity>>
    fun getJournalById(id: Int): LiveData<JournalEntity>
    fun getArticles(): Flow<Resource<List<ArticlesModel>>>
}