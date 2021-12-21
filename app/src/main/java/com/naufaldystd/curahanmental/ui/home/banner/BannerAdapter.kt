package com.naufaldystd.curahanmental.ui.home.banner

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.naufaldystd.curahanmental.databinding.ItemHomeBannerBinding

class BannerAdapter(private val listBanner: ArrayList<BannerModel>) :
	RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
		val binding =
			ItemHomeBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return BannerViewHolder(binding)
	}

	override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
		val banner = listBanner[position]
		holder.bind(banner)
	}

	override fun getItemCount(): Int = listBanner.size

	inner class BannerViewHolder(private var binding: ItemHomeBannerBinding) :
		RecyclerView.ViewHolder(binding.root) {
		fun bind(banner: BannerModel) {
			binding.apply {
				Glide.with(itemView.context)
					.load(banner.bannerImage)
					.into(homeBannerImage)
				homeBannerText.text = banner.tagline
				homeBtnCta.setOnClickListener { view ->
					banner.ctaLink?.let { view.findNavController().navigate(it) }
				}
				banner.color?.let { container.setBackgroundColor(it) }
				homeBtnCta.text = banner.btnTxt
			}
		}
	}
}