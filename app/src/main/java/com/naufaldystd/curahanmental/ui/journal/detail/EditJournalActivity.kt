package com.naufaldystd.curahanmental.ui.journal.detail

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.naufaldystd.curahanmental.R
import com.naufaldystd.curahanmental.data.source.local.entity.JournalEntity
import com.naufaldystd.curahanmental.databinding.ActivityEditJournalBinding
import com.naufaldystd.curahanmental.ui.viewmodel.ViewModelFactory
import com.naufaldystd.curahanmental.utils.Constants.EXTRA_JOURNAL
import com.naufaldystd.curahanmental.utils.DateUtils

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
			id = journalId,
			stressLevel = stressLevel,
			event = event,
			eventDetail = eventDetail,
			manageEvent = manageEvent,
			idealEventScenario = idealEventScenario,
			reason = reason,
			reasonDetail = reasonDetail,
			date = date
		)
		Log.i("Ini cara mendebug room", "$journal")
		detailJournalViewModel.updateJournal(journal)

	}

	private fun loadActionBar(journalEntity: JournalEntity?) {
		findViewById<ImageView>(R.id.back_button_fragment).setOnClickListener {
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