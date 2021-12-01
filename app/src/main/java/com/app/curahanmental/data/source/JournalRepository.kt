package com.app.curahanmental.data.source

import androidx.lifecycle.LiveData
import com.app.curahanmental.data.source.local.LocalDataSource
import com.app.curahanmental.data.source.local.entity.JournalEntity

class JournalRepository(private val localDataSource: LocalDataSource): JournalDataSource {
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

    companion object{
        @Volatile
        private var INSTANCE: JournalRepository? = null
        fun getInstance(localDataSource: LocalDataSource): JournalRepository =
            INSTANCE ?: synchronized(this){
                JournalRepository(localDataSource).apply {
                    INSTANCE = this
                }
            }
    }
}