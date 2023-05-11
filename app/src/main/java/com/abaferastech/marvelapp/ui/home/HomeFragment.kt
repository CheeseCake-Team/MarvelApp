package com.abaferastech.marvelapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.ui.model.DataItem
import com.abaferastech.marvelapp.databinding.FragmentHomeBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.home.adapters.HomeAdapter
import com.abaferastech.marvelapp.ui.home.adapters.NavigationInteractionListener

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
            val adapter = HomeAdapter(it as List<DataItem>, this)
            binding.recyclerViewHome.adapter = adapter
        }

        viewModel.selectedCharacterID.observe(viewLifecycleOwner) {
            if (viewModel.isCharacterClicked.value == true) {
                val action = HomeFragmentDirections.actionHomeFragmentToCharacterFragment(it)
                findNavController().navigate(action)
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