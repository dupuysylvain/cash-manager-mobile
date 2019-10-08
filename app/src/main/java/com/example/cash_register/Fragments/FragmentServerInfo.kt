package com.example.cash_register.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cash_register.R

class FragmentServerInfo : Fragment() {

    companion object {

        fun newInstance(): FragmentServerInfo {
            return FragmentServerInfo()
        }
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.activity_fragment_server_info, container, false)
    }


}
