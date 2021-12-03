package com.app.curahanmental.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.app.curahanmental.R
import com.app.curahanmental.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

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
		homeViewModel.getCurrentUserDisplayName()
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		homeViewModel.currentData.observe(viewLifecycleOwner){
			binding.homeGreetings1.text = StringBuilder("Selamat datang, $it")
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}