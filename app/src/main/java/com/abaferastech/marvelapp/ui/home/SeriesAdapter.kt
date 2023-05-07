package com.abaferastech.marvelapp.ui.home

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.Series
import com.abaferastech.marvelapp.databinding.ItemSeriesBinding
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener

class SeriesAdapter(items: List<Series>, listener: SeriesInteractionListener) :
    BaseAdapter<Series>(items, listener) {
    override val layoutID = R.layout.item_series

    override fun bindItemViewHolder(
        holder: BaseViewHolder,
        item: Series,
        listener: BaseInteractionListener?
    ) {
        (holder.binding as ItemSeriesBinding).apply {
            series = item
            this.listener = listener as SeriesInteractionListener
        }
    }
}

interface SeriesInteractionListener : BaseInteractionListener {
    fun onClickSeries(series: Series)
}