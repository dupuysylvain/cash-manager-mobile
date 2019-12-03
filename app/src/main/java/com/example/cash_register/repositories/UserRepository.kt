package com.example.cash_register.repositories

import android.content.Context
import com.example.cash_register.modele.User
import com.example.cash_register.network.HttpClient
import com.example.cash_register.services.UserService
import retrofit2.Call

object UserRepository {

    fun getArticleFromApi(context: Context): Call<User> {
        return HttpClient<UserService>(context).create(UserService::class.java)
            .getArticles()
    }


}
