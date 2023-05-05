package com.abaferastech.marvelapp.ui.creators

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.Series
import com.abaferastech.marvelapp.databinding.CreatorSeriesCardBinding
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener

class CreatorAdapters(items : List<Series>, listener : SeriesInterActionListener)
    : BaseAdapter<Series>(items,listener){
    override val layoutID: Int
        get() = R.layout.creator_series_card

    override fun bindItemViewHolder(
        holder: BaseViewHolder,
        item: Series,
        listener: BaseInteractionListener?
    ) {
        (holder.binding as CreatorSeriesCardBinding).item = item
    }
}
interface SeriesInterActionListener : BaseInteractionListener {

}