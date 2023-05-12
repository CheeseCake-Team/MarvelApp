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

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(),
    NavigationInteractionListener {
    override val layoutIdFragment: Int
        get() = R.layout.fragment_home
    override val viewModelClass: Class<HomeViewModel>
        get() = HomeViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        viewModel.homeData.observe(viewLifecycleOwner) {
            val adapter = it.toData()?.let { it1 -> HomeAdapter(it1, this) }
            binding.recyclerViewHome.adapter = adapter
        }

        viewModel.navigationEvents.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { homeEvent ->
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
        }


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


}