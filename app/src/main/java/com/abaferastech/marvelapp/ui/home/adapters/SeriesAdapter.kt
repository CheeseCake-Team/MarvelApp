package com.abaferastech.marvelapp.ui.home.adapters

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.remote.response.SeriesDTO
import com.abaferastech.marvelapp.domain.models.Series
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener

class SeriesAdapter(listener: SeriesInteractionListener) :
    BaseAdapter<Series>(listener) {
    override val layoutId = R.layout.item_series

}

interface SeriesInteractionListener : BaseInteractionListener {
    fun onClickSeries(series: Series)
}
