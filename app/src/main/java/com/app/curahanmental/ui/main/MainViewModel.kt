package com.app.curahanmental.ui.main

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class MainViewModel : ViewModel() {
	private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
	private val database: FirebaseDatabase by lazy { FirebaseDatabase.getInstance() }
}