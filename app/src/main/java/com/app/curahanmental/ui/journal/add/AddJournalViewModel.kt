package com.app.curahanmental.ui.journal.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.curahanmental.data.source.JournalRepository
import com.app.curahanmental.data.source.local.entity.JournalEntity
import kotlinx.coroutines.launch

class AddJournalViewModel(private val repository: JournalRepository) : ViewModel() {

	fun insertJournal(journal: JournalEntity) {
		viewModelScope.launch {
			repository.insertJournal(journal)
		}
	}
}