package com.abaferastech.marvelapp.ui.home.adapters

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.remote.response.SeriesDTO
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener

class SeriesAdapter(items: List<SeriesDTO>, listener: SeriesInteractionListener) :
    BaseAdapter<SeriesDTO>(items, listener) {
    override val layoutID = R.layout.item_series

}

interface SeriesInteractionListener : BaseInteractionListener {
    fun onClickSeries(series: SeriesDTO)
}
