package com.naufaldystd.curahanmental.ui.journal.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naufaldystd.curahanmental.data.source.JournalRepository
import com.naufaldystd.curahanmental.data.source.local.entity.JournalEntity
import kotlinx.coroutines.launch

class AddJournalViewModel(private val repository: JournalRepository) : ViewModel() {

	fun insertJournal(journal: JournalEntity) {
		viewModelScope.launch {
			repository.insertJournal(journal)
		}
	}
}