package com.abaferastech.marvelapp.data.local.database.entity.search

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("COMIC_SEARCH_TABLE")
data class ComicSearchEntity(
    @PrimaryKey(false) val id: Int,
    val title: String,
    val issueNumber: Float?,
    val modified: String?,
    val imageUri: String?,
)
