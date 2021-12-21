package com.naufaldystd.curahanmental.ui.journal.add

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.naufaldystd.curahanmental.databinding.ActivityAddJournalBinding

class AddJournalActivity : AppCompatActivity() {

	private val binding: ActivityAddJournalBinding by lazy { ActivityAddJournalBinding.inflate(layoutInflater) }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

	}

}