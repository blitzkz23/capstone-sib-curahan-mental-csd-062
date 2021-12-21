package com.app.curahanmental.ui.home.banner

import com.app.curahanmental.R

object BannerData {
	private var arr = arrayOf(
		BannerModel(
			R.drawable.curahan_banner,
			"Curahkan mentalmu",
			R.id.navigation_journal,
			R.color.curahan_dark_green,
			"Jernihkan Pikiranmu"
		),
		BannerModel(
			R.drawable.curahan_banner2,
			"Temukan pesan dukungan",
			R.id.navigation_support_message,
			R.color.curahan_soft_brown,
			"Lihat Sekarang"
		)
	)

	val bannerList: ArrayList<BannerModel> get() {
		val list = arrayListOf<BannerModel>()
		for (data in arr) {
			val banner = BannerModel()
			banner.bannerImage = data.bannerImage
			banner.tagline = data.tagline
			banner.ctaLink = data.ctaLink
			banner.btnTxt = data.btnTxt
			list.add(banner)
		}
		return list
	}
}