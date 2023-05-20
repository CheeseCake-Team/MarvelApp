package com.abaferastech.marvelapp.data.local.database.entity.favourite

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("SERIES_FAVOURITE_TABLE")
data class SeriesFavouriteEntity(
    @PrimaryKey(false) val id: Int,
    val title: String,
    val description: String?,
    val startYear: Int,
    val endYear: Int?,
    val rating: String?,
    val modified: String?,
    val imageUri: String?,
    val isFavourite: Boolean? = null,
)
