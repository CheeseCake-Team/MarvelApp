package com.abaferastech.marvelapp.ui.creatorsDetails

import android.os.Bundle
import android.view.View
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.CreatorDetailsBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment


class CreatorDetailsFragment : BaseFragment<CreatorDetailsBinding, CreatorDetailsViewModel>() {

    override val layoutIdFragment: Int
        get() = R.layout.creator_details
    override val viewModelClass: Class<CreatorDetailsViewModel>
        get() = CreatorDetailsViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMarvelCreator(15)
        val adapter = CreatorAdapter(emptyList(), object : SeriesInteractionListener {})
        binding.recyclerSeries.adapter = adapter

        viewModel.series.observe(viewLifecycleOwner) {
            binding.recyclerSeries.adapter = adapter
        }

    }

}