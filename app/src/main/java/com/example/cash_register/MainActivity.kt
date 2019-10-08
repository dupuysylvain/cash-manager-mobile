package com.example.cash_register

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import com.example.cash_register.Fragments.FragmentHome
import com.example.cash_register.Fragments.FragmentSetting
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

     var adapterViewPager: FragmentPagerAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager = viewPager

        adapterViewPager = MyPageAdapter(supportFragmentManager)
        viewPager.adapter = adapterViewPager

        viewPager.currentItem = 0
    }
    class MyPageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment? {
            when (position) {
                0 -> return FragmentHome.newInstance()
                1 -> return FragmentSetting.newInstance()
            }
            return null
        }

        override fun getCount(): Int {
            return 2
        }
        // nombre de page
    }

}
