package com.example.cash_register.network


import android.content.Context
import com.example.cash_register.BasicAuthInterceptor
import com.example.cash_register.Constants
import com.example.cash_register.Prefs
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HttpClient<T>(context: Context) {
    private var API_URL = "http://192.168.137.1:8080/"
    private val retrofit: Retrofit

    init {
        val context1 = context.applicationContext

        val loggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(
                BasicAuthInterceptor(
                    Prefs.getString(context1, Constants.SHARED_PREFS, Constants.USER_PASSWORD),
                    Prefs.getString(context1, Constants.SHARED_PREFS, Constants.USER_NAME))
            )
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun create(service: Class<T>): T {
        return retrofit.create(service)
    }
}
