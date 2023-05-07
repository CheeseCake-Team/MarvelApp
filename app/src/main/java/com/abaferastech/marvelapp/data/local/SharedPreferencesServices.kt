package com.abaferastech.marvelapp.data.local

interface SharedPreferencesServices {
    fun saveRank(points: Int)
    fun getRank(): Int
}