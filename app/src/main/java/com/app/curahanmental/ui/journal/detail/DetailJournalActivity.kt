package com.app.curahanmental.ui.journal.detail

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.ViewModelProvider
import com.app.curahanmental.R
import com.app.curahanmental.data.source.local.entity.JournalEntity
import com.app.curahanmental.databinding.ActivityDetailJournalBinding
import com.app.curahanmental.ui.viemodel.ViewModelFactory
import com.app.curahanmental.utils.Constants.EXTRA_JOURNAL
import com.app.curahanmental.utils.DateUtils
import com.google.android.material.button.MaterialButton

class DetailJournalActivity : AppCompatActivity() {

	private val binding: ActivityDetailJournalBinding by lazy { ActivityDetailJournalBinding.inflate(layoutInflater) }
	private lateinit var detailJournalViewModel: DetailJournalViewModel

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)
		val viewModelFactory = ViewModelFactory.getInstance(this)
		detailJournalViewModel = ViewModelProvider(this, viewModelFactory)[DetailJournalViewModel::class.java]

		val extras = intent.extras
		if (extras != null) {
			val journalId = extras.getInt(JOURNAL)

			detailJournalViewModel.setSelectedJournal(journalId)
			detailJournalViewModel.journalDetail.observe(this, { journalEntity ->
				populateJournal(journalEntity)
			})
		}
	}

	private fun populateJournal(journalEntity: JournalEntity) {
		binding.apply {
			loadActionBar(journalEntity)

			val stressLevel = journalEntity.stressLevel
			if (stressLevel <= 49) {
				stressIllustration.setImageResource(R.drawable.pokemon)
				containerBg.background = AppCompatResources.getDrawable(this@DetailJournalActivity, R.drawable.greenish_gradient)
			} else if (stressLevel == 50 || stressLevel <= 74) {
				stressIllustration.setImageResource(R.drawable.godzilla)
				containerBg.background = AppCompatResources.getDrawable(this@DetailJournalActivity, R.drawable.yellowish_gradient)
			} else {
				stressIllustration.setImageResource(R.drawable.joker)
				containerBg.background = AppCompatResources.getDrawable(this@DetailJournalActivity, R.drawable.redish_gradient)
			}

			detailJournalDate.text = DateUtils.convertMillisToString(journalEntity.date)
			detailStressPercentage.text = getString(R.string.stress_percentage, journalEntity.stressLevel)
			eventOptionText.text = journalEntity.event
			eventDetailText.setText(journalEntity.eventDetail)
			manageEventOptionText.text = journalEntity.manageEvent
			manageEventDetailText.setText(journalEntity.idealEventScenario)
			reasonOptionText.text = journalEntity.reason
			reasonDetailText.setText(journalEntity.reasonDetail)
		}
	}

	private fun loadActionBar(journalEntity: JournalEntity) {
		findViewById<MaterialButton>(R.id.back_button).setOnClickListener {
			super.onBackPressed()
		}
		findViewById<MaterialButton>(R.id.delete_button).setOnClickListener {
			Toast.makeText(this, "Delete Journal?", Toast.LENGTH_SHORT).show()
		}
		findViewById<TextView>(R.id.journal_title).text = getString(R.string.journal, DateUtils.convertMillisToString(journalEntity.date))
	}

	companion object {
		const val JOURNAL = EXTRA_JOURNAL
	}
}