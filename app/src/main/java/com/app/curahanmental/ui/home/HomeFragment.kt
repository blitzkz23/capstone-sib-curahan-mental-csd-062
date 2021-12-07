package com.app.curahanmental.ui.home

import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.app.curahanmental.R
import com.app.curahanmental.databinding.FragmentHomeBinding
import com.app.curahanmental.ui.settings.SettingsActivity

class HomeFragment : Fragment() {

	private lateinit var homeViewModel: HomeViewModel
	private var _binding: FragmentHomeBinding? = null

	private val binding get() = _binding!!

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		homeViewModel =
			ViewModelProvider(this)[HomeViewModel::class.java]

		_binding = FragmentHomeBinding.inflate(inflater, container, false)
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
		getView()?.findViewById<ImageView>(R.id.home_btn_settings)?.setOnClickListener {
			startActivity(Intent(activity, SettingsActivity::class.java))
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}