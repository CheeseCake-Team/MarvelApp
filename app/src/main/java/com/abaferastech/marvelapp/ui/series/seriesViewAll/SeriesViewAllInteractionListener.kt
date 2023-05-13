package com.abaferastech.marvelapp.ui.series.seriesViewAll

import com.abaferastech.marvelapp.data.model.result.Series
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener

interface SeriesViewAllInteractionListener : BaseInteractionListener {
    fun onClickSeries(series: Series)
}
