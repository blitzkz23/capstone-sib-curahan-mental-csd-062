package com.app.curahanmental.ui.journal.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.app.curahanmental.data.source.JournalRepository
import com.app.curahanmental.data.source.local.entity.JournalEntity

class DetailJournalViewModel(private val repository: JournalRepository) : ViewModel() {
	private var journalId = MutableLiveData<Int>()

	fun setSelectedJournal(journalId: Int) {
		this.journalId.value = journalId
	}

	var journalDetail: LiveData<JournalEntity> = Transformations.switchMap(journalId) { mJournalId ->
		repository.getJournalById(mJournalId)
	}
}