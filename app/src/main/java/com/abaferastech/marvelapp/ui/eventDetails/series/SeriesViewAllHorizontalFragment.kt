package com.abaferastech.marvelapp.ui.eventDetails.series

import android.os.Bundle
import android.view.View
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.result.Series
import com.abaferastech.marvelapp.databinding.FragmentSeriesViewAllHorizontalBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.characters.CharactersFragment
import com.abaferastech.marvelapp.ui.events.EventsViewModel
import com.abaferastech.marvelapp.ui.home.adapters.SeriesInteractionListener
import com.abaferastech.marvelapp.ui.model.TYPE
import com.abaferastech.marvelapp.ui.seriesScreen.SeriesFragment
import com.abaferastech.marvelapp.ui.seriesScreen.SeriesViewModel
import com.abaferastech.marvelapp.utils.Constants

class SeriesViewAllHorizontalFragment :
    BaseFragment<FragmentSeriesViewAllHorizontalBinding, SeriesViewModel>() {

    override val layoutIdFragment: Int
        get() = R.layout.fragment_series_view_all_horizontal

    override val viewModelClass: Class<SeriesViewModel>
        get() = SeriesViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val typeID = arguments?.getInt(Constants.TYPE_ID)
        when (arguments?.getParcelable<TYPE>(Constants.PUT_TYPE)) {
            TYPE.EVENT -> viewModel.getEventSeries(typeID!!)
            else -> viewModel.getComicSeries(typeID!!)
        }

        val adapter = SeriesHorizontalAdapter(emptyList(), object : SeriesInteractionListen {
            override fun onClickSeries(series: Series) {

            }
        })
        binding.recyclerViewSeries.adapter = adapter
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