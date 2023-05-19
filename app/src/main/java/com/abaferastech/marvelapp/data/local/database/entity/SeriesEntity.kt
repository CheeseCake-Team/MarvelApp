package com.abaferastech.marvelapp.data.local.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    "SERIES_TABLE",
    foreignKeys = [ForeignKey(
        entity = SearchQueryEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("searchID"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class SeriesEntity(
    @PrimaryKey(false) val id: Int,
    val title: String,
    val description: String?,
    val startYear: Int,
    val endYear: Int,
    val rating: String?,
    val modified: String?,
    val imageUri: String?,
    var searchID: Long? = null,
    val isFavourite: Boolean? = null,

    )
