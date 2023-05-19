package com.abaferastech.marvelapp.data.local.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity("CHARACTER_TABLE",
    foreignKeys = [ForeignKey(
        entity = SearchQueryEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("searchID"),
        onDelete = ForeignKey.CASCADE
    )])
data class CharacterEntity(
    @PrimaryKey(false) val id: Int,
    val name: String,
    val description: String?,
    val modified: String?,
    val imageUri: String?,
    val isFavourite: Boolean? = null,
    var searchID: Long? = null

)


