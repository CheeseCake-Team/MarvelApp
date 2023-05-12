package com.abaferastech.marvelapp.ui.series.seriesDetails

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentSeriesDetailsBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.comic.comicDetails.ComicFragmentPageAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class SeriesDetailsFragment: BaseFragment<FragmentSeriesDetailsBinding, SeriesDetailsViewModel>() {

    override val layoutIdFragment: Int
        get() = R.layout.fragment_series_details
    override val viewModelClass: Class<SeriesDetailsViewModel>
        get() = SeriesDetailsViewModel::class.java

    private val args: SeriesDetailsFragmentArgs by navArgs()

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
        viewModel.getSeriesById(args.seriesId)

        init()
    }

    private fun init() {
        val adapter = SeriesDetailsFragmentPageAdapter(
            requireActivity().supportFragmentManager,
            lifecycle,
            args.seriesId
        )
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Details"
                1 -> tab.text = "Characters"
                2 -> tab.text = "Creators"
                3 -> tab.text = "Comics"
            }
        }.attach()
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}