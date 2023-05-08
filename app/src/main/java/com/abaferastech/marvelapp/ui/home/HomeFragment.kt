package com.abaferastech.marvelapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.DataItem
import com.abaferastech.marvelapp.databinding.FragmentHomeBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(),NavigationInteractionListener{
    override val layoutIdFragment: Int
        get() = R.layout.fragment_home
    override val viewModelClass: Class<HomeViewModel>
        get() = HomeViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.homeData.observe(viewLifecycleOwner) {
            val adapter = HomeAdapter(it as List<DataItem>, this)
            binding.recyclerViewHome.adapter = adapter
        }
        viewModel.isCharacterClicked.observe(viewLifecycleOwner){isClicked ->
            if(isClicked){
                val selectedCharacter = viewModel.selectedCharacterID.value
                val action = selectedCharacter?.let {
                    HomeFragmentDirections.actionHomeFragmentToCharacterFragment(
                        it
                    )
                }
                action?.let { findNavController().navigate(it) }
                viewModel.resetCharacterClickStatus()
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


            }
            is DataItem.HeaderItem -> TODO()
            is DataItem.SeriesTagItem -> {
                val action = HomeFragmentDirections.actionHomeFragmentToSeriesViewAllFragment()
                findNavController().navigate(action)
            }
            else -> {}
        }
    }

}