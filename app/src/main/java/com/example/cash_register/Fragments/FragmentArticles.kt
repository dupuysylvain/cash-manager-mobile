package com.example.cash_register.Fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
 import com.example.cash_register.R
  import com.example.cash_register.ChoosePayement

class FragmentArticles : Fragment(){

    companion object {

        fun newInstance(): FragmentArticles {
            return FragmentArticles()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.activity_fragment__artcles, container, false)
        val btn:Button =  view.findViewById(R.id.btn_choosepay)
        btn.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, ChoosePayement::class.java)
            startActivity(intent)
        })

        return view
    }




}
