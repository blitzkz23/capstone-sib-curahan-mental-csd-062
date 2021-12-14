package com.app.curahanmental.ui.journal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.paging.PagedList
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.curahanmental.R
import com.app.curahanmental.data.source.local.entity.JournalEntity
import com.app.curahanmental.databinding.FragmentJournalBinding
import com.app.curahanmental.ui.viemodel.ViewModelFactory
import com.app.curahanmental.utils.JournalsSortType
import com.google.android.material.button.MaterialButton

class JournalFragment : Fragment() {

	private lateinit var journalViewModel: JournalViewModel
	private var _binding: FragmentJournalBinding? = null

	// This property is only valid between onCreateView and
	// onDestroyView.
	private val binding get() = _binding!!

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentJournalBinding.inflate(inflater, container, false)
		val root: View = binding.root
		val viewModelFactory = requireContext().let { ViewModelFactory.getInstance(it) }
		journalViewModel = ViewModelProvider(this, viewModelFactory)[JournalViewModel::class.java]


		return root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.fabAdd.setOnClickListener { view ->
			view.findNavController().navigate(R.id.action_navigation_journal_to_addJournalActivity)
		}
		getView()?.findViewById<MaterialButton>(R.id.sort_button)?.setOnClickListener { view ->
			showSortingPopUpMenu(view)
		}
		journalViewModel.journals.observe(viewLifecycleOwner, Observer(this::showJournalRecyclerView))
	}

	private fun showSortingPopUpMenu(view: View) {
		PopupMenu(activity, view).run {
			menuInflater.inflate(R.menu.sort_journal_menu, menu)

			setOnMenuItemClickListener {
				journalViewModel.sort(
					when (it.itemId) {
						R.id.bystress_level -> JournalsSortType.STRESS_LEVELS
						R.id.bydate -> JournalsSortType.DATE
						else -> JournalsSortType.ALL_JOURNALS
					}
				)
				true
			}
			show()
		}
	}

	private fun showJournalRecyclerView(journal: PagingData<JournalEntity>) {
		val recyclerAdapter = JournalAdapter()
		with(binding) {
			rvJournal.layoutManager = LinearLayoutManager(activity)
			rvJournal.setHasFixedSize(true)
			rvJournal.adapter = recyclerAdapter
			recyclerAdapter.submitData(lifecycle, journal)
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}