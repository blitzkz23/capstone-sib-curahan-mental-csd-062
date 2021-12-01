package com.app.curahanmental.data.source.local

import com.app.curahanmental.data.source.local.entity.JournalEntity
import com.app.curahanmental.data.source.local.room.JournalDao

class LocalDataSource private constructor(private val journalDao: JournalDao){

    fun insertJournal(journal: JournalEntity) = journalDao.insertJournal(journal)

    fun deleteJournal(journal: JournalEntity) = journalDao.deleteJournal(journal)

    fun getAllJournal() = journalDao.getAllJournal()

    fun getJournalById(id: Int) = journalDao.getJournalById(id)

    companion object{
        @Volatile
        private var INSTANCE: LocalDataSource? = null
        fun getInstance(journalDao: JournalDao): LocalDataSource{
            return INSTANCE ?: getInstance(journalDao).apply {
                INSTANCE = this
            }
        }
    }
}