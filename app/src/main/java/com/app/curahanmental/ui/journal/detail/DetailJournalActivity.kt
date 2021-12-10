package com.app.curahanmental.ui.journal.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.curahanmental.R
import com.app.curahanmental.utils.Constants.EXTRA_JOURNAL

class DetailJournalActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_detail_journal)
	}

	companion object {
		const val JOURNAL = EXTRA_JOURNAL
	}
}