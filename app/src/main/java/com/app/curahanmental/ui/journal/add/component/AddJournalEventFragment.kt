package com.app.curahanmental.ui.journal.add.component

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.curahanmental.databinding.FragmentAddJournalEventBinding


class AddJournalEventFragment : Fragment() {

	private var _binding: FragmentAddJournalEventBinding? = null
	private val binding get() = _binding!!

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		// Inflate the layout for this fragment
		_binding = FragmentAddJournalEventBinding.inflate(inflater, container, false)
		val root: View = binding.root

		return root
	}

	companion object {

	}
}