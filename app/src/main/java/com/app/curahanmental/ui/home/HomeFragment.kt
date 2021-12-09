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
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.curahanmental.R
import com.app.curahanmental.databinding.FragmentHomeBinding
import com.app.curahanmental.ui.home.articles.ArticleAdapter
import com.app.curahanmental.ui.home.tips.TipsActivity
import com.app.curahanmental.ui.settings.SettingsActivity
import com.app.curahanmental.ui.viemodel.ViewModelFactory
import com.app.curahanmental.utils.StatusResponse
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

	private lateinit var homeViewModel: HomeViewModel
	private var _binding: FragmentHomeBinding? = null
	private val articleAdapter = ArticleAdapter()

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
		return binding.root
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
		showRecycleViewArticle()

	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}

	private fun initArticleContent() {
		lifecycleScope.launch {
			homeViewModel.getArticles().observe(viewLifecycleOwner, { res ->
				if (res != null) {
					when (res.status) {
						StatusResponse.SUCCESS -> {
							Log.d("GET_DATA", "Success get data")
							articleAdapter.setArticleData(res.body.listOfArticles)
						}
						StatusResponse.ERROR -> Log.d("GET_DATA", "Unable to get data")
						else -> Log.d("GET_DATA", "Something wrong!")
					}

				}
			})
		}
	}

	private fun showRecycleViewArticle() {
		with(binding) {
			rvHomeArticle.layoutManager = LinearLayoutManager(context)
			rvHomeArticle.setHasFixedSize(true)
			rvHomeArticle.adapter = articleAdapter
		}
	}
}