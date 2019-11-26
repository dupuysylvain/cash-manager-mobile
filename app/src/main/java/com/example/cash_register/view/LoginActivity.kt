package com.example.cash_register.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.cash_register.AuthenticationValidator
import com.example.cash_register.Constants
import com.example.cash_register.Prefs
import com.example.cash_register.R
import com.example.cash_register.dto.UserAuthenticationDto
import com.example.cash_register.modele.User
import com.example.cash_register.network.HttpClient
import com.example.cash_register.services.UserService
import okhttp3.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

import java.io.IOException


class LoginActivity : AppCompatActivity() {


    private lateinit var username: EditText
    private lateinit var password: EditText
    private var token: String = ""
    private var API_URL = "http://192.168.137.1:8080/"
    private val USERNAME_ALREADY_USED_CODE = 409


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)

        val textNoAccount = findViewById<TextView>(R.id.noAccount)
        val btnSubmit = findViewById<Button>(R.id.buttonSubmit)
        textNoAccount.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
        btnSubmit.setOnClickListener {
            sendRegisterRequest()
        }
    }

  /*  fun login() {
        val loginValidator = AuthenticationValidator(username, password)
        if (loginValidator.areLoginCredentialsValid()) {
            sendLoginRequest()
        } else {
            loginValidator.displayCredentialsErrorOnEditText()
        }
    }*/



   /* private fun sendLoginRequest() {
        val userAuthenticationDto = UserAuthenticationDto(
            username.getText().toString(),
            password.getText().toString()
        )
        val loginCall = HttpClient<UserService>(this@LoginActivity).create(UserService::class.java)
            .login(userAuthenticationDto)

        loginCall.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                handleLoginResponse(response)
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                handleLoginFailure(t)
            }
        })
    }*/


    private fun sendRegisterRequest() {
        val userRegisterDto = UserAuthenticationDto(
            password.getText().toString(),
                    username.getText().toString()
        )
        val registerCall =
            HttpClient<UserService>(this@LoginActivity).create(UserService::class.java)
                .register(userRegisterDto)

        registerCall.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                handleRegisterResponse(response)
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                handleRegisterFailure(t)
            }
        })
    }


    private fun handleRegisterResponse(response: Response<User>) {
        if (response.isSuccessful) {
             Toast.makeText(
                applicationContext,
                "Création effectuée avec succès.",
                Toast.LENGTH_SHORT
            ).show()
            finish()
        } else {
             if (response.code() == USERNAME_ALREADY_USED_CODE) {
                Toast.makeText(
                    applicationContext,
                    "username est déjà utilisée par un autre compte.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    applicationContext,
                    "Une erreur est survenue lors de l'authentification.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun handleRegisterFailure(t: Throwable) {
        Toast.makeText(
            applicationContext,
            "Erreur lors de la tentative de connexion",
            Toast.LENGTH_SHORT
        ).show()
        Timber.e(t)
     }

    /*
    private fun handleLoginResponse(response: Response<User>) {
        if (response.isSuccessful) {
            Prefs.setBoolean(applicationContext, Constants.SHARED_PREFS, Constants.IS_LOGGED, true)
            Prefs.setString(
                applicationContext,
                Constants.SHARED_PREFS,
                Constants.USER_NAME,
                username.getText().toString()
            )
            Prefs.setString(
                applicationContext,
                Constants.SHARED_PREFS,
                Constants.USER_PASSWORD,
                password.getText().toString()
            )
            startActivity(Intent(applicationContext, SplashScreen::class.java))
            finish()
        } else {
            Timber.e(response.toString())
            Toast.makeText(
                applicationContext,
                "sign in error",
                Toast.LENGTH_LONG
            ).show()
         }
    }

    private fun handleLoginFailure(t: Throwable) {
        Toast.makeText(
            applicationContext,
            "Erreur lors de la tentative de connexion",
            Toast.LENGTH_SHORT
        ).show()
        Timber.e(t)
     }

*/

}
