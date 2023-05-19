package com.abaferastech.marvelapp.data.local.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity("EVENT_TABLE",
    foreignKeys = [ForeignKey(
        entity = SearchQueryEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("searchID"),
        onDelete = ForeignKey.CASCADE
    )])
data class EventEntity(
    @PrimaryKey(false) val id: Int,
    val title: String?,
    val description: String?,
    val modified: String?,
    val imageUri: String,
    var searchID: Long? = null,
    val isFavourite: Boolean? = null,

    )
