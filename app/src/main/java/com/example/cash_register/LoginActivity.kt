package com.example.cash_register

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val textNoAccount = findViewById<TextView>(R.id.noAccount)
        val btnSubmit = findViewById<Button>(R.id.buttonSubmit)


        textNoAccount.setOnClickListener {
            //Toast.makeText(this@LoginActivity, "todooo : Go to Sign Up", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent);
        }

        btnSubmit.setOnClickListener {
            Toast.makeText(this@LoginActivity, "todooo : Call the API to /login", Toast.LENGTH_SHORT).show()

        }
    }
}
