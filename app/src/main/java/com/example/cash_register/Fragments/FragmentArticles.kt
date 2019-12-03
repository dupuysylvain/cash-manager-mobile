package com.example.cash_register.Fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.util.Log
import com.example.cash_register.*
import com.example.cash_register.services.Session
import com.example.cash_register.view.ChoosePayement

import java.io.IOException

import okhttp3.*
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.cash_register.modele.Article
import com.google.gson.Gson
import okhttp3.OkHttpClient
import kotlinx.android.synthetic.main.activity_fragment__articles.*
import com.google.gson.reflect.TypeToken




class FragmentArticles : Fragment() {
     lateinit var taVAriable: ArrayList<Article>
    private lateinit var articleList: Article
    private var articles: ArrayList<Article> =  arrayListOf()

    var API_URL = "http://localhost:8080/"
    val session: Session? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_fragment__articles, container, false)
        val btn: Button = view.findViewById(R.id.btn_choosepay)
        val recycler_view_articles: RecyclerView = view.findViewById(R.id.recycler_view_articles)

        btn.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, ChoosePayement::class.java)
            startActivity(intent)
        })
       /* val photosAdapter = PhotosAdapter(this.requireContext())
        val layoutManager = GreedoLayoutManager(photosAdapter)
        layoutManager.setMaxRowHeight(MeasUtils.dpToPx(150f, this.requireContext()))


        recycler_view_articles.layoutManager = layoutManager
        recycler_view_articles.adapter = photosAdapter

        val spacing = MeasUtils.dpToPx(4f, this.requireContext())
        recycler_view_articles.addItemDecoration(GreedoSpacingItemDecoration(spacing))*/

        getArticles()
         taVAriable = ArrayList(articles)
        recycler_view_articles.layoutManager = LinearLayoutManager(context)
        // You can use GridLayoutManager if you want multiple columns. Enter the number of columns as a parameter.
        recycler_view_articles.layoutManager = GridLayoutManager(context, 2)
        recycler_view_articles.adapter = ArticleAdapter(taVAriable, this.requireContext())

        return view
    }


    @Throws(IOException::class)
    fun getArticles(): ArrayList<Article> {
        val client = OkHttpClient.Builder()
            .build()

        val request = Request.Builder()
            .url(Prefs.getApiUrl(this.requireContext()) + "/api/articles")
            .header(
                "Authorization",
                Prefs.getString(this.requireContext(), Constants.SHARED_PREFS, Constants.TOKEN)
            )
            .build()
        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                Log.d("error", e.message)
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                val gson = Gson()
                val articles = gson.fromJson(response.body()!!.string(),  Array<Article>::class.java)
                for(article in articles){
                    articleList = article
                    taVAriable.addAll(listOf(article))
                }
            }
        })
        return articles
    }


}


