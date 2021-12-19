package com.app.curahanmental.ui.kindlinessmessage.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.app.curahanmental.databinding.FragmentAddMessageDialogBinding

class AddMessageDialogFragment : DialogFragment() {

	private var _binding: FragmentAddMessageDialogBinding? = null
	private val binding get() = _binding!!

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setStyle(STYLE_NORMAL, android.R.style.Theme_DeviceDefault_Dialog_MinWidth)
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		_binding = FragmentAddMessageDialogBinding.inflate(layoutInflater, container, false)



		return binding.root
	}

}