package com.abaferastech.marvelapp.ui.series.seriesScreen

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.result.Series
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener

class SeriesAdapter(items: List<Series>, listener: SeriesInteractionListener) :
    BaseAdapter<Series>(items, listener) {
    override val layoutID: Int
        get() = R.layout.item_series
}

interface SeriesInteractionListener : BaseInteractionListener {

}


