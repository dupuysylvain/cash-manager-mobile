package com.example.cash_register.Fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
   import com.example.cash_register.view.ChoosePayement
import android.support.v7.widget.RecyclerView
import com.example.cash_register.R
 import com.fivehundredpx.greedolayout.GreedoLayoutManager
  import com.fivehundredpx.greedolayout.GreedoSpacingItemDecoration
import com.example.cash_register.greedo_layout_tools.MeasUtils
import com.example.cash_register.greedo_layout_tools.PhotosAdapter
import android.view.WindowManager
 import android.os.Build
import android.widget.ToggleButton



class FragmentArticles : Fragment(){


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.activity_fragment__artcles, container, false)
        val btn:Button =  view.findViewById(R.id.btn_choosepay)
        btn.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, ChoosePayement::class.java)
            startActivity(intent)
        })

        val photosAdapter = PhotosAdapter(this.requireContext())
        val layoutManager = GreedoLayoutManager(photosAdapter)
        layoutManager.setMaxRowHeight(MeasUtils.dpToPx(150f, this.requireContext()))

        val recyclerView:RecyclerView =   view.findViewById(R.id.recycler_view)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = photosAdapter

        val spacing = MeasUtils.dpToPx(4f, this.requireContext())
        recyclerView.addItemDecoration(GreedoSpacingItemDecoration(spacing))



        return view
    }


}
