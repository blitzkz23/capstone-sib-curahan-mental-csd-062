package com.naufaldystd.curahanmental.ui.kindlinessmessage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.naufaldystd.curahanmental.data.source.remote.entity.KindlinessMessageEntity
import com.naufaldystd.curahanmental.data.source.remote.entity.UserEntity
import com.naufaldystd.curahanmental.utils.Constants.NODE_MESSAGE
import com.naufaldystd.curahanmental.utils.Constants.NODE_USER
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue

class KindlinessMessageViewModel : ViewModel() {
	val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
	private val db: FirebaseDatabase by lazy { FirebaseDatabase.getInstance() }
	private val messageRef = db.getReference(NODE_MESSAGE)

	private val _result = MutableLiveData<Exception?>()
	val result: LiveData<Exception?> get() = _result

	private val _currentData = MutableLiveData<UserEntity?>()
	val currentData: LiveData<UserEntity?> = _currentData

	private val _message = MutableLiveData<KindlinessMessageEntity?>()
	val message: LiveData<KindlinessMessageEntity?> get() = _message

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

	private val childEventListener = object : ChildEventListener {
		override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
			val message = snapshot.getValue(KindlinessMessageEntity::class.java)

			message?.id = snapshot.key
			_message.value = message
		}

		override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}

		override fun onChildRemoved(snapshot: DataSnapshot) {}

		override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

		override fun onCancelled(error: DatabaseError) {}

	}

	fun getRealtimeMessage() {
		messageRef.addChildEventListener(childEventListener)
	}

	override fun onCleared() {
		super.onCleared()
		messageRef.removeEventListener(childEventListener)
	}

	fun getCurrentUserDisplayName() {
		val reference = db.reference
		val userId = auth.currentUser.let { it?.uid }
		if (userId != null) {
			reference.child(NODE_USER).child(userId).addListenerForSingleValueEvent(object :
				ValueEventListener {
				override fun onDataChange(snapshot: DataSnapshot) {
					val userProfile = snapshot.getValue<UserEntity>()
					lateinit var user: UserEntity
					if (userProfile != null) {
						user = UserEntity(
							userProfile.firstName,
							userProfile.lastName,
							userProfile.email
						)
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
}