package com.abaferastech.marvelapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("character")
data class CharacterEntity(
    @PrimaryKey(false) val id: Int,
    val name: String,
    val description: String?,
    val modified: String?,
    val imageUri: String?
)
