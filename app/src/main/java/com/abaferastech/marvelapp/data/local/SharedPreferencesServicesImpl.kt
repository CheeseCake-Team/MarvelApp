package com.abaferastech.marvelapp.data.local

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesServicesImpl: SharedPreferencesServices {

    override fun saveRank(points: Int) {
        sharedPreferences?.edit()?.putInt(PREFS_KEY_POINTS,points)?.apply()
    }

    override fun getRank(): Int {
        return sharedPreferences!!.getInt(PREFS_KEY_POINTS,0)
    }

    companion object{
        var sharedPreferences: SharedPreferences? = null
        fun initSharedPreferences(context: Context){
            sharedPreferences = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)
        }
        const val PREFS_NAME = "shared_preferences"
        const val PREFS_KEY_POINTS = "points"
    }
}