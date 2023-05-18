package com.abaferastech.marvelapp.ui.creator.creators

import com.abaferastech.marvelapp.data.domain.models.Creator
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener

interface CreatorsInteractionListener: BaseInteractionListener {
    fun onClickCreators(creators : Creator)
}