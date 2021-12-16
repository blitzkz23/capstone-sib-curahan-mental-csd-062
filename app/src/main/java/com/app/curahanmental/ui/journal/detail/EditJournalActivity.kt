package com.app.curahanmental.ui.journal.detail

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.app.curahanmental.R
import com.app.curahanmental.data.source.local.entity.JournalEntity
import com.app.curahanmental.databinding.ActivityEditJournalBinding
import com.app.curahanmental.ui.viemodel.ViewModelFactory
import com.app.curahanmental.utils.Constants.EXTRA_JOURNAL
import com.app.curahanmental.utils.DateUtils
import com.google.android.material.button.MaterialButton

class EditJournalActivity : AppCompatActivity() {

	private val binding : ActivityEditJournalBinding by lazy { ActivityEditJournalBinding.inflate(layoutInflater) }
	private lateinit var detailJournalViewModel: DetailJournalViewModel
	private var journalId: Int = 0
	private var date: Long = 0

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_edit_journal)

		val factory = ViewModelFactory.getInstance(this)
		detailJournalViewModel =
			ViewModelProvider(this, factory)[DetailJournalViewModel::class.java]

		journalId = intent.getIntExtra(JOURNAL, 0)

		detailJournalViewModel.setSelectedJournal(journalId)
		detailJournalViewModel.journalDetail.observe(this, { journalEntity ->
			populateJournal(journalEntity)
			Log.i(TAG, "EDIT ${journalEntity.reasonDetail}")
		})

		binding.updateButton.setOnClickListener {
			updateJournal()
			Toast.makeText(this, "ASU", Toast.LENGTH_SHORT).show()
			finish()
		}

	}

	private fun populateJournal(journalEntity: JournalEntity?) {
		binding.apply {
			loadActionBar(journalEntity)

			journalEntity?.apply {
				Log.i(TAG, "EDIT 2 ${journalEntity.reasonDetail}")
				sliderEdit.value = journalEntity.stressLevel.toFloat()
				eventDetailTextEdit.setText(journalEntity.eventDetail)
				eventTitle.text = journalEntity.event
				manageEventDetailTextEdit.setText(journalEntity.idealEventScenario)
				reasonDetailTextEdit.setText(journalEntity.reasonDetail)
				date = journalEntity.date
			}
		}
	}

	private fun updateJournal() {
		binding.apply {
			val stressLevel = sliderEdit.value.toInt()
			val event = spinnerEdit.selectedItem.toString()
			val eventDetail = eventDetailTextEdit.text.toString().trim()
			val manageEvent = spinner2Edit.selectedItem.toString()
			val idealEventScenario = manageEventDetailTextEdit.text.toString().trim()
			val reason = spinner3Edit.selectedItem.toString()
			val reasonDetail = reasonDetailTextEdit.text.toString().trim()

			Log.i(TAG, "ASU EDIT 3 $reasonDetail")

			val journal = JournalEntity(
				stressLevel = stressLevel,
				event = event,
				eventDetail = eventDetail,
				manageEvent = manageEvent,
				idealEventScenario = idealEventScenario,
				reason = reason,
				reasonDetail = reasonDetail,
				date = date
			)
			detailJournalViewModel.updateJournal(journal)

		}

	}

	private fun loadActionBar(journalEntity: JournalEntity?) {
		findViewById<MaterialButton>(R.id.back_button).setOnClickListener {
			super.onBackPressed()
		}
		findViewById<TextView>(R.id.journal_title).text =
			getString(R.string.journal,
				journalEntity?.let { DateUtils.convertMillisToString(journalEntity.date) })
	}

	companion object {
		const val JOURNAL = EXTRA_JOURNAL
	}
}