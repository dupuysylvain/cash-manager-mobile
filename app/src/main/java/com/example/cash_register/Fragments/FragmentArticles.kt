package com.example.cash_register.Fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.cash_register.R
import com.fivehundredpx.greedolayout.GreedoLayoutManager
import com.fivehundredpx.greedolayout.GreedoSpacingItemDecoration
import com.example.cash_register.greedo_layout_tools.MeasUtils
import com.example.cash_register.greedo_layout_tools.PhotosAdapter
import com.example.cash_register.services.Session
import com.example.cash_register.view.ChoosePayement
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.Callback
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.PersistentCookieJar




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
        val cookieJar = PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(context))

        val client = OkHttpClient.Builder()
            .cookieJar(cookieJar)
            .build()

        val request = Request.Builder()
            .url(API_URL + "api/articles")
            .get()
            .addHeader("Authorization", "Bearer  eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJzZWN1cmUtYXBpIiwiYXVkIjoic2VjdXJlLWFwcCIsInN1YiI6ImFkbWluLmFkbWluIiwiZXhwIjoxNTc1MDE5OTIwLCJyb2wiOlsiQURNSU5fVVNFUiIsIlNUQU5EQVJEX1VTRVIiXX0.aEXXG1HFNl5B1lqvgFxpqmVVD9KeNHLKvx2I6r8_Erk4mhZ7zC_VyhgxNfcXyNiLjul65AS427ff5zra8P2tnQ" )
             .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                Log.d("error", e.message)

            }
            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                Log.d("message",  response.message())
                Log.d("body",  response.body().toString())


            }
        })

    }

}

