package com.app.curahanmental.ui.kindlinessmessage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.app.curahanmental.R
import com.app.curahanmental.databinding.FragmentKindlinessMessageBinding

class KindlinessMessageFragment : Fragment() {

	private lateinit var supportMessageViewModel: KindlinessMessageViewModel
	private var _binding: FragmentKindlinessMessageBinding? = null

	// This property is only valid between onCreateView and
	// onDestroyView.
	private val binding get() = _binding!!

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		supportMessageViewModel =
			ViewModelProvider(this)[KindlinessMessageViewModel::class.java]

		_binding = FragmentKindlinessMessageBinding.inflate(inflater, container, false)

		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.fabAdd.setOnClickListener { view ->
			view.findNavController().navigate(R.id.action_navigation_support_message_to_addKindnessMessageActivity)
		}
		supportMessageViewModel.getALlUser()
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}