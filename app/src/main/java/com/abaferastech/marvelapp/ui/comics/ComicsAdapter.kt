package com.abaferastech.marvelapp.ui.comics

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.Comics
import com.abaferastech.marvelapp.databinding.ItemComicBinding
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener

class ComicsAdapter(items: List<Comics>, listener: ComicsInteractionListener) :
    BaseAdapter<Comics>(items, listener) {
    override val layoutID = R.layout.item_comic

    override fun bindItemViewHolder(
        holder: BaseViewHolder,
        item: Comics,
        listener: BaseInteractionListener?
    ) {
        (holder.binding as ItemComicBinding).comics = item
    }
}

interface ComicsInteractionListener : BaseInteractionListener {
}

