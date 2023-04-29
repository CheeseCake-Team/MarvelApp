package com.abaferastech.marvelapp.view

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
}

fun main() {
    val timestamp = Instant.now().epochSecond.toString()
    val privateKey = BuildConfig.pKey
    val publicKey = BuildConfig.lKey
    val inputString = timestamp + privateKey + publicKey
    val md = MessageDigest.getInstance("MD5")
    val hash = md.digest(inputString.toByteArray())

    val hexString = hash.joinToString("") { "%02x".format(it) }
    println(hexString)
}