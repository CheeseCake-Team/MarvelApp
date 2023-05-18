package com.abaferastech.marvelapp.ui.series.seriesViewAll

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.domain.models.Series
import com.abaferastech.marvelapp.ui.base.BaseAdapter

class SeriesViewAllAdapter(listener: SeriesViewAllInteractionListener) :
    BaseAdapter<Series>(listener) {
    override val layoutId = R.layout.items_series_view_all
}

