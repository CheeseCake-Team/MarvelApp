package com.abaferastech.marvelapp.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("SEARCH_QUERY_TABLE")
data class SearchQueryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val searchQuery: String
)
