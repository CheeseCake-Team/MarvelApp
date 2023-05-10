package com.abaferastech.marvelapp.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class TYPE: Parcelable {
    COMIC,SERIES,CHARACTER,EVENT,CREATOR
}