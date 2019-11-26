package com.example.cash_register.services

import com.example.cash_register.dto.ArticleDto
 import retrofit2.http.GET
import retrofit2.Call

interface ArticlesService {
    @GET("/articles")
    fun listArticles(): Call<List<ArticleDto>>
}
