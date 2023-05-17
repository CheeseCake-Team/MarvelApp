package com.abaferastech.marvelapp.ui.filter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.abaferastech.marvelapp.databinding.FragmentFilterBinding
import com.abaferastech.marvelapp.ui.model.TYPE
import com.abaferastech.marvelapp.ui.search.SearchViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilterFragment : BottomSheetDialogFragment() {


    private val searchViewModel by activityViewModels<SearchViewModel>()
    private lateinit var binding: FragmentFilterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilterBinding.inflate(inflater, container, false)
        //searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        binding.viewModel = searchViewModel
        Log.i("state",searchViewModel.toString())
        getFilterChip()
        return binding.root
    }

    private fun getFilterChip() {
        when(searchViewModel.searchType.value) {
            TYPE.SERIES -> binding.seriesChip.isSelected = true
            TYPE.CHARACTER -> binding.charactersChip.isSelected = true
            TYPE.EVENT -> binding.eventsChip.isSelected = true
            else -> binding.comicsChip.isSelected = true
        }
        Log.i("state",searchViewModel.searchType.value.toString())
    }

}