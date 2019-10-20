package com.example.cash_register.QRcode

import android.graphics.PointF
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.dlazaro66.qrcodereaderview.QRCodeReaderView
import android.widget.TextView
import android.widget.CheckBox
import android.Manifest.permission
import android.annotation.SuppressLint
import android.support.v4.app.ActivityCompat
import android.content.pm.PackageManager
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.dlazaro66.qrcodereaderview.QRCodeReaderView.OnQRCodeReadListener
import com.example.cash_register.R
import kotlinx.android.synthetic.main.activity_qrcode.*







class Qrcode : AppCompatActivity(), ActivityCompat.OnRequestPermissionsResultCallback, OnQRCodeReadListener {

    private val MY_PERMISSION_REQUEST_CAMERA = 0
    private var resultTextView: TextView? = null
    private var qrCodeReaderView: QRCodeReaderView? = null
    private var flashlightCheckBox: CheckBox? = null




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

            flashlightCheckBox!!.setOnCheckedChangeListener { _, isChecked ->
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
    }

    private fun requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission.CAMERA)) {
            ActivityCompat.requestPermissions(this, arrayOf(permission.CAMERA), MY_PERMISSION_REQUEST_CAMERA)
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(permission.CAMERA), MY_PERMISSION_REQUEST_CAMERA)
        }
    }


}