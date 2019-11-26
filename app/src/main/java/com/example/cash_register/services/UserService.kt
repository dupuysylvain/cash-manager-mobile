package com.example.cash_register.services

import com.example.cash_register.dto.UserAuthenticationDto
import com.example.cash_register.modele.User
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.POST
import retrofit2.http.FormUrlEncoded




interface UserService {
    @POST("auth/authenticate")
    fun login(@Body userAuthenticationDto: UserAuthenticationDto): Call<User>


    @get:GET("users/me")
    val currentUserInformations: Call<User>

    @Headers("Access-Control-Allow-Origin: *")
    @POST("auth/authenticate")
    fun getToken(@Body userAuthenticationDto: UserAuthenticationDto): Call<User>

    @POST("/auth/authenticate")
    fun register(@Body userAuthenticationDto: UserAuthenticationDto): Call<User>

    @GET("api/users/me")
    fun getUserid(@Path("id") id: String, @Header("Authorization") authHeader: String): Call<User>

    @DELETE("/api/users")
    fun deleteCurrentUser(): Call<Void>

    @GET("/api/users")
    fun getProfile(): Call<User>

    @Headers("Content-Type: application/json")
    @POST("auth/authenticate")
    fun getAuth(@Body body: String): Call<User>

    @POST("/auth/authenticate")
    fun basicLogin(): Call<User>


    @POST("/auth/authenticate")
    @FormUrlEncoded
    fun refreshToken(@Field("username") username: String,@Field("password")
    password: String, @Field("grant_type") grant_type: String): Call<User>
}
