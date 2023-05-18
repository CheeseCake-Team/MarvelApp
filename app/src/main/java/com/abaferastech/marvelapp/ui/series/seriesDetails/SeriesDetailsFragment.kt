package com.abaferastech.marvelapp.ui.series.seriesDetails

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentSeriesDetailsBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.comic.comicDetails.ComicDetailsFragmentArgs
import com.abaferastech.marvelapp.ui.comic.comicDetails.ComicFragmentPageAdapter
import com.google.android.material.tabs.TabLayoutMediator

class SeriesDetailsFragment : BaseFragment<FragmentSeriesDetailsBinding, SeriesDetailsViewModel>() {

    override val layoutIdFragment: Int
        get() = R.layout.fragment_series_details
    override val viewModelClass: Class<SeriesDetailsViewModel>
        get() = SeriesDetailsViewModel::class.java
    private val passedId: SeriesDetailsFragmentArgs by navArgs()

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

        viewModel.saveSeriesId(passedId.seriesId)
        init()
    }

    private fun init() {
        setupPageAdapter()

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

    private fun setupPageAdapter() {
        val adapter = SeriesDetailsFragmentPageAdapter(
            requireActivity().supportFragmentManager,
            lifecycle,
            passedId.seriesId
        )
        binding.viewPager.adapter = adapter
    }
}