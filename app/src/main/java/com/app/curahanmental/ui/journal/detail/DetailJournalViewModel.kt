package com.app.curahanmental.ui.journal.detail

import androidx.lifecycle.*
import com.app.curahanmental.data.source.JournalRepository
import com.app.curahanmental.data.source.local.entity.JournalEntity
import kotlinx.coroutines.launch

class DetailJournalViewModel(private val repository: JournalRepository) : ViewModel() {
	private var journalId = MutableLiveData<Int>()

	fun setSelectedJournal(journalId: Int) {
		this.journalId.value = journalId
	}

	var journalDetail: LiveData<JournalEntity> = Transformations.switchMap(journalId) { mJournalId ->
		repository.getJournalById(mJournalId)
	}

	fun deleteJournal() {
		viewModelScope.launch {
			journalDetail.value?.let {
				repository.deleteJournal(it)
			}
		}
	}
}