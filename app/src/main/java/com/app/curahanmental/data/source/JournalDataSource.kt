package com.app.curahanmental.data.source

import androidx.lifecycle.LiveData
import com.app.curahanmental.data.source.local.entity.JournalEntity

interface JournalDataSource {
    fun insertJournal(journalEntity: JournalEntity)
    fun deleteJournal(journalEntity: JournalEntity)
    fun getALlJournal(): LiveData<List<JournalEntity>>
    fun getJournalById(id: Int): LiveData<JournalEntity>
}