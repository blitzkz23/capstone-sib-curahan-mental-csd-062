package com.app.curahanmental.ui.viemodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.curahanmental.data.source.JournalRepository
import com.app.curahanmental.di.AppModule
import com.app.curahanmental.ui.home.HomeViewModel

class ViewModelFactory private constructor(private val myRepository: JournalRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> { HomeViewModel(myRepository) as T }
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