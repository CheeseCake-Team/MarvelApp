package com.abaferastech.marvelapp.ui.series.seriesViewAll

import com.abaferastech.marvelapp.ui.home.HomeEvent

sealed class SeriesEvents {
    data class ClickSeriesEvent(val seriesID: Int) : SeriesEvents()

}