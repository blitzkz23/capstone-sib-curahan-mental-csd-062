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
import com.app.curahanmental.R
import com.app.curahanmental.databinding.FragmentAddJournalReasonBinding
import com.google.android.material.button.MaterialButton

class AddJournalReasonFragment : Fragment() {

	private var _binding: FragmentAddJournalReasonBinding? = null
	private val binding get() = _binding!!
	private var stressLevel = 0
	private lateinit var event: String
	private lateinit var eventDetail: String
	private lateinit var manageEvent: String
	private lateinit var manageEventDetail: String
	private lateinit var reason: String
	private lateinit var reasonDetail: String

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		// Inflate the layout for this fragment
		_binding = FragmentAddJournalReasonBinding.inflate(inflater, container, false)
		val root: View = binding.root

		// Get argument from previous fragment
		stressLevel =
			AddJournalReasonFragmentArgs.fromBundle(arguments as Bundle).stressLevel
		event = AddJournalReasonFragmentArgs.fromBundle(arguments as Bundle).event
		eventDetail =
			AddJournalReasonFragmentArgs.fromBundle(arguments as Bundle).eventDetail
		manageEvent = AddJournalReasonFragmentArgs.fromBundle(arguments as Bundle).manageEvent
		manageEventDetail =
			AddJournalReasonFragmentArgs.fromBundle(arguments as Bundle).manageEventDetail

		loadActionBar()

		binding.apply {
			spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
				override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
					reason = spinner.selectedItem.toString()
				}

				override fun onNothingSelected(p0: AdapterView<*>?) {}
			}

			reasonDetailText.addTextChangedListener(object : TextWatcher {
				override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

				override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
					if (reasonDetailText.text.toString().isNotEmpty()) {
						finishButton.isClickable = true
						finishButton.isEnabled = true
					} else {
						finishButton.isClickable = false
						finishButton.isEnabled = false
					}
				}

				override fun afterTextChanged(p0: Editable?) {
					reasonDetail = reasonDetailText.text.toString().trim()
				}
			})

			finishButton.setOnClickListener {
				// TODO : nanti implement db here
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