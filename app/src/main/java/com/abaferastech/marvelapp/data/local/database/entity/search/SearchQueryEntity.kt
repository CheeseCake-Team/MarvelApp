package com.abaferastech.marvelapp.data.local.database.entity.search

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    "SEARCH_QUERY_TABLE",
    indices = [Index(value = ["searchQuery"], unique = true)]
)
data class SearchQueryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val searchQuery: String
)
