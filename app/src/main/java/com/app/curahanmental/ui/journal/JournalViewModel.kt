package com.app.curahanmental.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.curahanmental.data.source.JournalRepository

class JournalViewModel(private val repository: JournalRepository) : ViewModel() {

	private val _text = MutableLiveData<String>().apply {
		value = "This is journal Fragment"
	}
	val text: LiveData<String> = _text
}