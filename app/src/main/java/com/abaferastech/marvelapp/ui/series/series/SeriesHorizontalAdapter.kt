package com.abaferastech.marvelapp.ui.eventDetails.series

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.result.Series
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener
import com.abaferastech.marvelapp.ui.series.seriesViewAll.SeriesViewAllInteractionListener

class SeriesHorizontalAdapter(items: List<Series>, listener: SeriesViewAllInteractionListener) :
    BaseAdapter<Series>(listener) {
    override val layoutId = R.layout.item_series_horizontal

}
