package com.abaferastech.marvelapp.data.local.database.entity.search

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("EVENT_SEARCH_TABLE")
data class EventSearchEntity(
    @PrimaryKey(false) val id: Int,
    val title: String?,
    val imageUri: String,
)
