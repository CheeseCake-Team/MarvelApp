package com.abaferastech.marvelapp.ui.eventDetails.series

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.remote.response.SeriesDTO
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener
import com.abaferastech.marvelapp.ui.series.seriesViewAll.SeriesViewAllInteractionListener

class SeriesHorizontalAdapter(items: List<SeriesDTO>, listener: SeriesViewAllInteractionListener) :
    BaseAdapter<SeriesDTO>(items, listener) {
    override val layoutID = R.layout.item_series_horizontal

}
interface SeriesInteractionListen : BaseInteractionListener {
    fun onClickSeries(series: SeriesDTO)
}
