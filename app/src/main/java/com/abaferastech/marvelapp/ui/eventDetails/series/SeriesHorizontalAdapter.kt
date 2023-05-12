package com.abaferastech.marvelapp.ui.eventDetails.series

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.result.Series
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener

class SeriesHorizontalAdapter(items: List<Series>, listener: SeriesInteractionListen) :
    BaseAdapter<Series>(items, listener) {
    override val layoutID = R.layout.item_series_horizontal

}
interface SeriesInteractionListen : BaseInteractionListener {
    fun onClickSeries(series: Series)
}
