package com.app.curahanmental.ui.journal.add.component

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.app.curahanmental.R
import com.app.curahanmental.databinding.FragmentAddJournalStressLevelBinding
import com.google.android.material.button.MaterialButton

class AddJournalStressLevelFragment : Fragment() {

	private var _binding: FragmentAddJournalStressLevelBinding? = null
	private val binding get() = _binding!!

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		// Inflate the layout for this fragment
		_binding = FragmentAddJournalStressLevelBinding.inflate(inflater, container, false)
		val root: View = binding.root
		loadActionBar()

		binding.let {
			it.slider.addOnChangeListener { slider, value, fromUser ->
				val percentageValue = it.slider.value.toInt()
				it.percentage.setText(getString(R.string.stress_percentage, percentageValue))
			}

		}

		return root

	}

	private fun loadActionBar() {
		view?.findViewById<TextView>(R.id.journal_title)?.text = getString(R.string.stress_level)
		view?.findViewById<MaterialButton>(R.id.back_button_fragment)?.setOnClickListener {
			super.onDetach()
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}

}