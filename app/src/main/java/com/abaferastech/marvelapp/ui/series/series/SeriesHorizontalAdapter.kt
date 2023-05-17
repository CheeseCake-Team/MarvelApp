package com.abaferastech.marvelapp.ui.eventDetails.series

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.domain.models.Series
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.series.seriesViewAll.SeriesViewAllInteractionListener

class SeriesHorizontalAdapter(listener: SeriesViewAllInteractionListener) :
    BaseAdapter<Series>(listener) {
    override val layoutId = R.layout.item_series_horizontal

}
