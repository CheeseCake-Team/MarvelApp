package com.abaferastech.marvelapp.data.remote

import com.abaferastech.marvelapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response
import java.time.Instant
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val messageDigest:MessageDigest) : Interceptor {

    override fun intercept(chain: Chain): Response {
        val timeStamp = Instant.now().epochSecond.toString()
        val originalRequest = chain.request()
        val originalRequestUrl = originalRequest.url

        val privateKey = BuildConfig.pKey
        val publicKey = BuildConfig.lKey
        val hash =
            messageDigest.hash(
                timestamp = timeStamp,
                privateKey = privateKey,
                publicKey = publicKey
            )

        val newUrl = originalRequestUrl.newBuilder()
            .scheme(HTTPS_SCHEME)
            .addQueryParameter(TIME_STAMP, timeStamp)
            .addQueryParameter(API_KEY, BuildConfig.lKey)
            .addQueryParameter(HASH, hash)
            .build()

        val newRequest = originalRequest
            .newBuilder()
            .url(newUrl)
            .build()
        return chain.proceed(newRequest)
    }

    companion object {
        private const val TIME_STAMP = "ts"
        private const val API_KEY = "apikey"
        private const val HASH = "hash"
        private const val HTTPS_SCHEME = "https"
    }
}