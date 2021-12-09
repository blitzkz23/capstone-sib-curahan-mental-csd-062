package com.app.curahanmental.ui.home.tips

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.curahanmental.databinding.ActivityTipsBinding

class TipsActivity : AppCompatActivity() {
    private val binding: ActivityTipsBinding by lazy { ActivityTipsBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}