package com.abaferastech.marvelapp.ui.creators

import android.os.Bundle
import android.view.View
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.CreatorSeriesFragmentBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment


class CreatorFragment : BaseFragment<CreatorSeriesFragmentBinding,CreatorViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override val layoutIdFragment: Int
        get() = R.layout.creator_series_fragment
    override val viewModelClass: Class<CreatorViewModel>
        get() = CreatorViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CreatorAdapter(emptyList(), object : SeriesInteractionListener{})
        binding.recyclerSeries.adapter = adapter
    }


}