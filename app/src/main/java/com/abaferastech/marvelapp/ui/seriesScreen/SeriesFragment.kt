package com.abaferastech.marvelapp.ui.seriesScreen

import android.os.Bundle
import android.view.View
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentSeriesBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment

class SeriesFragment : BaseFragment<FragmentSeriesBinding, SeriesViewModel>() {
    override val layoutIdFragment: Int
        get() = R.layout.fragment_series
    override val viewModelClass: Class<SeriesViewModel>
        get() = SeriesViewModel::class.java


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = SeriesAdapter(emptyList(), object : SeriesInteractionListener {})
        binding.recyclerViewEvents.adapter = adapter
    }

}
