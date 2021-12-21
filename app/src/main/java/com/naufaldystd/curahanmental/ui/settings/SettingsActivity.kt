package com.naufaldystd.curahanmental.ui.settings

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.naufaldystd.curahanmental.databinding.ActivitySettingsBinding
import com.naufaldystd.curahanmental.ui.auth.login.LoginActivity

class SettingsActivity : AppCompatActivity() {
    private lateinit var settingsViewModel: SettingsViewModel
    private val binding: ActivitySettingsBinding by lazy { ActivitySettingsBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViewModel()
    }

    private fun initViewModel(){
        settingsViewModel = ViewModelProvider(this)[SettingsViewModel::class.java]
        settingsViewModel.getCurrentUserDisplayName()
        settingsViewModel.currentData.observe(this){
            binding.apply {
                settingsUserName.text = "${it?.firstName} ${it?.lastName}"
                settingsUserEmail.text = it?.email

                btnLogout.setOnClickListener {
                    settingsViewModel.auth.signOut()
                    startActivity(Intent(this@SettingsActivity, LoginActivity::class.java).also {
                       it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    })
                    finish()
                }

                btnSettingsBack.setOnClickListener {
                    finish()
                }
            }
        }
    }
}