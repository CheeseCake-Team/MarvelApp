package com.abaferastech.marvelapp.data.network

import java.math.BigInteger
import java.security.MessageDigest

class MessageDigest {

    fun hash(timestamp: String, privateKey: String, publicKey: String): String {
        val inputString = timestamp + privateKey + publicKey
        val md = MessageDigest.getInstance(MD5_ALGORITHM).digest(inputString.toByteArray())
        val bigInt = BigInteger(1, md)
        return bigInt.toString(16).padStart(32, '0')
    }

    companion object {
        private const val MD5_ALGORITHM = "MD5"
    }

}