package com.example.cash_register.QRcode

import android.graphics.PointF
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.dlazaro66.qrcodereaderview.QRCodeReaderView
import android.widget.TextView
import android.widget.CheckBox
import android.Manifest.permission
import android.annotation.SuppressLint
import android.content.Intent
import android.support.v4.app.ActivityCompat
import android.content.pm.PackageManager
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.dlazaro66.qrcodereaderview.QRCodeReaderView.OnQRCodeReadListener
import com.example.cash_register.Constants
import com.example.cash_register.PaymentSuccessActivity
import com.example.cash_register.Prefs
import com.example.cash_register.R
import com.example.cash_register.modele.ServerError
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_qrcode.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.io.IOException


class Qrcode : AppCompatActivity(), ActivityCompat.OnRequestPermissionsResultCallback, OnQRCodeReadListener {

    private val MY_PERMISSION_REQUEST_CAMERA = 0
    private var resultTextView: TextView? = null
    private var qrCodeReaderView: QRCodeReaderView? = null
    private var flashlightCheckBox: CheckBox? = null
    private var qrCode: String = ""

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }

        setContentView(R.layout.activity_qrcode)

        val bar = findViewById<View>(R.id.bar)
        val animation = AnimationUtils.loadAnimation(this@Qrcode, R.anim.scan_animation)

        bar.visibility = View.VISIBLE
        bar.startAnimation(animation)

        animation.setAnimationListener(object : Animation.AnimationListener {

            override fun onAnimationStart(animation: Animation) {}

            override fun onAnimationEnd(animation: Animation) {
                bar.visibility = View.GONE
            }
            override fun onAnimationRepeat(animation: Animation) {}
        })

        if (ActivityCompat.checkSelfPermission(this, permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {

            qrCodeReaderView =  findViewById(R.id.qrdecoderview)
            resultTextView = findViewById(R.id.result_text_view)
            flashlightCheckBox = findViewById(R.id.flashlight_checkbox)

            qrdecoderview.setOnQRCodeReadListener(this)

            // Use this function to enable/disable decoding
            qrdecoderview.setQRDecodingEnabled(true)

            // Use this function to change the autofocus interval (default is 5 secs)
            qrdecoderview.setAutofocusInterval(2000L)

            // Use this function to enable/disable Torch
            qrdecoderview.setTorchEnabled(true)

            // Use this function to set front camera preview
            qrdecoderview.setFrontCamera()

            // Use this function to set back camera preview
            qrdecoderview.setBackCamera()

            flashlightCheckBox!!.setOnCheckedChangeListener { compoundButton, isChecked ->
                qrCodeReaderView!!.setTorchEnabled(
                    isChecked
                )
            }
        } else {
            requestCameraPermission()
        }
    }

    override fun onResume() {
        super.onResume()

        if (qrCodeReaderView != null) {
            qrCodeReaderView!!.startCamera()
        }
    }

    override fun onPause() {
        super.onPause()
        if (qrCodeReaderView != null) {
            qrCodeReaderView!!.stopCamera()
        }
    }



    override fun onQRCodeRead(text: String, points: Array<PointF>) {
        //intent to pay now
        resultTextView!!.text = text
        qrCode = text
        purchaseCart()
    }

    private fun purchaseCart() {

        Log.d("QRCODE","Purchase with cheque : " + qrCode)

        val client = OkHttpClient()

        val body = RequestBody.create(null, byteArrayOf())

        val request = Request.Builder()
            .url(Prefs.getApiUrl(applicationContext) + "/api/payment/purchase/cheque/"+qrCode)
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

    private fun requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission.CAMERA)) {
            ActivityCompat.requestPermissions(this, arrayOf(permission.CAMERA), MY_PERMISSION_REQUEST_CAMERA)
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(permission.CAMERA), MY_PERMISSION_REQUEST_CAMERA)
        }
    }


}