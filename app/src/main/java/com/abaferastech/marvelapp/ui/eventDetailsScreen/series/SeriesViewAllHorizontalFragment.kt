package com.abaferastech.marvelapp.ui.eventDetailsScreen.series

import android.os.Bundle
import android.view.View
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentSeriesViewAllHorizontalBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.seriesScreen.SeriesViewModel

class SeriesViewAllHorizontalFragment :
    BaseFragment<FragmentSeriesViewAllHorizontalBinding, SeriesViewModel>() {

    override val layoutIdFragment: Int
        get() = R.layout.fragment_series_view_all_horizontal

    override val viewModelClass: Class<SeriesViewModel>
        get() = SeriesViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val eventId = arguments?.getInt(EVENT_ID)

        if (eventId != null) {
            viewModel.getSeriesByEventId(eventId)
        }

        val adapter = SeriesHorizontalAdapter(emptyList(), object : SeriesInteractionListener {})
        binding.recyclerViewSeries.adapter = adapter
    }

    companion object {
        private const val EVENT_ID = "event_id"

        @JvmStatic
        fun newInstance(id: Int) = SeriesViewAllHorizontalFragment().apply {
            arguments = Bundle().apply {
                putInt(EVENT_ID, id)
            }
        }
    }
}