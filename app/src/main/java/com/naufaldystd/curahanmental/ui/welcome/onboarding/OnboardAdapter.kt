package com.naufaldystd.curahanmental.ui.welcome.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.naufaldystd.curahanmental.databinding.SlidesOnboardBinding
import com.bumptech.glide.Glide

class OnboardAdapter(private var onboardModel: List<OnboardModel>): RecyclerView.Adapter<OnboardAdapter.OnboardViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OnboardAdapter.OnboardViewHolder {
       return OnboardViewHolder(SlidesOnboardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: OnboardAdapter.OnboardViewHolder, position: Int) {
        holder.bind(onboardModel[position])
    }

    override fun getItemCount(): Int = onboardModel.size

    inner class OnboardViewHolder(private var binding: SlidesOnboardBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(onboardModel: OnboardModel){
            binding.apply {
                Glide.with(itemView.context)
                    .load(onboardModel.image)
                    .into(onboardImage)

                onboardTextHeader.text = onboardModel.header
                onboardTextOverview.text = onboardModel.overview
            }
        }
    }
}