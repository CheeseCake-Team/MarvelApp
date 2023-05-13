package com.abaferastech.marvelapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentHomeBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.home.adapters.HomeAdapter
import com.abaferastech.marvelapp.ui.home.adapters.NavigationInteractionListener
import com.abaferastech.marvelapp.ui.model.DataItem
import com.abaferastech.marvelapp.ui.model.EventObserver

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(),
    NavigationInteractionListener {
    override val layoutIdFragment = R.layout.fragment_home
    override val viewModelClass = HomeViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        viewModel.homeData.observe(viewLifecycleOwner) {
            val adapter = it.toData()?.let { it1 -> HomeAdapter(it1, this) }
            binding.recyclerViewHome.adapter = adapter
        }

        val clickHomeEventObserver = EventObserver<HomeEvent> { event ->
            handleHomeEvent(event)
        }
        viewModel.navigationEvents.observe(viewLifecycleOwner,clickHomeEventObserver)
    }

    private fun handleHomeEvent(homeEvent: HomeEvent) {
        val action = when (homeEvent) {
            is HomeEvent.ClickCharacterEvent ->
                HomeFragmentDirections.actionHomeFragmentToCharacterFragment(homeEvent.characterID)
            is HomeEvent.ClickComicEvent ->
                HomeFragmentDirections.actionHomeFragmentToComicDetailsFragment(homeEvent.comicID)
            is HomeEvent.ClickSeriesEvent ->
                HomeFragmentDirections.actionHomeFragmentToSeriesDetailsFragment(homeEvent.seriesID)
        }
        findNavController().navigate(action)
    }

    override fun onNavigate(dataItem: DataItem) {
        when (dataItem) {
            is DataItem.CharacterTagItem -> {
                val action = HomeFragmentDirections.actionHomeFragmentToCharactersFragment()
                findNavController().navigate(action)
            }

            is DataItem.ComicsTagItem -> {
                val action = HomeFragmentDirections.actionHomeFragmentToComicsGridFragment()
                findNavController().navigate(action)
            }

            is DataItem.SeriesTagItem -> {
                val action = HomeFragmentDirections.actionHomeFragmentToSeriesViewAllFragment()
                findNavController().navigate(action)
            }
            else -> {}
        }
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