package com.example.cash_register.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.example.cash_register.MainActivity
import com.example.cash_register.R

class SplashScreen : AppCompatActivity() {
    private var imageLogo: ImageView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val actionBar = supportActionBar
        actionBar!!.hide()

        imageLogo = findViewById<View>(R.id.imageLogo) as ImageView?
        val anim = AnimationUtils.loadAnimation(this, R.anim.transition)
        imageLogo!!.startAnimation(anim)

        Handler().postDelayed({
            val intent = Intent(this@SplashScreen, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_TIME_OUT.toLong())
    }

    companion object {
        private val SPLASH_TIME_OUT = 3000
    }
}