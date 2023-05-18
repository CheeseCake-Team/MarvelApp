package com.abaferastech.marvelapp.ui.search

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentSearchBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.character.characters.CharactersAdapter
import com.abaferastech.marvelapp.ui.comic.comics.ComicsAdapter
import com.abaferastech.marvelapp.ui.event.events.EventAdapter
import com.abaferastech.marvelapp.ui.home.adapters.SeriesAdapter
import com.abaferastech.marvelapp.ui.model.EventObserver
import com.abaferastech.marvelapp.ui.model.TYPE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    override val layoutIdFragment = R.layout.fragment_search
    override val viewModel: SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        addEventAndListeners()
    }

    private fun init() {
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(false)
    }

    private fun addEventAndListeners() {
        binding.searchView.editText.setOnEditorActionListener { v, actionId, event ->
            binding.searchBar.text = binding.searchView.text
            binding.searchView.hide()
            viewModel.search(binding.searchView.text.toString())
            false
        }

        binding.searchBar.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId) {
                R.id.filterFragment ->{
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
        val action = when (event) {
            is SearchEvents.ClickCharacterEvent -> SearchFragmentDirections
                .actionSearchFragmentToCharacterFragment(event.characterID)

            is SearchEvents.ClickComicEvent -> SearchFragmentDirections
                .actionSearchFragmentToComicDetailsFragment(event.comicID)

            is SearchEvents.ClickEventEvent -> SearchFragmentDirections
                .actionSearchFragmentToEventFragment(event.eventID)

            is SearchEvents.ClickSeriesEvent -> SearchFragmentDirections
                .actionSearchFragmentToSeriesDetailsFragment(event.seriesID)

            null -> null
        }
        action?.let { it1 -> findNavController().navigate(it1) }
    }

    private fun setAdapter(it: TYPE?) {
        val adapter = when (it) {
            TYPE.CHARACTER -> CharactersAdapter(viewModel)
            TYPE.EVENT -> EventAdapter(viewModel)
            TYPE.SERIES -> SeriesAdapter(viewModel)
            else -> ComicsAdapter(viewModel)
        }
        binding.recyclerViewSearchResult.adapter = adapter
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