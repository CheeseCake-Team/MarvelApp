package com.abaferastech.marvelapp.ui.creators

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.CreatorSeriesFragmentBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.eventScreen.EventAdapter
import com.abaferastech.marvelapp.ui.eventScreen.EventsInteractionListener


class CreatorsFragment : BaseFragment<CreatorSeriesFragmentBinding,CreatorsViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override val layoutIdFragment: Int
        get() = R.layout.creator_series_fragment
    override val viewModelClass: Class<CreatorsViewModel>
        get() = CreatorsViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CreatorAdapters(emptyList(), object : SeriesInterActionListener{})
        binding.recyclerSeries.adapter = adapter
    }

}