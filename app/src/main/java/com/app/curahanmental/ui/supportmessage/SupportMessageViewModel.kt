package com.app.curahanmental.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SupportMessageViewModel : ViewModel() {

	private val _text = MutableLiveData<String>().apply {
		value = "This is support message Fragment"
	}
	val text: LiveData<String> = _text
}