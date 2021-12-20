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

class KindlinessMessageViewModel : ViewModel() {
	val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
	private val db : FirebaseDatabase by lazy { FirebaseDatabase.getInstance() }
	private val messageRef = db.getReference(NODE_MESSAGE)

	private var listUser: MutableList<UserEntity?> = mutableListOf()

	private val _result = MutableLiveData<Exception?>()
	val result: LiveData<Exception?> get() = _result

	fun addMessage(message: KindlinessMessageEntity) {
		// Generate auto key id for the object
		message.id = messageRef.push().key

		auth.currentUser?.let {
			message.id?.let { it1 ->
				db.reference.child(NODE_USER).child(it.uid).child(NODE_MESSAGE).child(it1).setValue(message).addOnCompleteListener { task ->
					if (task.isSuccessful) {
						_result.value = null
					} else {
						_result.value = task.exception
					}
				}
			}
		}
	}

	fun getALlUser(){
		val reference = db.reference
		reference.child("users").addListenerForSingleValueEvent(object : ValueEventListener {
			override fun onDataChange(snapshot: DataSnapshot) {
				val children = snapshot.children
				children.forEach {
					listUser.add(it.getValue(UserEntity::class.java))
				}
				Log.d("USERS", "$listUser")
			}
			override fun onCancelled(error: DatabaseError) {
				Log.e("ERROR", error.message)
			}
		})
	}

}