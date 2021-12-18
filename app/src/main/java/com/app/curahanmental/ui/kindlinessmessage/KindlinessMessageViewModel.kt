package com.app.curahanmental.ui.kindlinessmessage

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

class KindlinessMessageViewModel : ViewModel() {
	val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
	private val db : FirebaseDatabase by lazy { FirebaseDatabase.getInstance() }
	private var listUser: MutableList<UserEntity?> = mutableListOf()

	private val _text = MutableLiveData<String>().apply {
		value = "This is support message Fragment"
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

	val text: LiveData<String> = _text
}