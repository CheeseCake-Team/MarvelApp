package com.abaferastech.marvelapp.data.repository

import com.abaferastech.marvelapp.data.model.*
import com.abaferastech.marvelapp.data.model.response.MarvelResponse
import com.abaferastech.marvelapp.data.model.state.State
import com.abaferastech.marvelapp.data.network.MarvelAPI
import com.abaferastech.marvelapp.utils.wrapWithState
import io.reactivex.rxjava3.core.Single

class MarvelRepository {



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



}