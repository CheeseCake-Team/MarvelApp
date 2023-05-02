package com.abaferastech.marvelapp.milk

data class MarvelResponse(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val etag: String,
    val data: MarvelData
)

data class MarvelData(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Series>
)

data class Series(
    val id: Int,
    val title: String,
    val description: String?,
    val resourceURI: String,
    val urls: List<Url>,
    val startYear: Int,
    val endYear: Int,
    val rating: String,
    val type: String,
    val modified: String,
    val thumbnail: Thumbnail,
    val creators: Creators,
    val characters: Characters,
    val stories: Stories,
    val comics: Comics,
    val events: Events,
    val next: SeriesSummary?,
    val previous: SeriesSummary?
)

data class Url(
    val type: String,
    val url: String
)

data class Thumbnail(
    val path: String,
    val extension: String
)

data class Creators(
    val available: Int,
    val collectionURI: String,
    val items: List<Creator>,
    val returned: Int
)

data class Creator(
    val resourceURI: String,
    val name: String,
    val role: String
)

data class Characters(
    val available: Int,
    val collectionURI: String,
    val items: List<Character>,
    val returned: Int
)

data class Character(
    val resourceURI: String,
    val name: String
)

data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<Story>,
    val returned: Int
)

data class Story(
    val resourceURI: String,
    val name: String,
    val type: String
)

data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<Comic>,
    val returned: Int
)

data class Comic(
    val resourceURI: String,
    val name: String
)

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<Event>,
    val returned: Int
)

data class Event(
    val resourceURI: String,
    val name: String
)

data class SeriesSummary(
    val resourceURI: String?,
    val name: String?
)