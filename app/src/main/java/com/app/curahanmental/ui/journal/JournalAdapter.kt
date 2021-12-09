package com.app.curahanmental.ui.journal

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.curahanmental.R
import com.app.curahanmental.data.source.local.entity.JournalEntity
import com.app.curahanmental.databinding.ItemJournalListBinding
import com.app.curahanmental.utils.DateUtils


class JournalAdapter : PagedListAdapter<JournalEntity, JournalAdapter.JournalViewHolder>(DIFF_CALLBACK) {
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
		fun bind(journal: JournalEntity) {
			with(binding){
				journalDate.text = DateUtils.convertMillisToString(journal.date)
				stressEventPlaceholder.text = journal.event
				stressLevel.text = context.getString(R.string.stress_percent, journal.stressLevel)

				stressBar.progress = journal.stressLevel
				if (journal.stressLevel <= 50) {
					stressBar.progressTintList = ColorStateList.valueOf(Color.rgb(42, 96, 73))
				} else if (journal.stressLevel == 51 || journal.stressLevel <= 74) {
					stressBar.progressTintList = ColorStateList.valueOf(Color.rgb(255, 94, 67))
				} else {
					stressBar.progressTintList = ColorStateList.valueOf(Color.rgb(255, 20, 35))
				}
			}
		}
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