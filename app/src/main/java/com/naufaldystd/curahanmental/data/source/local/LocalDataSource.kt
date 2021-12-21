package com.naufaldystd.curahanmental.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.naufaldystd.curahanmental.data.source.local.entity.Articles
import com.naufaldystd.curahanmental.data.source.local.entity.JournalEntity
import com.naufaldystd.curahanmental.data.source.local.room.JournalDao
import com.naufaldystd.curahanmental.utils.JournalsSortType
import com.naufaldystd.curahanmental.utils.SortUtils
import kotlinx.coroutines.flow.Flow

class LocalDataSource private constructor(private val journalDao: JournalDao){

    fun insertJournal(journal: JournalEntity) = journalDao.insertJournal(journal)

    fun updateJournal(journal: JournalEntity) = journalDao.updateJournal(journal)

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

    fun getAllArticles(): Flow<List<Articles>> = journalDao.getAllArticles()

    suspend fun insertArticles(articles: List<Articles>) = journalDao.insertArticles(articles)

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