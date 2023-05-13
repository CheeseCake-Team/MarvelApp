package com.abaferastech.marvelapp.ui.comic.comics

import com.abaferastech.marvelapp.ui.home.HomeEvent

sealed class ComicEvents {
    data class ClickComicEvent(val comicID: Int) : ComicEvents()
}