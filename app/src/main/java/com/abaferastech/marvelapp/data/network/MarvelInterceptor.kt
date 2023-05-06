package com.abaferastech.marvelapp.data.network

import com.abaferastech.marvelapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response
import java.math.BigInteger
import java.security.MessageDigest
import java.time.Instant

class MarvelInterceptor : Interceptor {

    override fun intercept(chain:Chain): Response {
        val timeStamp = Instant.now().epochSecond.toString()
        val originalRequest = chain.request()
        val originalRequestUrl = originalRequest.url

        val newUrl = originalRequestUrl.newBuilder()
            .scheme("https")
            .addQueryParameter("orderBy", "-modified")
            .addQueryParameter("ts", timeStamp)
            .addQueryParameter("apikey", BuildConfig.lKey)
            .addQueryParameter("hash", generateHash(timeStamp))
            .build()

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

}