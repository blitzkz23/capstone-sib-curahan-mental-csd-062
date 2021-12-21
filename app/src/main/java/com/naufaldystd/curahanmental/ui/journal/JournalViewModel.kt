package com.naufaldystd.curahanmental.ui.journal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.paging.PagingData
import com.naufaldystd.curahanmental.data.source.JournalRepository
import com.naufaldystd.curahanmental.data.source.local.entity.JournalEntity
import com.naufaldystd.curahanmental.utils.JournalsSortType

class JournalViewModel(private val repository: JournalRepository) : ViewModel() {

	private val _sort = MutableLiveData<JournalsSortType>()

	val journals: LiveData<PagingData<JournalEntity>> = _sort.switchMap {
		repository.getJournalWithSorting(it)
	}

	init {
		_sort.value = JournalsSortType.ALL_JOURNALS
	}

	fun sort(sortType: JournalsSortType) {
		_sort.value = sortType
	}

	fun deleteSwiped(journal: JournalEntity) {
		repository.deleteJournal(journal)
	}

}