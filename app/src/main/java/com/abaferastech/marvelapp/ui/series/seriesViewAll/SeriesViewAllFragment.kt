package com.abaferastech.marvelapp.ui.series.seriesViewAll

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentSeriesviewallBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.model.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeriesViewAllFragment : BaseFragment<FragmentSeriesviewallBinding>() {
    override val layoutIdFragment = R.layout.fragment_seriesviewall
    override val viewModel: SeriesViewAllViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addAdapter()
        addSeriesEvents()
    }

    private fun addAdapter() {
        val adapter = SeriesViewAllAdapter(viewModel)
        binding.recyclerViewSeriesViewAll.adapter = adapter
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
                SeriesViewAllFragmentDirections
                    .actionSeriesViewAllFragmentToSeriesDetailsFragment(event.seriesID)

            null -> null
        }
        action?.let { it1 -> findNavController().navigate(it1) }
    }

}
