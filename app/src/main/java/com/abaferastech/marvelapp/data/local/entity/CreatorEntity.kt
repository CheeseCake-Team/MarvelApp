package com.abaferastech.marvelapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("creator")
data class CreatorEntity(
    @PrimaryKey val id: Int?,
    val fullName: String?,
    val modified: String?,
    val imageUri: String?
)
