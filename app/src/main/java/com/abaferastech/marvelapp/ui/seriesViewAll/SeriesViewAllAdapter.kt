package com.abaferastech.marvelapp.ui.seriesViewAll

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.result.Series
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener

class SeriesViewAllAdapter (items: List<Series>, listener: SeriesViewAllInteractionListener) :
    BaseAdapter<Series>(items, listener) {
    override val layoutID = R.layout.items_series_view_all
}

interface SeriesViewAllInteractionListener : BaseInteractionListener {
}
