package com.app.curahanmental.ui.kindlinessmessage

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.app.curahanmental.R
import com.app.curahanmental.data.source.remote.entity.KindlinessMessageEntity
import com.app.curahanmental.databinding.ItemKindlinessMessageBinding
import com.app.curahanmental.utils.DateUtils

class KindlinessMessageAdapter : RecyclerView.Adapter<KindlinessMessageAdapter.ViewHolder>() {

	private var listMessage = mutableListOf<KindlinessMessageEntity>()

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val binding = ItemKindlinessMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return ViewHolder(binding)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val message = listMessage[position]
		holder.bind(message)
	}

	override fun getItemCount(): Int = listMessage.size

	inner class ViewHolder(private var binding: ItemKindlinessMessageBinding) : RecyclerView.ViewHolder(binding.root){
		fun bind(message: KindlinessMessageEntity) {
			binding.apply {
				username.text = message.poster
				userMessage.text = message.messages
				messageDate.text = message.time?.let { DateUtils.convertMillisToString(it) }
				messageTime.text = message.time?.let { DateUtils.convertTime(it) }
			}
			itemView.animation = AnimationUtils.loadAnimation(itemView.context, R.anim.recycler_anim)
		}
	}
	@SuppressLint("NotifyDataSetChanged")
	fun setMessageData(newMessage: KindlinessMessageEntity?){
		if (newMessage == null) return
		listMessage.add(newMessage)
		listMessage.reverse()
		notifyDataSetChanged()
	}
}