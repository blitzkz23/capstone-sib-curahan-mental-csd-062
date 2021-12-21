package com.naufaldystd.curahanmental.ui.home.tips

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.naufaldystd.curahanmental.databinding.ItemTipsHomeBinding
import com.bumptech.glide.Glide

class TipsAdapter(private val listTips: ArrayList<TipsModel>): RecyclerView.Adapter<TipsAdapter.TipsViewHolder>() {

    var onItemClick: ((TipsModel) -> Unit) ? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipsAdapter.TipsViewHolder {
        val binding = ItemTipsHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TipsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TipsAdapter.TipsViewHolder, position: Int) {
        val tips = listTips[position]
        holder.bind(tips)
    }

    override fun getItemCount(): Int = listTips.size

    inner class TipsViewHolder(private var binding: ItemTipsHomeBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(content: TipsModel){
            binding.apply {
                tvTipsTitle.text = content.title
                Glide.with(itemView.context)
                    .load(content.image)
                    .into(imgTips)
            }
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listTips[absoluteAdapterPosition])
            }
        }
    }
}