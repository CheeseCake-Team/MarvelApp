package com.abaferastech.marvelapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("series")
data class SeriesEntity(
    @PrimaryKey(false) val id: Int,
    val title: String,
    val description: String?,
    val startYear: Int,
    val endYear: Int,
    val rating: String?,
    val modified: String?,
    val imageUri: String?
)
