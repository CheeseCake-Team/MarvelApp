package com.abaferastech.marvelapp.ui.favourites.favouritesAdapters

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.domain.models.Series
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.character.characters.CharactersInteractionListener
import com.abaferastech.marvelapp.ui.home.adapters.SeriesInteractionListener
import com.abaferastech.marvelapp.ui.series.seriesViewAll.SeriesViewAllInteractionListener

class FavouriteSeriesAdapter (listener: SeriesViewAllInteractionListener) :
    BaseAdapter<Series>(object : SeriesViewAllInteractionListener {
        override fun onClickSeries(series: Series) {}
    }) {

    override val layoutId = R.layout.item_series_horizontal

}