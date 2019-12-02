package com.example.cash_register.Fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.cash_register.Constants
import com.example.cash_register.Prefs
import com.example.cash_register.view.SignUpActivity
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
 import com.example.cash_register.MainActivity
import com.example.cash_register.R


class FragmentLogin : Fragment() {

    private lateinit var username: EditText
    private lateinit var password: EditText


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_login, container, false)

        username = view.findViewById(R.id.username)
        password = view.findViewById(R.id.password)

        val textNoAccount = view.findViewById<TextView>(R.id.noAccount)
        val btnSubmit = view.findViewById<Button>(R.id.buttonSubmit)
        textNoAccount.setOnClickListener {
            val intent = Intent(this.requireContext(), SignUpActivity::class.java)
            startActivity(intent)
        }
        btnSubmit.setOnClickListener {
            sendLoginRequest()
        }

        return view
    }

    @Throws(IOException::class)
    fun sendLoginRequest() {
        val client = OkHttpClient()
        val body = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("username", username.text.toString())
            .addFormDataPart("password", password.text.toString())

            .build()

        val request = Request.Builder()
            .url(Prefs.getApiUrl(this.requireContext()) + "/auth/authenticate")
            .post(body)
            .addHeader("Content-Type", "application/json")
            .build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                Log.d("error", e.message)
                handleLoginError()
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                var token = response.header("Authorization").toString()
                handleLogin(token)
            }
        })
    }

    fun handleLogin(token: String) {
        Prefs.setString(requireContext(), Constants.SHARED_PREFS, Constants.TOKEN, token)
        activity!!.runOnUiThread(Runnable {
            Toast.makeText(
                requireContext(),
                "Authentification effectuée avec succès.",
                Toast.LENGTH_SHORT
            ).show()
        })
        val i = Intent(activity, MainActivity::class.java)
        startActivity(i)
    }


    fun handleLoginError() {
        activity!!.runOnUiThread(Runnable {
            Toast.makeText(
                requireContext(),
                "Erreur lors de la tentative de connexion",
                Toast.LENGTH_SHORT
            ).show()
        })
    }
}
