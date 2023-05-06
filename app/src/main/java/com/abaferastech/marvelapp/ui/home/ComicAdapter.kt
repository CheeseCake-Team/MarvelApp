package com.abaferastech.marvelapp.ui.home

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.Comics
import com.abaferastech.marvelapp.databinding.ItemComicBinding
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener

class ComicAdapter(items: List<Comics>, listener: ComicsInteractionListener) :
    BaseAdapter<Comics>(items, listener) {
    override val layoutID = R.layout.item_comic

    override fun bindItemViewHolder(
        holder: BaseViewHolder,
        item: Comics,
        listener: BaseInteractionListener?
    ) {
        (holder.binding as ItemComicBinding).comic = item
        (holder.binding as ItemComicBinding).listener = listener as ComicsInteractionListener
    }
}

interface ComicsInteractionListener : BaseInteractionListener {
    fun onClickSeries(Comics: Comics)
}
