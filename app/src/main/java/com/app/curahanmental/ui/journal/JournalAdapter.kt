package com.app.curahanmental.ui.journal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.curahanmental.R
import com.app.curahanmental.data.source.local.entity.JournalEntity

class JournalAdapter : PagedListAdapter<JournalEntity, JournalAdapter.JournalViewHolder>(DIFF_CALLBACK) {
	override fun onBindViewHolder(holder: JournalViewHolder, position: Int) {
		val journal = getItem(position) as JournalEntity
		holder.bind(journal)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JournalViewHolder {
		return JournalViewHolder(
			LayoutInflater.from(parent.context).inflate(R.layout.item_journal_list, parent, false)
		)
	}

	inner class JournalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		fun bind(journal: JournalEntity) {

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