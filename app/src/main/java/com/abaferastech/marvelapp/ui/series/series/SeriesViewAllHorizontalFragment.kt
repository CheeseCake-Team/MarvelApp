package com.abaferastech.marvelapp.ui.series.series

import android.os.Bundle
import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentSeriesViewAllHorizontalBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.creator.creatorsDetails.CreatorDetailsFragmentDirections
import com.abaferastech.marvelapp.ui.eventDetails.series.SeriesHorizontalAdapter
import com.abaferastech.marvelapp.ui.model.EventObserver
import com.abaferastech.marvelapp.ui.model.TYPE
import com.abaferastech.marvelapp.ui.series.seriesViewAll.SeriesEvents
import com.abaferastech.marvelapp.ui.series.seriesViewAll.SeriesViewAllFragmentDirections
import com.abaferastech.marvelapp.ui.series.seriesViewAll.SeriesViewAllViewModel
import com.abaferastech.marvelapp.utilities.Constants

class SeriesViewAllHorizontalFragment :
    BaseFragment<FragmentSeriesViewAllHorizontalBinding, SeriesViewAllViewModel>() {

    override val layoutIdFragment = R.layout.fragment_series_view_all_horizontal

    override val viewModelClass = SeriesViewAllViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        setupAdapter()
        addSeriesEvents()
    }

    private fun addSeriesEvents() {

        val clickSeriesEventObserver = EventObserver<SeriesEvents> { event ->
            handleSeriesEvent(event)
        }

        viewModel.navigationEvents.observe(viewLifecycleOwner, clickSeriesEventObserver)
    }

    private fun handleSeriesEvent(event: SeriesEvents?) {
        val action = when (event) {
            is SeriesEvents.ClickSeriesEvent ->
                navDirections(event)
            null -> null
        }
        action?.let { it1 -> findNavController().navigate(it1) }
    }

    private fun navDirections(event: SeriesEvents.ClickSeriesEvent): NavDirections {

        return when (arguments?.getParcelable<TYPE>(Constants.PUT_TYPE)) {
            TYPE.CREATOR -> CreatorDetailsFragmentDirections
                .actionCreatorDetailsFragmentToSeriesDetailsFragment2(event.seriesID)
            else -> SeriesViewAllFragmentDirections
                .actionSeriesViewAllFragmentToSeriesDetailsFragment(event.seriesID)
        }
    }


    private fun setupAdapter() {
        val adapter = SeriesHorizontalAdapter( viewModel)
        binding.recyclerViewSeries.adapter = adapter
    }

    private fun init() {
        val typeID = arguments?.getInt(Constants.TYPE_ID)
        when (arguments?.getParcelable<TYPE>(Constants.PUT_TYPE)) {
            TYPE.EVENT -> viewModel.getEventSeries(typeID!!)
            TYPE.CREATOR -> viewModel.getCreatorSeries(typeID!!)
            else -> viewModel.getComicSeries(typeID!!)
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(id: Int, type: TYPE) = SeriesViewAllHorizontalFragment().apply {
            arguments = Bundle().apply {
                putInt(Constants.TYPE_ID, id)
                putParcelable(Constants.PUT_TYPE, type)
            }
        }
    }
}