package com.abaferastech.marvelapp.ui.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.abaferastech.marvelapp.databinding.FragmentFilterBinding
import com.abaferastech.marvelapp.ui.model.TYPE
import com.abaferastech.marvelapp.ui.search.SearchViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilterFragment : BottomSheetDialogFragment() {

    private val searchViewModel: SearchViewModel by activityViewModels()
    private lateinit var binding: FragmentFilterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilterBinding.inflate(inflater, container, false)
        binding.viewModel = searchViewModel
        getFilterChip()
        return binding.root
    }

    private fun getFilterChip() {
        when(searchViewModel.searchType.value) {
            TYPE.SERIES -> binding.seriesChip.isChecked = true
            TYPE.CHARACTER -> binding.charactersChip.isChecked = true
            TYPE.EVENT -> binding.eventsChip.isChecked = true
            else -> binding.comicsChip.isChecked = true
        }
    }

}