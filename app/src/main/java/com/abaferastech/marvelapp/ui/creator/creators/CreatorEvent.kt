package com.abaferastech.marvelapp.ui.creator.creators

sealed class CreatorEvent {
    data class ClickCreatorEvent(val creatorID: Int) : CreatorEvent()

}