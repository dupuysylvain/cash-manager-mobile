package com.example.cash_register.Nfc

import android.content.Context
import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.NfcManager
import android.nfc.Tag
import android.nfc.tech.IsoDep
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.cash_register.Constants
import com.example.cash_register.PaymentSuccessActivity
import com.example.cash_register.Prefs
import com.example.cash_register.R
import com.example.cash_register.modele.ServerError
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_nfc_reader.*
import okhttp3.*
import java.io.IOException


class NfcReader : AppCompatActivity(), NfcAdapter.ReaderCallback  {
    private var nfcAdapter: NfcAdapter? = null
    private lateinit var purchaseBtn: Button
    private var nfcId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nfc_reader)
        val manager = this.getSystemService(Context.NFC_SERVICE) as NfcManager
        val adapter = manager.defaultAdapter
        if (adapter != null && adapter.isEnabled) {
            purchaseBtn = findViewById(R.id.purchaseBtn)
            purchaseBtn.isEnabled = false

            purchaseBtn.setOnClickListener { purchaseCart() }

            nfcAdapter = NfcAdapter.getDefaultAdapter(this)
            av_from_code.setAnimation("nfc_payment2.json")
            av_from_code.playAnimation()
            av_from_code.loop(true)
        }else {
            startNfcSettingsActivity()
        }
    }

    public override fun onResume() {
        super.onResume()
        nfcAdapter?.enableReaderMode(this, this,
            NfcAdapter.FLAG_READER_NFC_A or
                    NfcAdapter.FLAG_READER_NFC_B or
                    NfcAdapter.FLAG_READER_NFC_F or
                    NfcAdapter.FLAG_READER_NFC_V or
                    NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK, null)
    }

    public override fun onPause() {
        super.onPause()
        nfcAdapter?.disableReaderMode(this)
    }

    override fun onTagDiscovered(tag: Tag?) {

        Log.i("NFC", tag!!.id.toString())

        val isoDep = IsoDep.get(tag)
        isoDep.connect()
        runOnUiThread {
            av_from_code.visibility = View.GONE
            purchaseBtn.isEnabled = true
            isoDep.close()
        }

        nfcId = Utils.toHex(isoDep.tag.id)
        Log.i("NFC", nfcId)
    }

    private fun purchaseCart() {

        Log.d("NFC","Purchase with CreditCard : " + nfcId)

        val client = OkHttpClient()

        val body = RequestBody.create(null, byteArrayOf())

        val request = Request.Builder()
            .url(Prefs.getApiUrl(applicationContext) + "/api/payment/purchase/creditCard/"+nfcId)
            .post(body)
            .header(
                "Authorization",
                Prefs.getString(applicationContext, Constants.SHARED_PREFS, Constants.TOKEN)
            )
            .build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                Log.d("error", e.message)
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                response.use {
                    if(it.isSuccessful) {
                        Log.d("OK: ", it.body()!!.string())
                        handlePurchase()
                    } else {
                        val error = Gson().fromJson(response.body()!!.string(),  ServerError::class.java)

                        runOnUiThread {
                            Toast.makeText(
                                applicationContext,
                                error.message,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }

            }
        })
    }

    private fun handlePurchase() {
        val intent = Intent(applicationContext, PaymentSuccessActivity::class.java)
        startActivity(intent)
    }

    private fun startNfcSettingsActivity() {
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            startActivity(Intent(Settings.ACTION_NFC_SETTINGS))
        } else {
            startActivity(Intent(Settings.ACTION_WIRELESS_SETTINGS))
        }
    }
}
