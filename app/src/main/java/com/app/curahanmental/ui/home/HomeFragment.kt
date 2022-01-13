package com.app.curahanmental.ui.home

import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.curahanmental.R
import com.app.curahanmental.data.source.Resource
import com.app.curahanmental.databinding.FragmentHomeBinding
import com.app.curahanmental.ui.home.articles.ArticleActivity
import com.app.curahanmental.ui.home.articles.ArticleAdapter
import com.app.curahanmental.ui.home.banner.BannerAdapter
import com.app.curahanmental.ui.home.banner.BannerData
import com.app.curahanmental.ui.home.banner.BannerModel
import com.app.curahanmental.ui.home.tips.TipsActivity
import com.app.curahanmental.ui.settings.SettingsActivity
import com.app.curahanmental.ui.viewmodel.ViewModelFactory
import com.google.android.material.button.MaterialButton

class HomeFragment : Fragment() {

	private lateinit var homeViewModel: HomeViewModel
	private var _binding: FragmentHomeBinding? = null
	private val articleAdapter = ArticleAdapter()
	private var listBanner: ArrayList<BannerModel> = arrayListOf()

	private val binding get() = _binding!!

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {

		_binding = FragmentHomeBinding.inflate(inflater, container, false)
		val viewModelFactory = requireContext().let { ViewModelFactory.getInstance(it) }
		homeViewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
		initArticleContent()

		listBanner.addAll(BannerData.bannerList)
		initAnimation()
		return binding.root
	}

	private fun initAnimation() {
		val fromTop = AnimationUtils.loadAnimation(context, R.anim.anim_from_top)
		val fromBottom = AnimationUtils.loadAnimation(context, R.anim.anim_from_bottom)
		val fromLeft = AnimationUtils.loadAnimation(context, R.anim.anim_from_left)
		val fromRight = AnimationUtils.loadAnimation(context, R.anim.anim_from_right)

		binding.homeGreetings1.animation = fromTop
		binding.homeGreetings2.animation = fromTop
		binding.rvBanner.animation = fromRight
		binding.homeCardTips.animation = fromLeft
		binding.homeTipsTitle.animation = fromLeft
		binding.homeArticleTitle.animation = fromBottom
		binding.rvHomeArticle.animation = fromBottom
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		homeViewModel.getCurrentUserDisplayName()
		val green = context?.let { getColor(it, R.color.curahan_light_green) }

		homeViewModel.currentData.observe(viewLifecycleOwner, { firstName ->
			val displayName = SpannableStringBuilder(firstName)
			if (firstName != null) {
				displayName.setSpan(
					green?.let { it1 -> ForegroundColorSpan(it1) },
					0,
					firstName.length,
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
				)
			}
			binding.homeGreetings1.text = TextUtils.concat("Selamat datang, ", displayName)
		})
		getView()?.findViewById<MaterialButton>(R.id.home_btn_settings)?.setOnClickListener {
			startActivity(Intent(activity, SettingsActivity::class.java))
		}
		binding.homeTipsButton.setOnClickListener {
			startActivity(Intent(activity, TipsActivity::class.java))
		}

		showRecycleViewBanner()
		showRecycleViewArticle()

	}

	private fun showRecycleViewBanner() {
		with(binding) {
			rvBanner.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
			val bannerAdapter = BannerAdapter(listBanner)
			rvBanner.adapter = bannerAdapter
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}

	private fun initArticleContent() {
		homeViewModel.articles.observe(viewLifecycleOwner, { res ->
			if (res != null) {
				when (res) {
					is Resource.Success -> {
						Log.d("GET_DATA", "Success get data")
						articleAdapter.setArticleData(res.data)
					}
					is Resource.Error -> Log.d("GET_DATA", "Unable to get data")
					else -> Log.d("GET_DATA", "Something wrong!")
				}

			}
		})
	}

	private fun showRecycleViewArticle() {
		with(binding) {
			rvHomeArticle.layoutManager = LinearLayoutManager(context)
			rvHomeArticle.setHasFixedSize(true)
			rvHomeArticle.adapter = articleAdapter
			articleAdapter.onItemClick = { selectedData ->
				val intent = Intent(activity, ArticleActivity::class.java)
				intent.putExtra(ArticleActivity.ARTICLE, selectedData)
				startActivity(intent)
			}
		}
	}
}