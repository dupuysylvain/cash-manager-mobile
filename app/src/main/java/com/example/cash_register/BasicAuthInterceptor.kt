package com.example.cash_register

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class BasicAuthInterceptor internal constructor(token: String?) : Interceptor {

    private val credentials: String

    init {
        credentials = token.toString()
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .header("Authorization", credentials)
            .build()

        return chain.proceed(request)
    }
}