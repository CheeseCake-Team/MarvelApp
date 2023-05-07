package com.abaferastech.marvelapp.ui.creatorsDetails

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.Series
import com.abaferastech.marvelapp.databinding.ItemCreatorSeriesBinding
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener


class CreatorAdapter(items : List<Series>,
                     listener: SeriesInteractionListener
): com.abaferastech.marvelapp.ui.base.BaseAdapter<Series>(items, listener) {
    override val layoutID: Int
        get() = R.layout.item_creator_series

    override fun bindItemViewHolder(
        holder: BaseViewHolder,
        item: Series,
        listener: BaseInteractionListener?
    ) {
            (holder.binding as ItemCreatorSeriesBinding).item = item
    }
}

interface SeriesInteractionListener : BaseInteractionListener {
}