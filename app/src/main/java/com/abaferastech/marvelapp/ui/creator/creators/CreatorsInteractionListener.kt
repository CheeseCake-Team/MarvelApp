package com.abaferastech.marvelapp.ui.creator.creators

import com.abaferastech.marvelapp.data.model.result.Creators
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener

interface CreatorsInteractionListener: BaseInteractionListener {
    fun onClickCreators(creators : Creators)
}