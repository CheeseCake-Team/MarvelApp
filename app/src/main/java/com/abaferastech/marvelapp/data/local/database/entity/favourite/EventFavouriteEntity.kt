package com.abaferastech.marvelapp.data.local.database.entity.favourite

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("EVENT_FAVOURITE_TABLE")
data class EventFavouriteEntity(
    @PrimaryKey(false) val id: Int,
    val title: String?,
    val description: String?,
    val modified: String?,
    val imageUri: String,
    val isFavourite: Boolean? = null,
)