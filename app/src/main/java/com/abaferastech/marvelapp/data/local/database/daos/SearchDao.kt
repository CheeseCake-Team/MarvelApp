package com.abaferastech.marvelapp.data.local.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abaferastech.marvelapp.data.local.database.entity.SearchQueryEntity
import com.abaferastech.marvelapp.data.local.database.entity.search.CharacterSearchEntity
import com.abaferastech.marvelapp.data.local.database.entity.search.ComicSearchEntity
import com.abaferastech.marvelapp.data.local.database.entity.search.EventSearchEntity
import com.abaferastech.marvelapp.data.local.database.entity.search.SeriesSearchEntity
import io.reactivex.rxjava3.core.Observable

@Dao
interface SearchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearchQuery(query: SearchQueryEntity)

    @Delete
    fun deleteSearchQuery(searchQueryEntity: SearchQueryEntity)

    @Query("SELECT * FROM SEARCH_QUERY_TABLE WHERE searchQuery LIKE :query")
    fun getSearchQueryEntityByQuery(query: String): SearchQueryEntity

    @Query("SELECT * FROM SEARCH_QUERY_TABLE")
    fun getAllSearchQueries(): Observable<List<SearchQueryEntity>>


    @Query("SELECT * FROM CHARACTER_SEARCH_TABLE WHERE name LIKE '%' || :name || '%' ")
    fun getSearchedCharacter(name: String): Observable<List<CharacterSearchEntity>>

    @Query("SELECT * FROM COMIC_SEARCH_TABLE WHERE title LIKE '%' || :title || '%' ")
    fun getSearchedComics(title: String): Observable<List<ComicSearchEntity>>

    @Query("SELECT * FROM SERIES_SEARCH_TABLE WHERE title LIKE '%' || :title || '%' ")
    fun getSearchedSeries(title: String): Observable<List<SeriesSearchEntity>>

    @Query("SELECT * FROM EVENT_SEARCH_TABLE WHERE title LIKE '%' || :title || '%' ")
    fun getSearchedEvents(title: String): Observable<List<EventSearchEntity>>

}