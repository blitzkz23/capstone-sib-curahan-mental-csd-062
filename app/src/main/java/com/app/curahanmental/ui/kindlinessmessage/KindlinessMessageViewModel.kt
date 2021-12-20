package com.app.curahanmental.ui.kindlinessmessage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.curahanmental.data.source.remote.entity.KindlinessMessageEntity
import com.app.curahanmental.data.source.remote.entity.UserEntity
import com.app.curahanmental.utils.Constants.NODE_MESSAGE
import com.app.curahanmental.utils.Constants.NODE_USER
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue

class KindlinessMessageViewModel : ViewModel() {
	val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
	private val db: FirebaseDatabase by lazy { FirebaseDatabase.getInstance() }
	private val messageRef = db.getReference(NODE_MESSAGE)

	private val _listUser = MutableLiveData<List<KindlinessMessageEntity?>>()

	private val _result = MutableLiveData<Exception?>()
	val result: LiveData<Exception?> get() = _result

	private val _currentData = MutableLiveData<UserEntity?>()

	fun addMessage(message: KindlinessMessageEntity) {
		// Generate auto key id for the object
		message.id = messageRef.push().key

		message.id?.let {
			db.reference.child(NODE_MESSAGE).child(it).setValue(message)
				.addOnCompleteListener { task ->
					if (task.isSuccessful) {
						_result.value = null
					} else {
						_result.value = task.exception
					}
				}
		}
	}

	fun getALlMessage() {
		val reference = db.reference
		reference.child(NODE_MESSAGE).limitToLast(100).addListenerForSingleValueEvent(object : ValueEventListener {
			override fun onDataChange(snapshot: DataSnapshot) {
				val children = snapshot.children
				val list = ArrayList<KindlinessMessageEntity>()
				children.forEach{
					it.getValue(KindlinessMessageEntity::class.java)?.let { it1 -> list.add(it1) }
					list.reverse()
				}
				_listUser.postValue(list)
				Log.d("USERS", "$list")
			}

			override fun onCancelled(error: DatabaseError) {
				Log.e("ERROR", error.message)
			}
		})
	}

	var listUser: LiveData<List<KindlinessMessageEntity?>> = _listUser

	fun getCurrentUserDisplayName(){
		val reference = db.reference
		val userId = auth.currentUser.let { it?.uid }
		if (userId != null) {
			reference.child(NODE_USER).child(userId).addListenerForSingleValueEvent(object :
				ValueEventListener {
				override fun onDataChange(snapshot: DataSnapshot) {
					val userProfile = snapshot.getValue<UserEntity>()
					lateinit var user: UserEntity
					if (userProfile != null) {
						user = UserEntity(userProfile.firstName, userProfile.lastName, userProfile.email)
					}
					_currentData.postValue(user)

					Log.i("Firebase", "Got users value $userProfile")
				}

				override fun onCancelled(error: DatabaseError) {
					Log.e("Firebase", "Failed to load users data")
				}
			})
		}
	}

	val currentData: LiveData<UserEntity?> = _currentData

}