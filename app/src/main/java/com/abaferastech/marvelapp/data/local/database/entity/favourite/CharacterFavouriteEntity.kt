package com.abaferastech.marvelapp.data.local.database.entity.favourite

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("CHARACTER_FAVOURITE_TABLE")
data class CharacterFavouriteEntity(
    @PrimaryKey(false) val id: Int,
    val name: String,
    val description: String?,
    val modified: String?,
    val imageUri: String?,
    val isFavourite: Boolean? = null,
)


