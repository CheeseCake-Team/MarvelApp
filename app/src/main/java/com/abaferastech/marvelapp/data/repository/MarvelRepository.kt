package com.abaferastech.marvelapp.data.repository

import android.util.Log
import com.abaferastech.marvelapp.data.model.response.MarvelBaseResponse
import com.abaferastech.marvelapp.data.model.result.*
import com.abaferastech.marvelapp.ui.model.UIState
import com.abaferastech.marvelapp.data.network.MarvelAPI
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

class MarvelRepository {

    fun searchInComics(query: String): Single<UIState<List<Comics>>> {
        return wrapWithState { MarvelAPI.apiService.searchInComics(query) }
    }

    fun getSingleCharacter(characterId: Int): Single<UIState<Characters>> {
        return wrapWithState { MarvelAPI.apiService.getSingleCharacter(characterId) }.mapListToSingleItem()
    }

    fun getSingleEvent(eventsId: Int): Single<UIState<Events>> {
        return wrapWithState { MarvelAPI.apiService.getEventsById(eventsId) }.mapListToSingleItem()
    }

    fun getSingleSeries(seriesId: Int): Single<UIState<Series>> {
        return wrapWithState { MarvelAPI.apiService.getSingleSeries(seriesId) }.mapListToSingleItem()
    }

    fun getSingleComic(comicsId: Int): Single<UIState<Comics>> {
        return wrapWithState { MarvelAPI.apiService.getSingleComic(comicsId) }.mapListToSingleItem()
    }

    fun getSingleCreator(creatorId: Int): Single<UIState<Creators>> {
        return wrapWithState { MarvelAPI.apiService.getSingleCreator(creatorId) }.mapListToSingleItem()
    }

    fun getAllEvents(): Single<UIState<List<Events>>> {
        return wrapWithState { MarvelAPI.apiService.getAllEvents() }
    }

    fun getAllCharacters(): Single<UIState<List<Characters>>> {
        return wrapWithState { MarvelAPI.apiService.getAllCharacters() }
    }

    fun getAllSeries(): Single<UIState<List<Series>>> {
        return wrapWithState { MarvelAPI.apiService.getAllSeries() }
    }

    fun getAllComics(): Single<UIState<List<Comics>>> {
        return wrapWithState { MarvelAPI.apiService.getAllComics() }
    }

    fun getAllCreators(): Single<UIState<List<Creators>>> {
        return wrapWithState { MarvelAPI.apiService.getAllCreators() }
    }


    fun getEventComics(eventId: Int): Single<UIState<List<Comics>>> {
        return wrapWithState { MarvelAPI.apiService.getEventComics(eventId) }
    }


    fun getCharacterEvents(characterId: Int): Single<UIState<List<Events>>> {
        return wrapWithState { MarvelAPI.apiService.getCharacterEvents(characterId) }
    }

    fun getCharacterComics(characterId: Int): Single<UIState<List<Comics>>> {
        return wrapWithState { MarvelAPI.apiService.getCharacterComics(characterId) }
    }


    fun getSeriesFullUrl(fullUrl: String): Single<UIState<List<Series>>> {
        return wrapWithState { MarvelAPI.apiService.getSeriesFullUrl(fullUrl) }
    }

    fun getSeriesComics(seriesId: Int): Single<UIState<List<Comics>>> {
        return wrapWithState { MarvelAPI.apiService.getSeriesComics(seriesId) }
    }

    fun getSeriesEvents(seriesId: Int): Single<UIState<List<Events>>> {
        return wrapWithState { MarvelAPI.apiService.getSeriesEvents(seriesId) }
    }

    fun getSeriesCharacters(seriesId: Int): Single<UIState<List<Characters>>> {
        return wrapWithState { MarvelAPI.apiService.getSeriesCharacters(seriesId) }
    }


    fun getSeriesCreators(seriesId: Int): Single<UIState<List<Creators>>> {
        return wrapWithState { MarvelAPI.apiService.getSeriesCreators(seriesId) }
    }


    fun getComicEvents(comicsId: Int): Single<UIState<List<Events>>> {
        return wrapWithState { MarvelAPI.apiService.getComicEvents(comicsId) }
    }

    fun getComicCharacters(comicsId: Int): Single<UIState<List<Characters>>> {
        return wrapWithState { MarvelAPI.apiService.getComicCharacters(comicsId) }
    }

    fun getComicSeries(comicsId: Int): Single<UIState<List<Series>>> {
        return wrapWithState { MarvelAPI.apiService.getComicSeries(comicsId) }
    }

    fun getComicCreators(comicsId: Int): Single<UIState<List<Creators>>> {
        return wrapWithState { MarvelAPI.apiService.getComicCreators(comicsId) }
    }


    fun getCreatorEvents(creatorId: Int): Single<UIState<List<Events>>> {
        return wrapWithState { MarvelAPI.apiService.getCreatorEvents(creatorId) }
    }

    fun getCreatorCharacters(creatorId: Int): Single<UIState<List<Characters>>> {
        return wrapWithState { MarvelAPI.apiService.getCreatorCharacters(creatorId) }
    }

    fun getCreatorComics(creatorId: Int): Single<UIState<List<Comics>>> {
        return wrapWithState { MarvelAPI.apiService.getCreatorComics(creatorId) }
    }

    fun getCreatorSeries(creatorId: Int): Single<UIState<List<Series>>> {
        return wrapWithState { MarvelAPI.apiService.getCreatorSeries(creatorId) }
    }


    fun getStoryEvents(storyId: Int): Single<UIState<List<Events>>> {
        return wrapWithState { MarvelAPI.apiService.getStoryEvents(storyId) }
    }

    fun getStoryCharacters(storyId: Int): Single<UIState<List<Characters>>> {
        return wrapWithState { MarvelAPI.apiService.getStoryCharacters(storyId) }
    }

    fun getStoryComics(storyId: Int): Single<UIState<List<Comics>>> {
        return wrapWithState { MarvelAPI.apiService.getStoryComics(storyId) }
    }

    fun getStoryCreators(storyId: Int): Single<UIState<List<Creators>>> {
        return wrapWithState { MarvelAPI.apiService.getStoryCreators(storyId) }
    }

    fun getStorySeries(storyId: Int): Single<UIState<List<Series>>> {
        return wrapWithState { MarvelAPI.apiService.getStorySeries(storyId) }
    }


    private fun <T> wrapWithState(function: () -> Single<Response<MarvelBaseResponse<T>>>):
            Single<UIState<List<T>>> {
        return function().map {
            if (it.isSuccessful) {
                UIState.Success(it.body()?.data?.results)
            } else {
                UIState.Error(it.message())
            }
        }
    }

    private fun <T> Single<UIState<List<T>>>.mapListToSingleItem(): Single<UIState<T>> {
        return this.map { state ->
            when (state) {
                is UIState.Success -> {
                    val firstItem = state.data?.firstOrNull()
                    if (firstItem != null) {
                        UIState.Success(firstItem)
                    } else {
                        UIState.Error("No results found.")
                    }
                }
                is UIState.Error -> {
                    state
                }
                is UIState.Loading -> {
                    state
                }
            }
        }
    }


}