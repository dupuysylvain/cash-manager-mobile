package com.example.cash_register.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cash_register.R

class FragmentHome : Fragment(){

    companion object {

        fun newInstance(): FragmentHome {
            return FragmentHome()
        }
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.activity_fragment__home, container, false)
    }


}
