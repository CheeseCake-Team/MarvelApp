package com.abaferastech.marvelapp.data.repository

import com.abaferastech.marvelapp.data.remote.response.BaseResponse
import com.abaferastech.marvelapp.domain.models.Character
import com.abaferastech.marvelapp.domain.models.Comic
import com.abaferastech.marvelapp.domain.models.Creator
import com.abaferastech.marvelapp.domain.models.Event
import com.abaferastech.marvelapp.domain.models.Series
import com.abaferastech.marvelapp.ui.model.UIState
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

interface IMarvelRepository {
    fun searchInComics(query: String): Single<UIState<List<Comic>>>
    fun searchInCharacters(query: String): Single<UIState<List<Character>>>
    fun searchInEvents(query: String): Single<UIState<List<Event>>>
    fun searchInSeries(query: String): Single<UIState<List<Series>>>
    fun getSingleCharacter(characterId: Int): Single<UIState<Character>>
    fun getSingleEvent(eventId: Int): Single<UIState<Event>>
    fun getSingleSeries(seriesId: Int): Single<UIState<Series>>
    fun getSingleComic(comicId: Int): Single<UIState<Comic>>
    fun getSingleCreator(creatorId: Int): Single<UIState<Creator>>
    fun getAllEvents(): Single<UIState<List<Event>>>
    fun getAllCharacters(): Single<UIState<List<Character>>>
    fun getAllComics(): Single<UIState<List<Comic>>>
    fun getAllCreators(): Single<UIState<List<Creator>>>
    fun getEventComics(eventId: Int): Single<UIState<List<Comic>>>
    fun getCharacterEvents(characterId: Int): Single<UIState<List<Event>>>
    fun getCharacterComics(characterId: Int): Single<UIState<List<Comic>>>
    fun getCharacterSeries(characterId: Int): Single<UIState<List<Series>>>
    fun getSeriesFullUrl(fullUrl: String): Single<UIState<List<Series>>>
    fun getSeriesComics(seriesId: Int): Single<UIState<List<Comic>>>
    fun getSeriesEvents(seriesId: Int): Single<UIState<List<Event>>>
    fun getSeriesCharacters(seriesId: Int): Single<UIState<List<Character>>>
    fun getEventCharacters(eventId: Int): Single<UIState<List<Character>>>
    fun getAllSeries(): Single<UIState<List<Series>>>
    fun getComicEvents(comicId: Int): Single<UIState<List<Event>>>
    fun getComicCharacters(comicId: Int): Single<UIState<List<Character>>>
    fun getComicSeries(comicId: Int): Single<UIState<List<Series>>>
    fun getEventSeries(eventId: Int): Single<UIState<List<Series>>>
    fun getSeriesCreators(creatorId: Int): Single<UIState<List<Creator>>>
    fun getComicCreators(comicId: Int): Single<UIState<List<Creator>>>
    fun getCreatorEvents(creatorId: Int): Single<UIState<List<Event>>>
    fun getCreatorCharacters(creatorId: Int): Single<UIState<List<Character>>>
    fun getCreatorComics(creatorId: Int): Single<UIState<List<Comic>>>
    fun getCreatorSeries(creatorId: Int): Single<UIState<List<Series>>>

}