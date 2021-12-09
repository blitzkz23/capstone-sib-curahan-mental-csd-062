package com.app.curahanmental.ui.journal.add.component

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.app.curahanmental.R
import com.app.curahanmental.databinding.FragmentAddJournalEventBinding
import com.google.android.material.button.MaterialButton


class AddJournalEventFragment : Fragment() {

	private var _binding: FragmentAddJournalEventBinding? = null
	private val binding get() = _binding!!
	private lateinit var event: String
	private var stressLevel = 0
	private lateinit var eventDetail: String

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		// Inflate the layout for this fragment
		_binding = FragmentAddJournalEventBinding.inflate(inflater, container, false)
		val root: View = binding.root

		// Get argument from previous fragment
		stressLevel = AddJournalEventFragmentArgs.fromBundle(arguments as Bundle).stressLevel

		return root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		loadActionBar()
		binding.apply {
			spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
				override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
					detailquestion.visibility = View.VISIBLE
					eventDetailColumn.visibility = View.VISIBLE
					event = spinner.selectedItem.toString()
				}

				override fun onNothingSelected(p0: AdapterView<*>?) {}
			}

			eventDetailText.addTextChangedListener(object : TextWatcher {
				override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

				override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
					if (eventDetailText.text.toString().isNotEmpty()) {
						nextButton.isClickable = true
						nextButton.isEnabled = true
					} else {
						nextButton.isClickable = false
						nextButton.isEnabled = false
					}
				}

				override fun afterTextChanged(p0: Editable?) {
					eventDetail = eventDetailText.text.toString().trim()
				}
			})

			nextButton.setOnClickListener { view ->
				navigate(view)
			}
		}

	}

	private fun navigate(view: View) {
		val toManageEvent =
			AddJournalEventFragmentDirections.actionAddJournalEventFragmentToAddJournalManageEventFragment()
		toManageEvent.stressLevel = stressLevel
		toManageEvent.event = event
		toManageEvent.eventDetail = eventDetail
		view.findNavController().navigate(toManageEvent)
	}

	private fun loadActionBar() {
		view?.findViewById<TextView>(R.id.journal_title)?.text = getString(R.string.event)
		view?.findViewById<MaterialButton>(R.id.back_button_fragment)?.setOnClickListener {
			val toPreviousFragment = AddJournalEventFragmentDirections.actionAddJournalEventFragmentToAddJournalStressLevel()
			view?.findNavController()?.navigate(toPreviousFragment)
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}