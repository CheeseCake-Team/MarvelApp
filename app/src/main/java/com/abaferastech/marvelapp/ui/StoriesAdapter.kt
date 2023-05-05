package com.abaferastech.marvelapp.ui

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.Comics
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener

class StoriesAdapter(items: List<Comics>, listener: ComicsInteractionListener) :
    BaseAdapter<Comics>(items, listener) {
    override val layoutID = R.layout.item_comic

    override fun bindItemViewHolder(
        holder: BaseViewHolder,
        item: Comics,
        listener: BaseInteractionListener?
    ) {
        /*val itemBinding = holder.binding as StoriesIteamBinding
        itemBinding.comics = item
        itemBinding.root.setOnClickListener {
            (listener as? ComicsInteractionListener)?.onClickSeries(item)
        }*/
    }
}

interface ComicsInteractionListener : BaseInteractionListener {
    fun onClickSeries(Comics: Comics)
}
