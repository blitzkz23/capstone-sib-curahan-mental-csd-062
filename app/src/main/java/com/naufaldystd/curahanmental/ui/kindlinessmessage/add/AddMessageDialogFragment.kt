package com.naufaldystd.curahanmental.ui.kindlinessmessage.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.naufaldystd.curahanmental.R
import com.naufaldystd.curahanmental.data.source.remote.entity.KindlinessMessageEntity
import com.naufaldystd.curahanmental.databinding.FragmentAddMessageDialogBinding
import com.naufaldystd.curahanmental.ui.kindlinessmessage.KindlinessMessageViewModel
import java.util.*

class AddMessageDialogFragment : DialogFragment() {

	private var _binding: FragmentAddMessageDialogBinding? = null
	private val binding get() = _binding!!
	private lateinit var kindlinessMessageViewModel: KindlinessMessageViewModel

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

		kindlinessMessageViewModel =
			ViewModelProvider(this)[KindlinessMessageViewModel::class.java]

		kindlinessMessageViewModel.result.observe(viewLifecycleOwner) {
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
		kindlinessMessageViewModel.getCurrentUserDisplayName()

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
		lateinit var poster: String

		kindlinessMessageViewModel.currentData.observe(viewLifecycleOwner) {
			poster = "${it?.firstName} ${it?.lastName}"
		}

		val message = KindlinessMessageEntity(
			messages = messageText,
			time = currentTime,
			poster = poster
		)

		if (messageText.isEmpty()) {
			binding.kindlinessColumn.requestFocus()
			binding.kindlinessText.error = getString(R.string.enter_message)
		} else {
			kindlinessMessageViewModel.addMessage(message)
		}

	}

}