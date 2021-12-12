package com.app.curahanmental.ui.auth.register

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.curahanmental.data.source.remote.entity.UserEntity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterViewModel : ViewModel() {
	private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
	private val database: FirebaseDatabase by lazy { FirebaseDatabase.getInstance() }
	private val _authRes = MutableLiveData<Task<AuthResult>>()

	fun signUpUser(firstName: String, lastName: String, email: String, password: String) {
		auth.fetchSignInMethodsForEmail(email)
			.addOnCompleteListener { task ->
				Log.d(TAG, "" + task.result?.signInMethods?.size)
				if (task.result?.signInMethods?.size == 0) {
					Log.d(TAG, "E-mail have not exist yet")
				} else {
					Log.d(TAG, "Email already existed")
				}
			}.addOnFailureListener { e ->
				e.printStackTrace()
			}

		auth.createUserWithEmailAndPassword(email, password)
			.addOnCompleteListener { task ->
				val user = UserEntity(
					firstName = firstName,
					lastName = lastName,
					email = email,
				)
				auth.currentUser?.let {
					database.reference.child("users").child(it.uid).setValue(user)
				}
				_authRes.postValue(task)
			}.addOnFailureListener { e ->
				e.printStackTrace()
			}
	}

	val authRes: LiveData<Task<AuthResult>> = _authRes

}