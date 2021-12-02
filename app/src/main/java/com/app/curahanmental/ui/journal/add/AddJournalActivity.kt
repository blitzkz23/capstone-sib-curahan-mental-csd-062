package com.app.curahanmental.ui.journal.add

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.curahanmental.databinding.ActivityAddJournalBinding

class AddJournalActivity : AppCompatActivity() {

	private val binding: ActivityAddJournalBinding by lazy { ActivityAddJournalBinding.inflate(layoutInflater) }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

	}
}