package com.abaferastech.marvelapp.ui.seriesScreen

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.Series
import com.abaferastech.marvelapp.databinding.ItemEventsBinding
import com.abaferastech.marvelapp.databinding.ItemSeriesBinding
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener


class SeriesAdapter(items: List<Series>, listener: SeriesInteractionListener) :
    BaseAdapter<Series>(items, listener) {
    override val layoutID: Int
        get() = R.layout.item_series

    override fun bindItemViewHolder(
        holder: BaseViewHolder,
        item: Series,
        listener: BaseInteractionListener?
    ) {
        (holder.binding as ItemSeriesBinding).series = item
    }
}

interface SeriesInteractionListener : BaseInteractionListener {

}


