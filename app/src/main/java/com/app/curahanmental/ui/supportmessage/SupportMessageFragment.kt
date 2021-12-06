package com.app.curahanmental.ui.supportmessage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.app.curahanmental.databinding.FragmentSupportMessageBinding
import com.app.curahanmental.ui.notifications.SupportMessageViewModel

class SupportMessageFragment : Fragment() {

	private lateinit var supportMessageViewModel: SupportMessageViewModel
	private var _binding: FragmentSupportMessageBinding? = null

	// This property is only valid between onCreateView and
	// onDestroyView.
	private val binding get() = _binding!!

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		supportMessageViewModel =
			ViewModelProvider(this)[SupportMessageViewModel::class.java]

		_binding = FragmentSupportMessageBinding.inflate(inflater, container, false)

		return binding.root
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}