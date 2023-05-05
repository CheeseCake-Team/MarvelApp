package com.abaferastech.marvelapp.ui.creators

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.Comics
import com.abaferastech.marvelapp.data.model.Creators
import com.abaferastech.marvelapp.data.model.response.CreatorItem
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener

class CreatorsAdapter(

    items : List<Comics>,
    listener : CreatorsInteractionListener?
) : BaseAdapter<Comics>(items, listener){
    override val layoutID: Int
        get() = R.layout.creator_series_card

}
interface CreatorsInteractionListener : BaseInteractionListener{
    fun onClickCreator(creator : Creators)
}