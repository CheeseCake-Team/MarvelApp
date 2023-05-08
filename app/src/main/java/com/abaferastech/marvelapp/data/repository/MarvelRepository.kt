package com.abaferastech.marvelapp.data.repository

import android.util.Log
import com.abaferastech.marvelapp.data.model.*
import com.abaferastech.marvelapp.data.model.response.MarvelResponse
import com.abaferastech.marvelapp.data.model.state.State
import com.abaferastech.marvelapp.data.network.MarvelAPI
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class MarvelRepository {

    fun getHomeData(): Observable<Triple<State<MarvelResponse<Characters>>,
            State<MarvelResponse<Comics>>,
            State<MarvelResponse<Series>>>> {
        return Observable.zip(
            getAllCharacters().toObservable(),
            getAllComics().toObservable(),
            getAllSeries().toObservable()
        ) { characters, comics, series ->
            Triple(characters, comics, series)
        }
    }

    fun searchInComics(query: String): Single<State<MarvelResponse<Comics>>> {
        return wrapWithState { MarvelAPI.apiService.searchInComics(query) }
    }
    fun getSingleCharacter(characterId: Int): Single<State<MarvelResponse<Characters>>> {
        // Log.i("characterssssssssssssssssss", "getSingleCharacter: ${MarvelAPI.apiService.getSingleCharacter(creatorId)}")
        return wrapWithState { MarvelAPI.apiService.getSingleCharacter(characterId) }
    }

    fun getEventsById(eventsId: Int): Single<State<MarvelResponse<Events>>> {
         Log.i("characterssssssssssssssssss", "events: ${MarvelAPI.apiService.getEventsById(eventsId).subscribe{t-> t.isSuccessful.toString()}}")
        return wrapWithState { MarvelAPI.apiService.getEventsById(eventsId) }
    }


    fun getAllEvents(): Single<State<MarvelResponse<Events>>> {
        return wrapWithState { MarvelAPI.apiService.getAllEvents() }
    }

    fun getAllCharacters(): Single<State<MarvelResponse<Characters>>> {
        return wrapWithState { MarvelAPI.apiService.getAllCharacters() }
    }

    fun getAllSeries(): Single<State<MarvelResponse<Series>>> {
        return wrapWithState { MarvelAPI.apiService.getAllSeries() }
    }

    fun getSingleSeries(seriesId: Int): Single<State<MarvelResponse<Series>>> {
        return wrapWithState { MarvelAPI.apiService.getSingleSeries(seriesId) }
    }

    fun getSeriesFullUrl(fullUrl: String): Single<State<MarvelResponse<Series>>> {
        return wrapWithState { MarvelAPI.apiService.getSeriesFullUrl(fullUrl) }
    }

    fun getSeriesComics(seriesId: Int): Single<State<MarvelResponse<Comics>>> {
        return wrapWithState { MarvelAPI.apiService.getSeriesComics(seriesId) }
    }

    fun getSeriesEvents(seriesId: Int): Single<State<MarvelResponse<Events>>> {
        return wrapWithState { MarvelAPI.apiService.getSeriesEvents(seriesId) }
    }

    fun getSeriesCharacters(seriesId: Int): Single<State<MarvelResponse<Characters>>> {
        return wrapWithState { MarvelAPI.apiService.getSeriesCharacters(seriesId) }
    }

    fun getSeriesStories(seriesId: Int): Single<State<MarvelResponse<Stories>>> {
        return wrapWithState { MarvelAPI.apiService.getSeriesStories(seriesId) }
    }

    fun getSeriesCreators(seriesId: Int): Single<State<MarvelResponse<Creators>>> {
        return wrapWithState { MarvelAPI.apiService.getSeriesCreators(seriesId) }
    }

    fun getAllComics(): Single<State<MarvelResponse<Comics>>> {
        return wrapWithState { MarvelAPI.apiService.getAllComics() }
    }

    fun getSingleComic(comicsId: Int): Single<State<MarvelResponse<Comics>>> {
        return wrapWithState { MarvelAPI.apiService.getSingleComic(comicsId) }
    }

    fun getComicEvents(comicsId: Int): Single<State<MarvelResponse<Events>>> {
        return wrapWithState { MarvelAPI.apiService.getComicEvents(comicsId) }
    }

    fun getComicCharacters(comicsId: Int): Single<State<MarvelResponse<Characters>>> {
        return wrapWithState { MarvelAPI.apiService.getComicCharacters(comicsId) }
    }

    fun getComicSeries(comicsId: Int): Single<State<MarvelResponse<Series>>> {
        return wrapWithState { MarvelAPI.apiService.getComicSeries(comicsId) }
    }

    fun getComicCreators(comicsId: Int): Single<State<MarvelResponse<Creators>>> {
        return wrapWithState { MarvelAPI.apiService.getComicCreators(comicsId) }
    }

    fun getComicStories(comicsId: Int): Single<State<MarvelResponse<Stories>>> {
        return wrapWithState { MarvelAPI.apiService.getComicStories(comicsId) }
    }

    fun getAllCreators(): Single<State<MarvelResponse<Creators>>> {
        return wrapWithState { MarvelAPI.apiService.getAllCreators() }
    }

    fun getSingleCreator(creatorId: Int): Single<State<MarvelResponse<Creators>>> {
        return wrapWithState { MarvelAPI.apiService.getSingleCreator(creatorId) }
    }

    fun getCreatorEvents(creatorId: Int): Single<State<MarvelResponse<Events>>> {
        return wrapWithState { MarvelAPI.apiService.getCreatorEvents(creatorId) }
    }

    fun getCreatorCharacters(creatorId: Int): Single<State<MarvelResponse<Characters>>> {
        return wrapWithState { MarvelAPI.apiService.getCreatorCharacters(creatorId) }
    }

    fun getCreatorComics(creatorId: Int): Single<State<MarvelResponse<Comics>>> {
        return wrapWithState { MarvelAPI.apiService.getCreatorComics(creatorId) }
    }

    fun getCreatorSeries(creatorId: Int): Single<State<MarvelResponse<Series>>> {
        return wrapWithState { MarvelAPI.apiService.getCreatorSeries(creatorId) }
    }

    fun getCreatorStories(creatorId: Int): Single<State<MarvelResponse<Stories>>> {
        return wrapWithState { MarvelAPI.apiService.getCreatorStories(creatorId) }
    }

    fun getAllStories(): Single<State<MarvelResponse<Stories>>> {
        return wrapWithState { MarvelAPI.apiService.getAllStories() }
    }

    fun getSingleStory(storyId: Int): Single<State<MarvelResponse<Stories>>> {
        return wrapWithState { MarvelAPI.apiService.getSingleStory(storyId) }
    }

    fun getStoryEvents(storyId: Int): Single<State<MarvelResponse<Events>>> {
        return wrapWithState { MarvelAPI.apiService.getStoryEvents(storyId) }
    }

    fun getStoryCharacters(storyId: Int): Single<State<MarvelResponse<Characters>>> {
        return wrapWithState { MarvelAPI.apiService.getStoryCharacters(storyId) }
    }

    fun getStoryComics(storyId: Int): Single<State<MarvelResponse<Comics>>> {
        return wrapWithState { MarvelAPI.apiService.getStoryComics(storyId) }
    }

    fun getStoryCreators(storyId: Int): Single<State<MarvelResponse<Creators>>> {
        return wrapWithState { MarvelAPI.apiService.getStoryCreators(storyId) }
    }

    fun getStorySeries(storyId: Int): Single<State<MarvelResponse<Series>>> {
        return wrapWithState { MarvelAPI.apiService.getStorySeries(storyId) }
    }
}