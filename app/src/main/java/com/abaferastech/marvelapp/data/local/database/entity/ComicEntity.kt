package com.abaferastech.marvelapp.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("COMIC_TABLE")
data class ComicEntity(
    @PrimaryKey(false) val id: Int,
    val title: String,
    val description: String?,
    val issueNumber: Float?,
    val price: Float?,
    val pageCount: Int?,
    val modified: String?,
    val imageUri: String?,
    val isFavourite: Boolean? = null,
    )
