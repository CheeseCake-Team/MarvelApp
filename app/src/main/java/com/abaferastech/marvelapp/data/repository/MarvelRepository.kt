package com.abaferastech.marvelapp.data.repository

import com.abaferastech.marvelapp.data.model.*
import com.abaferastech.marvelapp.data.model.response.MarvelResponse
import com.abaferastech.marvelapp.data.model.state.State
import com.abaferastech.marvelapp.data.network.MarvelAPI
import com.abaferastech.marvelapp.utils.wrapWithState
import io.reactivex.rxjava3.core.Single

class MarvelRepository {

    fun getAllSeries(): Single<State<MarvelResponse<Series>>> {
        return wrapWithState { MarvelAPI.apiService.getAllSeries() }
    }

    fun getSingleSeries(seriesId: Int): Single<State<MarvelResponse<Series>>> {
        return wrapWithState { MarvelAPI.apiService.getSingleSeries(seriesId) }
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


    fun getAllStories(): Single<State<MarvelResponse<Stories>>> {
        return wrapWithState { MarvelAPI.apiService.getAllStories() }
    }



}