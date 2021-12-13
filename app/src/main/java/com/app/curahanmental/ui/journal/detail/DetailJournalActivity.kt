package com.app.curahanmental.ui.journal.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.curahanmental.R
import com.app.curahanmental.databinding.ActivityDetailJournalBinding
import com.app.curahanmental.utils.Constants.EXTRA_JOURNAL
import com.google.android.material.button.MaterialButton

class DetailJournalActivity : AppCompatActivity() {

	private val binding: ActivityDetailJournalBinding by lazy { ActivityDetailJournalBinding.inflate(layoutInflater) }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)
		loadActionBar()

	}

	private fun loadActionBar() {
		findViewById<MaterialButton>(R.id.back_button).setOnClickListener {
			super.onBackPressed()
		}
		findViewById<MaterialButton>(R.id.delete_button).setOnClickListener {
			Toast.makeText(this, "Delete Journal?", Toast.LENGTH_SHORT).show()
		}
	}

	companion object {
		const val JOURNAL = EXTRA_JOURNAL
	}
}