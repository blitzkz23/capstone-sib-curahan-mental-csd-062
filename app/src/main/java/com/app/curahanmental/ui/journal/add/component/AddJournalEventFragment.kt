package com.app.curahanmental.ui.journal.add.component

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.curahanmental.R


class AddJournalEventFragment : Fragment() {


	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_add_journal_event, container, false)
	}

	companion object {

	}
}