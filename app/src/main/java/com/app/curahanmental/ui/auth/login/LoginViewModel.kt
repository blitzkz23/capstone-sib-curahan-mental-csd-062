package com.app.curahanmental.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel() {
	private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
	private val _authRes = MutableLiveData<Task<AuthResult>>()

	val currentUser = auth.currentUser

	fun signInUser(email: String, password: String) {
		auth.signInWithEmailAndPassword(email, password)
			.addOnCompleteListener {
				_authRes.postValue(it)
			}
	}

	val authRes: LiveData<Task<AuthResult>> = _authRes
}