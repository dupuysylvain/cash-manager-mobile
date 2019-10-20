package com.example.cash_register

 import android.os.Bundle
 import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment

import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log

import android.view.MenuItem
import com.example.cash_register.Fragments.FragmentArticles
import com.example.cash_register.Fragments.FragmentSetting


class MainActivity : AppCompatActivity() {

    //BottomNavigationView
    private var bottomNavigationView: BottomNavigationView? = null

    //viewPager
    private var viewpager: ViewPager? = null


    //Fragments
    private var fragmentArticles: Fragment = FragmentArticles()
    private var fragmentSetting: Fragment = FragmentSetting()
    private var prevMenuItem: MenuItem? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            this.supportActionBar!!.hide()
          }
        catch (e: NullPointerException) {
        }

        setContentView(R.layout.activity_main)


        //Initializing viewPager
        viewpager = findViewById(R.id.viewPager)

        //Initializing the bottomNavigationView
        bottomNavigationView = findViewById(R.id.nav_view)

        bottomNavigationView?.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_articles  -> viewpager!!.currentItem = 0
                R.id.navigation_setting -> viewpager!!.currentItem = 1
             }
            false
        }

        viewpager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                if (prevMenuItem != null) {
                    prevMenuItem!!.isChecked = false
                } else {
                    bottomNavigationView?.menu?.getItem(0)?.isChecked = false
                }
                Log.d("page", "onPageSelected: $position")
                bottomNavigationView?.menu?.getItem(position)?.isChecked = true
                prevMenuItem = bottomNavigationView?.menu?.getItem(position)
            }
            override fun onPageScrollStateChanged(state: Int) {}
        })
        setupViewPager(viewpager!!)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        fragmentArticles = FragmentArticles()
        fragmentSetting = FragmentSetting()
         adapter.addFragment(fragmentArticles)
        adapter.addFragment(fragmentSetting)
         viewPager.adapter = adapter
    }


}