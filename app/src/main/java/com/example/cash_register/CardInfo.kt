package com.example.cash_register

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class CardInfo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }
        setContentView(R.layout.activity_card_info)
    }
}

