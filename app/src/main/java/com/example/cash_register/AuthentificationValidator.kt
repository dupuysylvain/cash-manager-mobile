package com.example.cash_register

import android.text.TextUtils
import android.util.Log
import android.widget.EditText

class AuthenticationValidator(
    private val usernameEditText: EditText,
    private val passwordEditText: EditText
) {
    private val username: String
    private val password: String

    init {
        this.username = usernameEditText.text.toString()
        this.password = passwordEditText.text.toString()
    }

    fun areLoginCredentialsValid(): Boolean {
        return !TextUtils.isEmpty(username) && !TextUtils.isEmpty(password) && isUsername(username)
    }

    fun displayCredentialsErrorOnEditText() {
        if (TextUtils.isEmpty(username)) {
            usernameEditText.error = "Veuillez entrer un username."
        } else if (!isUsername(username)) {
            usernameEditText.error = "Le format du username n'est pas correct."
        }

        if (TextUtils.isEmpty(password)) {
            passwordEditText.error = "Veuillez entrer un mot de passe."
        } else if (password.length < 6) {
            passwordEditText.error = "Veuillez entrer un mot de passe d'au moins 6 caractères."
        }
    }

    private fun isUsername(username: String): Boolean {
        Log.d("isUsername", username)
        return true
    }
}