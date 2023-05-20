package com.abaferastech.marvelapp.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("CHARACTER_HOME_TABLE")
data class CharacterEntity(
    @PrimaryKey(false) val id: Int,
    val name: String,
    val description: String?,
    val modified: String?,
    val imageUri: String?,
)


