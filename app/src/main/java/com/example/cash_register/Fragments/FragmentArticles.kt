package com.example.cash_register.Fragments

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.cash_register.*
import com.example.cash_register.LoggingInterceptor
import com.fivehundredpx.greedolayout.GreedoLayoutManager
import com.fivehundredpx.greedolayout.GreedoSpacingItemDecoration
import com.example.cash_register.greedo_layout_tools.MeasUtils
import com.example.cash_register.greedo_layout_tools.PhotosAdapter
import com.example.cash_register.services.Session
import com.example.cash_register.view.ChoosePayement

import java.io.IOException
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.PersistentCookieJar

import com.example.cash_register.MyApplication.getAppContext
import okhttp3.*
import android.os.AsyncTask.execute
import okhttp3.OkHttpClient




class FragmentArticles : Fragment() {
    var API_URL = "http://localhost:8080/"
    val session: Session? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_fragment__artcles, container, false)
        val btn: Button = view.findViewById(R.id.btn_choosepay)
        btn.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, ChoosePayement::class.java)
            startActivity(intent)
        })
        val photosAdapter = PhotosAdapter(this.requireContext())
        val layoutManager = GreedoLayoutManager(photosAdapter)
        layoutManager.setMaxRowHeight(MeasUtils.dpToPx(150f, this.requireContext()))

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = photosAdapter

        val spacing = MeasUtils.dpToPx(4f, this.requireContext())
        recyclerView.addItemDecoration(GreedoSpacingItemDecoration(spacing))

        getArticles()
        return view
    }


    @Throws(IOException::class)
    fun getArticles() {
        val client = OkHttpClient.Builder()
            .addInterceptor(LoggingInterceptor())
            .build()

        val request = Request.Builder()
            .url(Prefs.getApiUrl(this.requireContext()) + "/api/articles")
            .header("Authorization", Prefs.getString(getAppContext(),Constants.SHARED_PREFS, Constants.API_URL))
            .build()

        val response = client.newCall(request).execute()
        response.body()?.close()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                Log.d("error", e.message)
             }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                Log.d("success", response.message())
            }
        })
    }
}


