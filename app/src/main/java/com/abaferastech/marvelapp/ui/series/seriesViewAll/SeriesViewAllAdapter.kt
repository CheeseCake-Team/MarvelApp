package com.abaferastech.marvelapp.ui.series.seriesViewAll

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.remote.response.SeriesDTO
import com.abaferastech.marvelapp.ui.base.BaseAdapter

class SeriesViewAllAdapter (items: List<SeriesDTO>, listener: SeriesViewAllInteractionListener) :
    BaseAdapter<SeriesDTO>(items, listener) {
    override val layoutID = R.layout.items_series_view_all
}

