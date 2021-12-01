package com.app.curahanmental.di

import android.content.Context
import com.app.curahanmental.data.source.JournalRepository
import com.app.curahanmental.data.source.local.LocalDataSource
import com.app.curahanmental.data.source.local.room.JournalDatabase

object AppModule {
    fun provideJournalRepository(context: Context): JournalRepository{
        val db = JournalDatabase.getInstance(context)
        val localDataSource = LocalDataSource.getInstance(db.journalDao())

        return JournalRepository.getInstance(localDataSource)
    }
}