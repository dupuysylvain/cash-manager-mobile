package com.example.cash_register.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
 import com.example.cash_register.Nfc.NfcReader
import com.example.cash_register.QRcode.Qrcode
import com.example.cash_register.R


class ChoosePayement : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }

         setContentView(R.layout.activity_choose_payement)

        val btn:ImageButton = findViewById(R.id.btn_nfc)
        btn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, NfcReader::class.java)
            startActivity(intent) })

            val btn2:ImageButton = findViewById(R.id.btn_qr)
            btn2.setOnClickListener(View.OnClickListener {
                val intent = Intent(this, Qrcode::class.java)
                startActivity(intent) })
        }
    }



