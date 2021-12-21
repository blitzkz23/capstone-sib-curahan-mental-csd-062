package com.naufaldystd.curahanmental.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.naufaldystd.curahanmental.data.source.local.entity.ArticlesModel
import com.naufaldystd.curahanmental.data.source.local.entity.JournalEntity
import com.naufaldystd.curahanmental.utils.JournalsSortType
import kotlinx.coroutines.flow.Flow


interface JournalDataSource {
    fun insertJournal(journal: JournalEntity)
    fun updateJournal(journal: JournalEntity)
    fun deleteJournal(journal: JournalEntity)
    fun getALlJournal(): LiveData<List<JournalEntity>>
    fun getJournalWithSorting(sortType: JournalsSortType): LiveData<PagingData<JournalEntity>>
    fun getJournalById(id: Int): LiveData<JournalEntity>
    fun getArticles(): Flow<Resource<List<ArticlesModel>>>
}