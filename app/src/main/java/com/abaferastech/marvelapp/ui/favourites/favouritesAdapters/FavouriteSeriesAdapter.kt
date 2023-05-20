package com.abaferastech.marvelapp.ui.favourites.favouritesAdapters

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.domain.models.Series
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.character.characters.CharactersInteractionListener
import com.abaferastech.marvelapp.ui.home.adapters.SeriesInteractionListener

class FavouriteSeriesAdapter (listener: CharactersInteractionListener) :
    BaseAdapter<Series>(object : SeriesInteractionListener {
        override fun onClickSeries(series: Series) {}
    }) {

    override val layoutId = R.layout.item_series_horizontal

}