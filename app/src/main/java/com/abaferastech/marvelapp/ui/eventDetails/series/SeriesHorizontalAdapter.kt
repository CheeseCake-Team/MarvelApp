package com.abaferastech.marvelapp.ui.eventDetailsScreen.series

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.Series
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener

class SeriesHorizontalAdapter(items: List<Series>, listener: SeriesInteractionListener) :
    BaseAdapter<Series>(items, listener) {
    override val layoutID = R.layout.item_series_horizontal

}
interface SeriesInteractionListener : BaseInteractionListener {
}
