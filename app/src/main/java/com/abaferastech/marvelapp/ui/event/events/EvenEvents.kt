package com.abaferastech.marvelapp.ui.event.events

sealed class EvenEvents {
    data class ClickEventEvents(val eventID: Int) : EvenEvents()
}