package com.app.curahanmental.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.curahanmental.data.source.JournalRepository
import com.app.curahanmental.di.AppModule
import com.app.curahanmental.ui.home.HomeViewModel
import com.app.curahanmental.ui.journal.JournalViewModel
import com.app.curahanmental.ui.journal.add.AddJournalViewModel
import com.app.curahanmental.ui.journal.detail.DetailJournalViewModel

class ViewModelFactory private constructor(private val myRepository: JournalRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> { HomeViewModel(myRepository) as T }
            modelClass.isAssignableFrom(AddJournalViewModel::class.java) -> { AddJournalViewModel(myRepository) as T }
            modelClass.isAssignableFrom(JournalViewModel::class.java) -> { JournalViewModel(myRepository) as T }
            modelClass.isAssignableFrom(DetailJournalViewModel::class.java) -> { DetailJournalViewModel(myRepository) as T }
            else -> throw Throwable("Unknown ViewModel Class" + modelClass.name)
        }
    }

    companion object{
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this){
                ViewModelFactory(AppModule.provideJournalRepository(context)).apply { instance = this }
            }
    }
}