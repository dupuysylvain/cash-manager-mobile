package com.example.cash_register

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import com.example.cash_register.Fragments.FragmentArticles
import com.example.cash_register.Fragments.FragmentLogin
import com.example.cash_register.Fragments.FragmentScanArticle
import com.example.cash_register.Fragments.FragmentSetting
import com.example.cash_register.view.ViewPagerAdapter

import kotlinx.android.synthetic.main.activity_main_menu.*

class MainMenuActivity : AppCompatActivity() {

    //viewPager
    private var viewpager: ViewPager? = null
    //Fragments
    private var fragmentLogin: Fragment = FragmentLogin()
    private var fragmentSetting: Fragment = FragmentSetting()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        //Initializing viewPager
        viewpager = findViewById(R.id.viewPagerMainMenu)
        // setup
        setupViewPager(viewpager!!)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)

        fragmentLogin = FragmentLogin()
        fragmentSetting = FragmentSetting()

        adapter.addFragment(fragmentLogin)
        adapter.addFragment(fragmentSetting)
        viewPager.adapter = adapter
    }

}
