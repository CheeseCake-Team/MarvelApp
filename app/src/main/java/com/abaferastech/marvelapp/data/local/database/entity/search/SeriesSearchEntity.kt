package com.abaferastech.marvelapp.data.local.database.entity.search

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("SERIES_SEARCH_TABLE")
data class SeriesSearchEntity(
    @PrimaryKey(false) val id: Int,
    val title: String,
    val startYear: Int,
    val modified: String?,
    val imageUri: String?,
)
