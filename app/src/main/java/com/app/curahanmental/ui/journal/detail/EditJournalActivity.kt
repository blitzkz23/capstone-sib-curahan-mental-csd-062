package com.app.curahanmental.ui.journal.detail

import android.os.Bundle
import android.widget.TextView
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

	private val binding: ActivityEditJournalBinding by lazy {
		ActivityEditJournalBinding.inflate(
			layoutInflater
		)
	}
	private lateinit var detailJournalViewModel: DetailJournalViewModel
	private var journalId: Int = 0
	private var date: Long = 0

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

		val factory = ViewModelFactory.getInstance(this)
		detailJournalViewModel =
			ViewModelProvider(this, factory)[DetailJournalViewModel::class.java]

		journalId = intent.getIntExtra(JOURNAL, 0)

		detailJournalViewModel.setSelectedJournal(journalId)
		detailJournalViewModel.journalDetail.observe(this, { journalEntity ->
			populateJournal(journalEntity)
		})


		binding.updateButton.setOnClickListener {
			updateJournal()
			finish()
		}


	}

	private fun populateJournal(journalEntity: JournalEntity?) {
		binding.apply {
			loadActionBar(journalEntity)

			journalEntity?.let {
				sliderEdit.value = journalEntity.stressLevel.toFloat()
				eventDetailTextEdit.setText(journalEntity.eventDetail)
				manageEventDetailTextEdit.setText(journalEntity.idealEventScenario)
				reasonDetailTextEdit.setText(journalEntity.reasonDetail)
				date = journalEntity.date
			}
		}
	}

	private fun updateJournal() {
		val stressLevel = binding.sliderEdit.value.toInt()
		val event = binding.spinnerEdit.selectedItem.toString()
		val eventDetail = binding.eventDetailTextEdit.text.toString().trim()
		val manageEvent = binding.spinner2Edit.selectedItem.toString()
		val idealEventScenario = binding.manageEventDetailTextEdit.text.toString().trim()
		val reason = binding.spinner3Edit.selectedItem.toString()
		val reasonDetail = binding.reasonDetailTextEdit.text.toString().trim()

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
		try {
			detailJournalViewModel.updateJournal(journal)
		} catch (e: Exception) {
			print("GABISA $e")
		}

	}

	private fun loadActionBar(journalEntity: JournalEntity?) {
		findViewById<MaterialButton>(R.id.back_button).setOnClickListener {
			super.onBackPressed()
		}
		findViewById<TextView>(R.id.journal_title).text = getString(R.string.edit_journal,
			journalEntity?.date?.let { DateUtils.convertMillisToString(it) })
		journalEntity?.let {
			binding.eventDetailTextEdit.setText(journalEntity.eventDetail)
		}
	}

	companion object {
		const val JOURNAL = EXTRA_JOURNAL
	}
}