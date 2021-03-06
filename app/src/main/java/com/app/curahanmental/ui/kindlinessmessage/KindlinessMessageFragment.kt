package com.app.curahanmental.ui.kindlinessmessage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.curahanmental.R
import com.app.curahanmental.databinding.FragmentKindlinessMessageBinding
import com.app.curahanmental.ui.kindlinessmessage.add.AddMessageDialogFragment

class KindlinessMessageFragment : Fragment() {

	private lateinit var supportMessageViewModel: KindlinessMessageViewModel
	private var _binding: FragmentKindlinessMessageBinding? = null
	private val messageAdapter = KindlinessMessageAdapter()

	// This property is only valid between onCreateView and
	// onDestroyView.
	private val binding get() = _binding!!

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		supportMessageViewModel =
			ViewModelProvider(this)[KindlinessMessageViewModel::class.java]

		_binding = FragmentKindlinessMessageBinding.inflate(inflater, container, false)
		showRecycleViewMessage()
		initAnimation()

		return binding.root
	}

	private fun initAnimation() {
		val fromTop = AnimationUtils.loadAnimation(context, R.anim.anim_from_top)
		val fromBottom = AnimationUtils.loadAnimation(context, R.anim.anim_from_bottom)

		binding.messageBoardTitle.animation = fromTop
		binding.fabAdd.animation = fromBottom
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.fabAdd.setOnClickListener {
			AddMessageDialogFragment().show(childFragmentManager, "")
		}
		supportMessageViewModel.message.observe(viewLifecycleOwner, {
			messageAdapter.setMessageData(it)
		})
		supportMessageViewModel.getRealtimeMessage()

	}

	private fun showRecycleViewMessage() {
		with(binding) {
			rvMessage.layoutManager = LinearLayoutManager(context)
			rvMessage.setHasFixedSize(false)
			rvMessage.adapter = messageAdapter
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}