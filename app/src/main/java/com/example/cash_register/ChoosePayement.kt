package com.example.cash_register

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import android.widget.ImageButton

import android.nfc.NfcManager
import com.example.cash_register.Nfc.NfcReader


class ChoosePayement : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }

        val manager = this.getSystemService(Context.NFC_SERVICE) as NfcManager
        val adapter = manager.defaultAdapter
        if (adapter != null && adapter.isEnabled) {
            // adapter exists and is enabled.
        setContentView(R.layout.activity_choose_payement)

        val btn:ImageButton = findViewById(R.id.btn_nfc)
        btn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, NfcReader::class.java)
            startActivity(intent) })
        }
        else {
            startNfcSettingsActivity()
        }
    }


    private fun startNfcSettingsActivity() {
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            startActivity(Intent(android.provider.Settings.ACTION_NFC_SETTINGS))
        } else {
            startActivity(Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS))
        }
    }
}
