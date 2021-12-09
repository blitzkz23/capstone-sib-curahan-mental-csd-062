package com.app.curahanmental.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.Config
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.app.curahanmental.data.source.local.entity.JournalEntity
import com.app.curahanmental.data.source.local.room.JournalDao
import com.app.curahanmental.utils.JournalsSortType
import com.app.curahanmental.utils.SortUtils

class LocalDataSource private constructor(private val journalDao: JournalDao){

    fun insertJournal(journal: JournalEntity) = journalDao.insertJournal(journal)

    fun deleteJournal(journal: JournalEntity) = journalDao.deleteJournal(journal)

    fun getAllJournal() = journalDao.getAllJournal()

    fun getJournalWithFilter(filter: JournalsSortType): LiveData<PagedList<JournalEntity>> {
        val query = SortUtils.getSortedQuery(filter)
        return journalDao.getJournalWithFilter(query).toLiveData(Config(PAGE_SIZE))
    }

    fun getJournalById(id: Int) = journalDao.getJournalById(id)

    companion object{
        @Volatile
        private var INSTANCE: LocalDataSource? = null
        const val PAGE_SIZE = 30
        fun getInstance(journalDao: JournalDao): LocalDataSource{
            return INSTANCE ?: LocalDataSource(journalDao).apply {
                INSTANCE = this
            }
        }
    }
}