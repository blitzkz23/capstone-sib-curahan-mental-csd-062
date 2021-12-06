package com.app.curahanmental.ui.settings

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class SettingsViewModel: ViewModel() {
    val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
}