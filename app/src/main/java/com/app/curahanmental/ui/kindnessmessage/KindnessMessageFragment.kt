package com.app.curahanmental.ui.kindnessmessage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.app.curahanmental.R
import com.app.curahanmental.databinding.FragmentKindnessMessageBinding
import com.app.curahanmental.ui.notifications.KindnessMessageViewModel

class KindnessMessageFragment : Fragment() {

	private lateinit var supportMessageViewModel: KindnessMessageViewModel
	private var _binding: FragmentKindnessMessageBinding? = null

	// This property is only valid between onCreateView and
	// onDestroyView.
	private val binding get() = _binding!!

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		supportMessageViewModel =
			ViewModelProvider(this)[KindnessMessageViewModel::class.java]

		_binding = FragmentKindnessMessageBinding.inflate(inflater, container, false)

		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.fabAdd.setOnClickListener { view ->
			view.findNavController().navigate(R.id.action_navigation_support_message_to_addKindnessMessageActivity)
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}