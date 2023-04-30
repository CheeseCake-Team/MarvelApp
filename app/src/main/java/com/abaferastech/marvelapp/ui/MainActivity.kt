package com.abaferastech.marvelapp.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.abaferastech.marvelapp.BuildConfig
import java.security.MessageDigest
import java.time.Instant


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

    }


    fun generateHash(): String {
        val timestamp = Instant.now().epochSecond.toString()
        val privateKey = BuildConfig.pKey
        val publicKey = BuildConfig.lKey
        val inputString = timestamp + privateKey + publicKey
        val md = MessageDigest.getInstance("MD5").digest(inputString.toByteArray())
        return md.joinToString("") { "%02x".format(it) }
    }
}