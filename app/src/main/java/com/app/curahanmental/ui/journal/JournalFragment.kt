package com.app.curahanmental.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.app.curahanmental.R
import com.app.curahanmental.databinding.FragmentJournalBinding

class JournalFragment : Fragment() {

	private lateinit var journalViewModel: JournalViewModel
	private var _binding: FragmentJournalBinding? = null

	// This property is only valid between onCreateView and
	// onDestroyView.
	private val binding get() = _binding!!

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		journalViewModel =
			ViewModelProvider(this).get(JournalViewModel::class.java)

		_binding = FragmentJournalBinding.inflate(inflater, container, false)
		val root: View = binding.root

		binding.fabAdd.setOnClickListener { view ->
			view.findNavController().navigate(R.id.action_navigation_journal_to_addJournalActivity)
		}
		return root

	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}