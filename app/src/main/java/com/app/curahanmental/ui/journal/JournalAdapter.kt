package com.app.curahanmental.ui.journal

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.curahanmental.R
import com.app.curahanmental.data.source.local.entity.JournalEntity
import com.app.curahanmental.databinding.ItemJournalListBinding
import com.app.curahanmental.ui.journal.detail.DetailJournalActivity
import com.app.curahanmental.utils.DateUtils


class JournalAdapter :
	PagingDataAdapter<JournalEntity, JournalAdapter.JournalViewHolder>(DIFF_CALLBACK) {
	private lateinit var context: Context

	override fun onBindViewHolder(holder: JournalViewHolder, position: Int) {
		context = holder.itemView.context
		val journal = getItem(position) as JournalEntity
		holder.bind(journal)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JournalViewHolder {
		val binding = ItemJournalListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return JournalViewHolder(binding)
	}

	inner class JournalViewHolder(private val binding: ItemJournalListBinding) : RecyclerView.ViewHolder(binding.root) {
		private lateinit var journal: JournalEntity

		fun bind(journal: JournalEntity) {
			this.journal = journal

			with(binding){
				journalDate.text = DateUtils.convertMillisToString(journal.date)
				stressEventPlaceholder.text = journal.event
				stressLevel.text = context.getString(R.string.stress_percent, journal.stressLevel)

				stressBar.progress = journal.stressLevel
				if (journal.stressLevel <= 49) {
					stressBar.progressTintList = ColorStateList.valueOf(Color.rgb(42, 96, 73))
				} else if (journal.stressLevel == 50 || journal.stressLevel <= 74) {
					stressBar.progressTintList = ColorStateList.valueOf(Color.rgb(255, 236, 62))
				} else {
					stressBar.progressTintList = ColorStateList.valueOf(Color.rgb(255, 20, 35))
				}

				itemView.setOnClickListener {
					val intent = Intent(itemView.context, DetailJournalActivity::class.java)
					intent.putExtra(DetailJournalActivity.JOURNAL, journal.id)
					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
					itemView.context.startActivity(intent)
				}
			}
		}

		fun getJournal(): JournalEntity = journal
	}

	companion object {

		private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<JournalEntity>() {
			override fun areItemsTheSame(oldItem: JournalEntity, newItem: JournalEntity): Boolean {
				return oldItem.id == newItem.id
			}

			override fun areContentsTheSame(oldItem: JournalEntity, newItem: JournalEntity): Boolean {
				return oldItem == newItem
			}
		}

	}
}