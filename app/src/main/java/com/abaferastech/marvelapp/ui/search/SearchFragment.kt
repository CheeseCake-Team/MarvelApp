package com.abaferastech.marvelapp.ui.search

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentSearchBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.character.characters.CharacterViewAllAdapter
import com.abaferastech.marvelapp.ui.comic.comics.ComicsAdapter
import com.abaferastech.marvelapp.ui.event.events.EventAdapter
import com.abaferastech.marvelapp.ui.model.EventObserver
import com.abaferastech.marvelapp.ui.model.TYPE
import com.abaferastech.marvelapp.ui.series.seriesViewAll.SeriesViewAllAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    override val layoutIdFragment = R.layout.fragment_search
    override val viewModel: SearchViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        addEventAndListeners()
    }

    private fun init() {
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        val searchQueryAdapter = SearchQueryAdapter(viewModel)
        binding.recyclerViewSearchHistory.adapter = searchQueryAdapter
    }

    private fun addEventAndListeners() {
        binding.searchView.editText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                binding.searchBar.text = binding.searchView.text
                binding.searchView.hide()
                viewModel.search(binding.searchView.text.toString())
                viewModel.saveSearchQuery(binding.searchView.text.toString())
            }
            false
        }

        binding.searchBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.filterFragment -> {
                    findNavController().navigate(R.id.action_searchFragment_to_filterFragment)
                }
            }
            true
        }

        viewModel.searchType.observe(viewLifecycleOwner) {
            setAdapter(it)
        }

        val clickSearchEventObserver = EventObserver<SearchEvents> { event ->
            handleSearchEvent(event)
        }
        viewModel.navigationEvents.observe(viewLifecycleOwner, clickSearchEventObserver)
    }

    private fun handleSearchEvent(event: SearchEvents?) {
        when (event) {
            is SearchEvents.ClickCharacterEvent -> {
                findNavController().navigate(
                    SearchFragmentDirections
                        .actionSearchFragmentToCharacterFragment(event.characterID)
                )
            }

            is SearchEvents.ClickComicEvent -> {
                findNavController().navigate(
                    SearchFragmentDirections
                        .actionSearchFragmentToComicDetailsFragment(event.comicID)
                )
            }

            is SearchEvents.ClickEventEvent -> {
                findNavController().navigate(
                    SearchFragmentDirections
                        .actionSearchFragmentToEventFragment(event.eventID)
                )
            }

            is SearchEvents.ClickSeriesEvent ->
                findNavController().navigate(
                    SearchFragmentDirections
                        .actionSearchFragmentToSeriesDetailsFragment(event.seriesID)
                )

            is SearchEvents.HideSearchViewEvent -> {
                binding.searchBar.text = event.searchQuery
                binding.searchView.hide()
            }

            else -> {}
        }
    }

    private fun setAdapter(it: TYPE?) {
        val adapter = when (it) {
            TYPE.CHARACTER -> CharacterViewAllAdapter(viewModel)
            TYPE.EVENT -> EventAdapter(viewModel)
            TYPE.SERIES -> SeriesViewAllAdapter(viewModel)
            else -> ComicsAdapter(viewModel)
        }
        binding.recyclerViewSearchResult.adapter = adapter
        binding.recyclerViewSearchResult.layoutManager = when (it) {
            TYPE.CHARACTER -> GridLayoutManager(requireContext(), 4)
            TYPE.SERIES -> GridLayoutManager(requireContext(), 2)
            else -> LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar?.show()
    }

}