package com.example.cash_register.dto

data class ArticleDto(val barcode: String,
                      val name: String,
                      var price: Int,
                      val image: String)
