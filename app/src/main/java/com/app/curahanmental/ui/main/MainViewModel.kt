package com.app.curahanmental.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.curahanmental.data.source.remote.entity.UserEntity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue

class MainViewModel : ViewModel() {
	private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
	private val database: FirebaseDatabase by lazy { FirebaseDatabase.getInstance() }

	fun getUser(): LiveData<UserEntity> {
		val userResult = MutableLiveData<UserEntity>()

		val reference = database.reference
		val userId = auth.uid

		if (userId != null) {
			reference.child("users").child(userId).addListenerForSingleValueEvent(object : ValueEventListener {
				override fun onDataChange(snapshot: DataSnapshot) {
					val userProfile = snapshot.getValue<UserEntity>()

					lateinit var user: UserEntity
					if (userProfile != null) {
						user = UserEntity(
							userProfile.firstName,
							userProfile.lastName,
							userProfile.email,
							userProfile.password,
						)
						userResult.postValue(user)
					}
					Log.i("firebase", "Got value $userProfile")
				}

				override fun onCancelled(error: DatabaseError) {
					Log.e("Firebase", "Failed to load user's data")
				}
			})
		}
		return userResult
	}
}