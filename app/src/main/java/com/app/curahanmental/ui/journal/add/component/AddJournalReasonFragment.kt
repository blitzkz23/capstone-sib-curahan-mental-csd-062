package com.app.curahanmental.ui.journal.add.component

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.app.curahanmental.R
import com.app.curahanmental.data.source.local.entity.JournalEntity
import com.app.curahanmental.databinding.FragmentAddJournalReasonBinding
import com.app.curahanmental.ui.journal.add.AddJournalViewModel
import com.app.curahanmental.ui.viemodel.ViewModelFactory
import com.google.android.material.button.MaterialButton
import java.util.*

class AddJournalReasonFragment : Fragment() {

	private var _binding: FragmentAddJournalReasonBinding? = null
	private val binding get() = _binding!!
	private var stressLevel = 0
	private lateinit var event: String
	private lateinit var eventDetail: String
	private lateinit var manageEvent: String
	private lateinit var idealEventScenario: String
	private lateinit var reason: String
	private lateinit var reasonDetail: String
	private lateinit var addJournalViewModel: AddJournalViewModel
	private var dateNow: Long = 0

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		// Inflate the layout for this fragment
		_binding = FragmentAddJournalReasonBinding.inflate(inflater, container, false)
		val root: View = binding.root

		val viewModelFactory = requireContext().let { ViewModelFactory.getInstance(it) }
		addJournalViewModel =
			ViewModelProvider(this, viewModelFactory)[AddJournalViewModel::class.java]

		// Get argument from previous fragment
		stressLevel =
			AddJournalReasonFragmentArgs.fromBundle(arguments as Bundle).stressLevel
		event = AddJournalReasonFragmentArgs.fromBundle(arguments as Bundle).event
		eventDetail =
			AddJournalReasonFragmentArgs.fromBundle(arguments as Bundle).eventDetail
		manageEvent = AddJournalReasonFragmentArgs.fromBundle(arguments as Bundle).manageEvent
		idealEventScenario =
			AddJournalReasonFragmentArgs.fromBundle(arguments as Bundle).manageEventDetail
		getTodayDate()

		return root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
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
				insertToDb(
					stressLevel,
					event,
					eventDetail,
					manageEvent,
					idealEventScenario,
					reason,
					reasonDetail,
					dateNow
				)
				Toast.makeText(activity, getString(R.string.journal_added), Toast.LENGTH_SHORT)
					.show()
				activity?.finish()
			}

		}
	}

	private fun insertToDb(
		stressLevel: Int,
		event: String,
		eventDetail: String,
		manageEvent: String,
		idealEventScenario: String,
		reason: String,
		reasonDetail: String,
		date: Long
	) {
		val journal = JournalEntity(
			stressLevel = stressLevel,
			event = event,
			eventDetail = eventDetail,
			manageEvent = manageEvent,
			idealEventScenario = idealEventScenario,
			reason = reason,
			reasonDetail = reasonDetail,
			date = date
		)
		addJournalViewModel.insertJournal(journal)
	}

	private fun getTodayDate() {
		val calendar = Calendar.getInstance()
		dateNow = calendar.timeInMillis
	}

	private fun loadActionBar() {
		view?.findViewById<TextView>(R.id.journal_title)?.text = getString(R.string.reason)
		view?.findViewById<MaterialButton>(R.id.back_button_fragment)?.setOnClickListener {
			val toPreviousFragment = AddJournalReasonFragmentDirections.actionAddJournalReasonFragmentToAddJournalManageEventFragment()
			view?.findNavController()?.navigate(toPreviousFragment)
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}

}