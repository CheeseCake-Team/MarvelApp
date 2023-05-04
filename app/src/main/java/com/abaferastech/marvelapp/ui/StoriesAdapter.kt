package com.abaferastech.marvelapp.ui

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.Stories
import com.abaferastech.marvelapp.databinding.StoriesIteamBinding
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener

class StoriesAdapter(items: List<Stories>, listener: StoriesInteractionListener) :
    BaseAdapter<Stories>(items, listener) {
    override val layoutID = R.layout.stories_iteam

    override fun bindItemViewHolder(
        holder: BaseViewHolder,
        item: Stories,
        listener: BaseInteractionListener?
    ) {
        val itemBinding = holder.binding as StoriesIteamBinding
        itemBinding.stories = item
        itemBinding.root.setOnClickListener {
            (listener as? StoriesInteractionListener)?.onClickSeries(item)
        }
    }
}

interface StoriesInteractionListener : BaseInteractionListener {
    fun onClickSeries(stories: Stories)
}
