package com.app.curahanmental.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class JournalViewModel : ViewModel() {

	private val _text = MutableLiveData<String>().apply {
		value = "This is journal Fragment"
	}
	val text: LiveData<String> = _text
}