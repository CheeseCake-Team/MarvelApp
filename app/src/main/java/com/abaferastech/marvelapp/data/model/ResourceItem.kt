package com.abaferastech.marvelapp.data.model

sealed class ResourceItem {
    abstract val id: Int?

    class CharacterItem(val character: Characters) : ResourceItem() {
        override val id: Int?
            get() = character.id
    }

    class ComicItem(val comic: Comics) : ResourceItem() {
        override val id: Int?
            get() = comic.id
    }

    class SeriesItem(val series: Series) : ResourceItem() {
        override val id: Int
            get() = series.id
    }

    class EventItem(val event: Events) : ResourceItem() {
        override val id: Int?
            get() = event.id
    }

    class CreatorItem(val creator: Creators) : ResourceItem() {
        override val id: Int?
            get() = creator.id
    }

    class StoryItem(val story: Stories) : ResourceItem() {
        override val id: Int?
            get() = story.id
    }
}
