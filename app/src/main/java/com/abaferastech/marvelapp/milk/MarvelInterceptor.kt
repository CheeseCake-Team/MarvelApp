package com.abaferastech.marvelapp.milk

import android.util.Log
import com.abaferastech.marvelapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response
import java.math.BigInteger
import java.security.MessageDigest
import java.time.Instant

class MarvelQueryParametersInterceptor : Interceptor {

    override fun intercept(chain:Chain): Response {
        val timeStamp = Instant.now().epochSecond.toString()

        val originalRequest = chain.request()
        val originalRequestUrl = originalRequest.url

        Log.i("aliiiiiiiiiiiiiiiiiiii", "intercept: $timeStamp")
        Log.i("aliiiiiiiiiiiiiiiiiiii", "intercept: ${generateHash(timeStamp)}")
        val newUrl = originalRequestUrl.newBuilder()
            .addQueryParameter("ts", timeStamp)
            .addQueryParameter("apikey", BuildConfig.lKey)
            .addQueryParameter("hash", generateHash(timeStamp))
            .build()
        Log.i("aliiiiiiiiiiiiiiiiiiii", "intercept: $newUrl")

        val newRequest = originalRequest
            .newBuilder()
            .url(newUrl)
            .build()
        return chain.proceed(newRequest)
    }

    private fun generateHash(timestamp:String): String {
        val privateKey = BuildConfig.pKey
        val publicKey = BuildConfig.lKey
        val inputString = timestamp + privateKey + publicKey
        val md = MessageDigest.getInstance("MD5").digest(inputString.toByteArray())
        val bigInt = BigInteger(1, md)
        return bigInt.toString(16).padStart(32, '0')
    }

//    fun calculateMd5(text: String): String {
//        val privateKey = BuildConfig.pKey
//        val publicKey = BuildConfig.lKey
//        val messageDigest = MessageDigest.getInstance("MD5")
//        val digest = messageDigest.digest(text.toByteArray())
//        val bigInt = BigInteger(1, digest)
//        return bigInt.toString(16).padStart(32, '0')
//    }


}