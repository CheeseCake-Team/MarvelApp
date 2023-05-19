package com.abaferastech.marvelapp.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    "SEARCH_QUERY_TABLE",
    indices = [Index(value = ["search_query"], unique = true)]
)
data class SearchQueryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,

    @ColumnInfo(name = "search_query")
    val searchQuery: String
)
