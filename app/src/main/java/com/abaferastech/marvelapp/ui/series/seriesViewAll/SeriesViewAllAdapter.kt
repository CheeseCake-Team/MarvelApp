package com.abaferastech.marvelapp.ui.series.seriesViewAll

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.result.Series
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener

class SeriesViewAllAdapter (items: List<Series>, listener: SeriesViewAllInteractionListener) :
    BaseAdapter<Series>(listener) {
    override val layoutId = R.layout.items_series_view_all
}

