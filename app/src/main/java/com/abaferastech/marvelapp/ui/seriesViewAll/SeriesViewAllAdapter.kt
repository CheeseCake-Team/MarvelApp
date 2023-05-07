package com.abaferastech.marvelapp.ui.seriesViewAll

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.Series
import com.abaferastech.marvelapp.databinding.ItemsSeriesViewAllBinding
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener

class SeriesViewAllAdapter (items: List<Series>, listener: SeriesViewAllInteractionListener) :
    BaseAdapter<Series>(items, listener) {
    override val layoutID = R.layout.items_series_view_all

    override fun bindItemViewHolder(
        holder: BaseViewHolder,
        item: Series,
        listener: BaseInteractionListener?
    ) {
        (holder.binding as ItemsSeriesViewAllBinding).series = item
    }
}

interface SeriesViewAllInteractionListener : BaseInteractionListener {
}
