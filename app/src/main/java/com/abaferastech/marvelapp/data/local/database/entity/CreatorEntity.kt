package com.abaferastech.marvelapp.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("CREATOR_TABLE")
data class CreatorEntity(
    @PrimaryKey val id: Int?,
    val fullName: String?,
    val modified: String?,
    val imageUri: String?
)
