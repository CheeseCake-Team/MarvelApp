package com.abaferastech.marvelapp.ui.characters

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.Characters
import com.abaferastech.marvelapp.databinding.ItemCharacterBinding
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener

class CharactersAdapter(items: List<Characters>, listener: CharactersInteractionListener) :
    BaseAdapter<Characters>(items, listener) {
    override val layoutID: Int
        get() = R.layout.item_character

    override fun bindItemViewHolder(
        holder: BaseViewHolder,
        item: Characters,
        listener: BaseInteractionListener?,
    ) {
        (holder.binding as ItemCharacterBinding).item = item
    }

}