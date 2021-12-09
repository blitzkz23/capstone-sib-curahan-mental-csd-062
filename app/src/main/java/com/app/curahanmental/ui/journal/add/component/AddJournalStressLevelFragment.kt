package com.app.curahanmental.ui.journal.add.component

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.app.curahanmental.R
import com.app.curahanmental.databinding.FragmentAddJournalStressLevelBinding
import com.google.android.material.button.MaterialButton

class AddJournalStressLevelFragment : Fragment() {

	private var _binding: FragmentAddJournalStressLevelBinding? = null
	private val binding get() = _binding!!
	var percentageArg = 0

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		// Inflate the layout for this fragment
		_binding = FragmentAddJournalStressLevelBinding.inflate(inflater, container, false)
		val root: View = binding.root


		return root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		loadActionBar()
		binding.apply {
			slider.addOnChangeListener { slider, value, fromUser ->
				val percentageValue = slider.value.toInt()
				percentage.setText(getString(R.string.stress_percentage, percentageValue))
				if (percentage.text.toString().isNotEmpty()) {
					nextButton.isEnabled = true
					nextButton.isCheckable = true
				}
				percentageArg = percentageValue
			}

			nextButton.setOnClickListener { view ->
				navigate(view)
			}
		}
	}

	private fun navigate(view: View) {
		val toEventFragment =
			AddJournalStressLevelFragmentDirections.actionAddJournalStressLevelToAddJournalEventFragment()
		toEventFragment.stressLevel = percentageArg
		view.findNavController().navigate(toEventFragment)
	}

	private fun loadActionBar() {
		view?.findViewById<TextView>(R.id.journal_title)?.text = getString(R.string.stress_level)
		view?.findViewById<MaterialButton>(R.id.back_button_fragment)?.setOnClickListener {
			activity?.finish()
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}

}