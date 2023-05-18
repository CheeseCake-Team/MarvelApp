package com.abaferastech.marvelapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentHomeBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.home.adapters.HomeAdapter
import com.abaferastech.marvelapp.ui.model.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutIdFragment = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        setupAdapter()
        addHomeEvents()
    }

    private fun addHomeEvents() {
        val clickHomeEventObserver = EventObserver<HomeEvent> { event ->
            handleHomeEvent(event)
        }
        viewModel.navigationEvents.observe(viewLifecycleOwner, clickHomeEventObserver)
    }

    private fun setupAdapter() {
        val adapter = HomeAdapter(mutableListOf(), viewModel)
        binding.recyclerViewHome.adapter = adapter
    }

    private fun handleHomeEvent(homeEvent: HomeEvent) {
        val action = when (homeEvent) {
            is HomeEvent.ClickCharacterEvent ->
                HomeFragmentDirections.actionHomeFragmentToCharacterFragment(homeEvent.characterID)

            is HomeEvent.ClickComicEvent ->
                HomeFragmentDirections.actionHomeFragmentToComicDetailsFragment(homeEvent.comicID)

            is HomeEvent.ClickSeriesEvent ->
                HomeFragmentDirections.actionHomeFragmentToSeriesDetailsFragment(homeEvent.seriesID)

            is HomeEvent.ClickAllCharacterEvent ->
                HomeFragmentDirections.actionHomeFragmentToCharactersFragment()

            is HomeEvent.ClickAllComicEvent ->
                HomeFragmentDirections.actionHomeFragmentToComicsGridFragment()

            is HomeEvent.ClickAllSeriesEvent ->
                HomeFragmentDirections.actionHomeFragmentToSeriesViewAllFragment()
        }
        findNavController().navigate(action)
    }


    override fun onResume() {
        super.onResume()
        if (viewModel.stateInitialized()) {
            binding.recyclerViewHome.layoutManager?.onRestoreInstanceState(
                viewModel.restoreRecyclerViewState()
            )
        }
    }

    override fun onPause() {
        super.onPause()
        binding.recyclerViewHome.layoutManager?.onSaveInstanceState()
            ?.let { viewModel.saveRecyclerViewState(it) }
    }


}