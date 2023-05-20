package com.abaferastech.marvelapp.data.local.database.entity.search

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("CHARACTER_SEARCH_TABLE")
data class CharacterSearchEntity(
    @PrimaryKey(false) val id: Int,
    val name: String,
    val modified: String?,
    val imageUri: String?,
)
