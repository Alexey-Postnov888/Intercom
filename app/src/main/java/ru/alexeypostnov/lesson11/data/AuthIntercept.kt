package ru.alexeypostnov.lesson11.data

import okhttp3.Interceptor
import okhttp3.Response
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class AuthIntercept : Interceptor {
    @OptIn(ExperimentalEncodingApi::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val login = "root"
        val password = "123456"
        val authBase64 = Base64.encode("$login:$password".encodeToByteArray())

        return run {
            val requestWithBasicAuth = originalRequest.newBuilder()
                .header("Authorization", "Basic $authBase64")
                .build()

            chain.proceed(requestWithBasicAuth)
        }
    }
}