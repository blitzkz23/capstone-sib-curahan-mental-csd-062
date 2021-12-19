package com.app.curahanmental.ui.kindlinessmessage.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.app.curahanmental.R
import com.app.curahanmental.data.source.remote.entity.KindlinessMessageEntity
import com.app.curahanmental.databinding.FragmentAddMessageDialogBinding
import com.app.curahanmental.ui.kindlinessmessage.KindlinessMessageViewModel
import java.util.*

class AddMessageDialogFragment : DialogFragment() {

	private var _binding: FragmentAddMessageDialogBinding? = null
	private val binding get() = _binding!!
	private lateinit var supportMessageViewModel: KindlinessMessageViewModel

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setStyle(STYLE_NO_FRAME, android.R.style.Theme_DeviceDefault_Dialog_MinWidth)
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		// Inflate the layout for this fragment
		_binding = FragmentAddMessageDialogBinding.inflate(layoutInflater, container, false)

		supportMessageViewModel =
			ViewModelProvider(this)[KindlinessMessageViewModel::class.java]

		supportMessageViewModel.result.observe(viewLifecycleOwner) {
			if (it == null) {
				Toast.makeText(
					requireContext(),
					getString(R.string.success_message),
					Toast.LENGTH_SHORT
				).show()
			} else {
				Toast.makeText(
					requireContext(),
					getString(R.string.failure_message),
					Toast.LENGTH_SHORT
				).show()
			}
			dismiss()
		}

		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.apply {
			cancelButton.setOnClickListener {
				dismiss()
			}
			addButton.setOnClickListener {
				saveMessage()
			}
		}
	}

	private fun saveMessage() {
		val messageText = binding.kindlinessText.text.toString().trim()
		val currentTime = Calendar.getInstance().timeInMillis

		val message = KindlinessMessageEntity(
			messages = messageText,
			time = currentTime
		)

		if (messageText.isEmpty()) {
			binding.kindlinessColumn.requestFocus()
			binding.kindlinessText.error = getString(R.string.douzo_meseji_haire_kudasai)
		} else {
			supportMessageViewModel.addMessage(message)
		}
	}

}