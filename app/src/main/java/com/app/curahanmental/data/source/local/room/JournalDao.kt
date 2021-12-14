package com.app.curahanmental.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.app.curahanmental.data.source.local.entity.JournalEntity
import com.app.curahanmental.data.source.remote.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface JournalDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insertJournal(journal: JournalEntity)

	@Delete
	fun deleteJournal(journal: JournalEntity)

	@Query("SELECT * FROM journal")
	fun getAllJournal(): LiveData<List<JournalEntity>>

	@RawQuery(observedEntities = [JournalEntity::class])
	fun getJournalWithFilter(query: SupportSQLiteQuery): DataSource.Factory<Int, JournalEntity>

	@Query("SELECT * FROM journal WHERE journal.id = :id")
	fun getJournalById(id: Int): LiveData<JournalEntity>

	@Insert(onConflict = OnConflictStrategy.IGNORE)
	suspend fun insertArticles(articles: List<ArticleEntity>)

	@Query("SELECT * FROM articles")
	fun getAllArticles(): Flow<List<ArticleEntity>>
}