package com.app.curahanmental.ui.kindlinessmessage.add

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.curahanmental.databinding.ActivityAddKindlinessMessageBinding

class AddKindlinessMessageActivity : AppCompatActivity() {

	private val binding: ActivityAddKindlinessMessageBinding by lazy {
		ActivityAddKindlinessMessageBinding.inflate(
			layoutInflater
		)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)
	}
}