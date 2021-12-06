package com.app.curahanmental.ui.home

import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.app.curahanmental.R
import com.app.curahanmental.databinding.FragmentHomeBinding
import com.app.curahanmental.ui.auth.login.LoginActivity
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {

	private lateinit var homeViewModel: HomeViewModel
	private var _binding: FragmentHomeBinding? = null

	// This property is only valid between onCreateView and
	// onDestroyView.
	private val binding get() = _binding!!

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		homeViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[HomeViewModel::class.java]

		_binding = FragmentHomeBinding.inflate(inflater, container, false)
		val root: View = binding.root
		val green = context?.let { getColor(it, R.color.curahan_dark_green) }

		homeViewModel.getUser().observe(viewLifecycleOwner, {
			val username = it.firstName
			val spannableName = SpannableStringBuilder(username)
			if (username != null) {
				spannableName.setSpan(
					green?.let { it1 -> ForegroundColorSpan(it1) },
					0,
					username.length,
					Spannable.SPAN_EXCLUSIVE_INCLUSIVE
				)
			}
			binding.homeGreetings1.text = getString(R.string.title_home_greetings, spannableName)
		})

		view?.findViewById<MaterialButton>(R.id.logout_button)?.setOnClickListener {
			Firebase.auth.signOut()
			Toast.makeText(activity, "Kamu telah logout", Toast.LENGTH_SHORT).show()
			startActivity(Intent(activity, LoginActivity::class.java).also {
				Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
			})
		}

		return root
	}


	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}