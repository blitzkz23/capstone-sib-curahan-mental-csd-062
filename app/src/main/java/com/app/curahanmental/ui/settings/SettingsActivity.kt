package com.app.curahanmental.ui.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.curahanmental.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    private lateinit var settingsViewModel: SettingsViewModel
    private val binding: ActivitySettingsBinding by lazy { ActivitySettingsBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}