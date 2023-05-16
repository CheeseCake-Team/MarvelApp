package com.abaferastech.marvelapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("EVENT_TABLE")
data class EventEntity(
    @PrimaryKey(false) val id: Int,
    val title: String?,
    val description: String?,
    val modified: String?,
    val imageUri: String
)
