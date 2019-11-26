package com.example.cash_register

 /*
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.cash_register.R
import com.example.cash_register.dto.UserAuthenticationDto
import com.example.cash_register.modele.User
import com.example.cash_register.network.HttpClient
import com.example.cash_register.services.Session
import com.example.cash_register.services.UserService
import okhttp3.*

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.POST
import java.io.IOException


class LoginActivity : AppCompatActivity() {


    private lateinit var username: EditText
    private lateinit var password: EditText
    private var token: String = ""
    private var API_URL = "http://192.168.137.1:8080/"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)

        val textNoAccount = findViewById<TextView>(R.id.noAccount)
        val btnSubmit = findViewById<Button>(R.id.buttonSubmit)
        textNoAccount . setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
        btnSubmit.setOnClickListener {
            sendLoginRequest()
        }
    }

    @Throws(IOException::class)
    fun sendLoginRequest(): String {
        val client = OkHttpClient()
        val body = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("username", username.text.toString())
            .addFormDataPart("password", password.text.toString())
            .build()

        val request = Request.Builder()
            .url(API_URL + "auth/authenticate")
            .post(body)
            .addHeader("Content-Type", "application/json")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                Log.d("error", e.message)
            }
            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                token = response.header("Authorization").toString()
                Log.d("********* token", token)
            }
        })
        return token
    }


}*/