package com.app.curahanmental.ui.settings

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

class SettingsViewModel: ViewModel() {
    val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val db: FirebaseDatabase by lazy { FirebaseDatabase.getInstance() }
    private val _currentData = MutableLiveData<UserEntity?>()

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