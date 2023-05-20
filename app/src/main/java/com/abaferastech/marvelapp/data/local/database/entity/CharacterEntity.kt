package com.abaferastech.marvelapp.data.local.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.util.TableInfo

@Entity("CHARACTER_TABLE")
data class CharacterEntity(
    @PrimaryKey(false) val id: Int,
    val name: String,
    val description: String?,
    val modified: String?,
    val imageUri: String?,
    val isFavourite: Boolean? = null,

)


