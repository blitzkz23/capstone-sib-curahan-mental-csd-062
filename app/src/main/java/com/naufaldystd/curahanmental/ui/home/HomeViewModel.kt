package com.naufaldystd.curahanmental.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.naufaldystd.curahanmental.data.source.JournalRepository
import com.naufaldystd.curahanmental.data.source.remote.entity.UserEntity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue

class HomeViewModel(private val repository: JournalRepository) : ViewModel() {
	val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
	private val db: FirebaseDatabase by lazy { FirebaseDatabase.getInstance() }
	private val _currentData = MutableLiveData<String?>()

	fun getCurrentUserDisplayName(){
		val reference = db.reference
		val userId = auth.currentUser.let { it?.uid }
		if (userId != null) {
			reference.child("users").child(userId).addListenerForSingleValueEvent(object :
				ValueEventListener {
				override fun onDataChange(snapshot: DataSnapshot) {
					val userProfile = snapshot.getValue<UserEntity>()
					lateinit var user: UserEntity
					if (userProfile != null) {
						user = UserEntity(userProfile.firstName)
					}
					_currentData.postValue(user.firstName)

					Log.i("Firebase", "Got users value: $userProfile")
				}

				override fun onCancelled(error: DatabaseError) {
					Log.e("Firebase", "Failed to load users data")
				}
			})
		}
	}

	val currentData: LiveData<String?> = _currentData

	val articles = repository.getArticles().asLiveData()
}