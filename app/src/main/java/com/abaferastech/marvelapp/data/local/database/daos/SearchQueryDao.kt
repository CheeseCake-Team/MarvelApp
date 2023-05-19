package com.abaferastech.marvelapp.data.local.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abaferastech.marvelapp.data.local.database.entity.SearchQueryEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

@Dao
interface SearchQueryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(query: SearchQueryEntity): Completable

    @Delete
    fun delete(searchQueryEntity: SearchQueryEntity): Completable

    @Query("SELECT * FROM SEARCH_QUERY_TABLE WHERE search_query LIKE :query")
    fun getSearchQueryEntityByQuery(query: String): SearchQueryEntity

    @Query("SELECT * FROM SEARCH_QUERY_TABLE")
    fun getAllSearchQueries(): Observable<List<SearchQueryEntity>>
}