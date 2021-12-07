package com.app.curahanmental.ui.journal.add.component

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
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

		val stressLevel = AddJournalEventFragmentArgs.fromBundle(arguments as Bundle).stressLevel
		binding.apply {
			val event = spinner.selectedItem.toString()
			val eventDetail = eventDetailText.text.toString()
			spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
				override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
					detailquestion.visibility = View.VISIBLE
					eventDetailColumn.visibility = View.VISIBLE
				}

				override fun onNothingSelected(p0: AdapterView<*>?) {

				}
			}
			eventDetailText.addTextChangedListener(object : TextWatcher {
				override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

				}

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
				}
			})

		}

		return root
	}

	companion object {

	}
}