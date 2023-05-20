package com.abaferastech.marvelapp.ui.series.seriesDetails

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentSeriesDetailsBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class SeriesDetailsFragment : BaseFragment<FragmentSeriesDetailsBinding>() {

    override val layoutIdFragment = R.layout.fragment_series_details
    override val viewModel: SeriesDetailsViewModel by viewModels()

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar.let {
            it?.hide()
        }
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar.let {
            it?.show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFavourites.setOnClickListener {
            if ((it as CheckBox).isChecked) {
                viewModel.isFavouriteClicked.postValue(true)
            } else {
                viewModel.isFavouriteClicked.postValue(false)
            }
        }

        viewModel.isFavouriteClicked.observe(viewLifecycleOwner) { isClicked ->
            isClicked?.let {
                if (it) {
                    viewModel.insertSeries()
                    Toast.makeText(requireContext(), "added to room", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.deleteSeries()
                    Toast.makeText(requireContext(), "removed from room", Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.getSeriesById()
        init()
    }

    private fun init() {
        val adapter = SeriesDetailsFragmentPageAdapter(
            requireActivity().supportFragmentManager,
            lifecycle,
            viewModel.seriesArgs.seriesId
        )
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Characters"
                1 -> tab.text = "Creators"
                2 -> tab.text = "Comics"
                3 -> tab.text = "Details"
            }
        }.attach()
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}