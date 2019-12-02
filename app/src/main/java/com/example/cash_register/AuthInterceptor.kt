package com.example.cash_register

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
/*
internal class AuthInterceptor(  private val repository: TokenRepository,
                                 private val authUrl: String):Interceptor{


    override fun intercept(chain: Interceptor.Chain): Response {
        val currentToken = repository.getToken()
        val originalRequest = chain.request()

        return if (!currentToken.transformToLocalEntity().hasExpired()) {
            chain.proceedDeletingTokenOnError(chain.request().newBuilder().addHeaders(currentToken).build())
        } else {
            val refreshTokenRequest =
                originalRequest
                    .newBuilder()
                    .get()
                    .url(authUrl)
                    .addHeaders(currentToken)
                    .build()
            val refreshResponse = chain.proceedDeletingTokenOnError(refreshTokenRequest)

            if (refreshResponse.isSuccessful) {
                val refreshedToken = Gson().fromJson(refreshResponse.body()?.string(),
                    TiendeoTokenRemoteEntity::class.java).transformToDomainEntity(
                    currentToken.thirdParty)
                repository.saveTiendeoToken(refreshedToken)
                val newCall = originalRequest.newBuilder().addHeaders(refreshedToken).build()
                chain.proceedDeletingTokenOnError(newCall)

            } else chain.proceedDeletingTokenOnError(chain.request())
        }

         fun Interceptor.Chain.proceedDeletingTokenOnError(request: Request): Response {
            val response = proceed(request)
            if (response.code() == 401) {
                repository.deleteToken()
            }
            return response
        }

        fun Request.Builder.addHeaders(token: String) =
            this.apply { header("Authorization", "Bearer $token") }
    }
}
*/