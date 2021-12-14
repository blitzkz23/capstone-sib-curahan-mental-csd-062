package com.app.curahanmental.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.app.curahanmental.data.source.local.entity.JournalEntity
import com.app.curahanmental.data.source.local.room.JournalDao
import com.app.curahanmental.data.source.remote.entity.ArticleEntity
import com.app.curahanmental.utils.JournalsSortType
import com.app.curahanmental.utils.SortUtils
import kotlinx.coroutines.flow.Flow

class LocalDataSource private constructor(private val journalDao: JournalDao){

    fun insertJournal(journal: JournalEntity) = journalDao.insertJournal(journal)

    fun deleteJournal(journal: JournalEntity) = journalDao.deleteJournal(journal)

    fun getAllJournal() = journalDao.getAllJournal()

    fun getJournalWithSorting(sortType: JournalsSortType): LiveData<PagingData<JournalEntity>> {
        val query = SortUtils.getSortedQuery(sortType)
        val config = Config(PAGE_SIZE)
        return Pager(
            PagingConfig(
                config.pageSize,
                config.prefetchDistance,
                config.enablePlaceholders,
                config.initialLoadSizeHint,
                config.maxSize
            ),
            null,
            journalDao.getJournalWithFilter(query).asPagingSourceFactory(
//                ArchTaskExecutor.getIOThreadExecutor().asCoroutineDispatcher()
            )
        ).liveData
    }

    fun getJournalById(id: Int) = journalDao.getJournalById(id)

    fun getAllArticles(): Flow<List<ArticleEntity>> = journalDao.getAllArticles()

    suspend fun insertArticles(articles: List<ArticleEntity>) = journalDao.insertArticles(articles)

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