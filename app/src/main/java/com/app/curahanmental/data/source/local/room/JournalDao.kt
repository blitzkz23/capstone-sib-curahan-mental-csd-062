package com.app.curahanmental.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.app.curahanmental.data.source.local.entity.JournalEntity
import retrofit2.http.GET

@Dao
interface JournalDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insertJournal(journal: JournalEntity)

	@Delete
	fun deleteJournal(journal: JournalEntity)

	@Query("SELECT * FROM journal")
	fun getAllJournal(): LiveData<List<JournalEntity>>

	@Query("SELECT * FROM journal WHERE journal.id = :id")
	fun getJournalById(id: Int): LiveData<JournalEntity>
}