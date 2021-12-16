package com.app.curahanmental.ui.journal.detail

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
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

	private val binding: ActivityDetailJournalBinding by lazy {
		ActivityDetailJournalBinding.inflate(
			layoutInflater
		)
	}
	private lateinit var detailJournalViewModel: DetailJournalViewModel
	private var journalId: Int = 0

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)
		val viewModelFactory = ViewModelFactory.getInstance(this)
		detailJournalViewModel =
			ViewModelProvider(this, viewModelFactory)[DetailJournalViewModel::class.java]

		journalId = intent.getIntExtra(JOURNAL, 0)

		detailJournalViewModel.setSelectedJournal(journalId)
		detailJournalViewModel.journalDetail.observe(this, { journalEntity ->
			populateJournal(journalEntity)
		})
	}

	private fun populateJournal(journalEntity: JournalEntity?) {
		binding.apply {
			loadActionBar(journalEntity)

			val stressLevel = journalEntity?.stressLevel
			if (stressLevel != null) {
				if (stressLevel <= 49) {
					stressIllustration.setImageResource(R.drawable.pokemon)
					containerBg.background = AppCompatResources.getDrawable(
						this@DetailJournalActivity,
						R.drawable.greenish_gradient
					)
				} else if (stressLevel == 50 || stressLevel <= 74) {
					stressIllustration.setImageResource(R.drawable.godzilla)
					containerBg.background = AppCompatResources.getDrawable(
						this@DetailJournalActivity,
						R.drawable.yellowish_gradient
					)
				} else {
					stressIllustration.setImageResource(R.drawable.joker)
					containerBg.background = AppCompatResources.getDrawable(
						this@DetailJournalActivity,
						R.drawable.redish_gradient
					)
				}
			}

			journalEntity?.apply {
				detailJournalDate.text = DateUtils.convertMillisToString(journalEntity.date)
				detailStressPercentage.text =
					getString(R.string.stress_percentage, journalEntity.stressLevel)
				eventOptionText.text = journalEntity.event
				eventDetailText.setText(journalEntity.eventDetail)
				manageEventOptionText.text = journalEntity.manageEvent
				manageEventDetailText.setText(journalEntity.idealEventScenario)
				reasonOptionText.text = journalEntity.reason
				reasonDetailText.setText(journalEntity.reasonDetail)
			}

		}
	}

	private fun loadActionBar(journalEntity: JournalEntity?) {
		findViewById<MaterialButton>(R.id.back_button).setOnClickListener {
			super.onBackPressed()
		}
		findViewById<MaterialButton>(R.id.edit_button).setOnClickListener {
			val intent = Intent(this, EditJournalActivity::class.java)
			intent.putExtra(EditJournalActivity.JOURNAL, journalId)
			startActivity(intent)
		}
		findViewById<MaterialButton>(R.id.delete_button).setOnClickListener {
			AlertDialog.Builder(this).apply {
				setMessage(getString(R.string.del_journal))
				setNegativeButton(getString(R.string.no), null)
				setPositiveButton(getString(R.string.yes)) { _, _ ->
					detailJournalViewModel.deleteJournal()
					finish()
				}
				show()
			}
		}
		findViewById<TextView>(R.id.journal_title).text =
			getString(R.string.journal,
				journalEntity?.let { DateUtils.convertMillisToString(it.date) })
	}

	companion object {
		const val JOURNAL = EXTRA_JOURNAL
	}
}