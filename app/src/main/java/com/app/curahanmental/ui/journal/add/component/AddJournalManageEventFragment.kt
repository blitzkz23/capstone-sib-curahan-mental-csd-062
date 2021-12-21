package com.app.curahanmental.ui.journal.add.component

import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.app.curahanmental.R
import com.app.curahanmental.databinding.FragmentAddJournalManageEventBinding


class AddJournalManageEventFragment : Fragment(), View.OnClickListener {

	private var _binding: FragmentAddJournalManageEventBinding? = null
	private val binding get() = _binding!!
	private var stressLevel = 0
	private lateinit var event: String
	private lateinit var eventDetail: String
	private lateinit var manageEvent: String
	private lateinit var idealEventScenario: String


	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		// Inflate the layout for this fragment
		_binding = FragmentAddJournalManageEventBinding.inflate(inflater, container, false)
		val root: View = binding.root

		// Get argument from previous fragment
		stressLevel =
			AddJournalManageEventFragmentArgs.fromBundle(arguments as Bundle).stressLevel
		event = AddJournalManageEventFragmentArgs.fromBundle(arguments as Bundle).event
		eventDetail =
			AddJournalManageEventFragmentArgs.fromBundle(arguments as Bundle).eventDetail

		return root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		loadActionBar()
		// Set click listener
		binding.let {
			it.thumbUpButton.setOnClickListener(this)
			it.thumbDownButton.setOnClickListener(this)
			it.questionMarkButton.setOnClickListener(this)
		}

		//Listener for text input edit
		binding.idealSolutionText.addTextChangedListener(object : TextWatcher {
			override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

			override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
				binding.apply {
					if (idealSolutionText.text.toString().isNotEmpty()) {
						nextButton.isClickable = true
						nextButton.isEnabled = true
					} else {
						nextButton.isClickable = false
						nextButton.isEnabled = false
					}
				}
			}

			override fun afterTextChanged(p0: Editable?) {
				idealEventScenario = binding.idealSolutionText.text.toString().trim()
			}

		})

		binding.nextButton.setOnClickListener { view ->
			navigate(view)
		}

	}

	private fun navigate(view: View) {
		val toReasonFragment =
			AddJournalManageEventFragmentDirections.actionAddJournalManageEventFragmentToAddJournalReasonFragment2()
		toReasonFragment.stressLevel = stressLevel
		toReasonFragment.event = event
		toReasonFragment.eventDetail = eventDetail
		toReasonFragment.manageEvent = manageEvent
		toReasonFragment.manageEventDetail = idealEventScenario
		view.findNavController().navigate(toReasonFragment)
	}

	private fun loadActionBar() {
		view?.findViewById<TextView>(R.id.journal_title)?.text = getString(R.string.manage_event)
		view?.findViewById<ImageView>(R.id.back_button_fragment)?.setOnClickListener {
			val toPreviousFragment = AddJournalManageEventFragmentDirections.actionAddJournalManageEventFragmentToAddJournalEventFragment()
			view?.findNavController()?.navigate(toPreviousFragment)
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}

	override fun onClick(p0: View) {
		val colorIntGreen = resources.getColor(R.color.curahan_dark_green)
		val colorIntGrey = resources.getColor(R.color.curahan_subtext)
		val colorStateList1 = ColorStateList.valueOf(colorIntGreen)
		val colorStateList2 = ColorStateList.valueOf(colorIntGrey)

		when (p0.id) {
			R.id.thumb_up_button -> {
				binding.manageEventText.setText(getString(R.string.good))
				binding.thumbUpButton.backgroundTintList = colorStateList1
				binding.thumbDownButton.backgroundTintList = colorStateList2
				binding.questionMarkButton.backgroundTintList = colorStateList2

				manageEvent = getString(R.string.good)
			}
			R.id.thumb_down_button -> {
				binding.manageEventText.setText(getString(R.string.bad))
				binding.thumbUpButton.backgroundTintList = colorStateList2
				binding.thumbDownButton.backgroundTintList = colorStateList1
				binding.questionMarkButton.backgroundTintList = colorStateList2

				manageEvent = getString(R.string.bad)
			}
			R.id.question_mark_button -> {
				binding.manageEventText.setText(getString(R.string.confused))
				binding.thumbUpButton.backgroundTintList = colorStateList2
				binding.thumbDownButton.backgroundTintList = colorStateList2
				binding.questionMarkButton.backgroundTintList = colorStateList1

				manageEvent = getString(R.string.confused)
			}
			else -> {
			}
		}
	}
}