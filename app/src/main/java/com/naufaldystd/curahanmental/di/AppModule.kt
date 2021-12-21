package com.naufaldystd.curahanmental.di

import android.content.Context
import com.naufaldystd.curahanmental.data.source.JournalRepository
import com.naufaldystd.curahanmental.data.source.local.LocalDataSource
import com.naufaldystd.curahanmental.data.source.local.room.JournalDatabase
import com.naufaldystd.curahanmental.data.source.remote.ApiConfig
import com.naufaldystd.curahanmental.data.source.remote.RemoteDataSource

object AppModule {
    fun provideJournalRepository(context: Context): JournalRepository{
        val db = JournalDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig())
        val localDataSource = LocalDataSource.getInstance(db.journalDao())

        return JournalRepository.getInstance(remoteDataSource, localDataSource)
    }
}