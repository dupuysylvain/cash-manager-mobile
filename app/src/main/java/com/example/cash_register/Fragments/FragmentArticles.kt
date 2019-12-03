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
import com.example.cash_register.view.ChoosePayement

import java.io.IOException

import okhttp3.*
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.example.cash_register.modele.Article
import com.example.cash_register.modele.Cart
import com.google.gson.Gson
import okhttp3.OkHttpClient












class FragmentArticles : Fragment() {
    private var articlesList: ArrayList<Article> = arrayListOf()
    private var articles: ArrayList<Article> =  arrayListOf()
    private lateinit var articleAdapter: ArticleAdapter
    private lateinit var choosePayment: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_fragment__articles, container, false)
        choosePayment = view.findViewById(R.id.btn_choosepay)

        val id = resources.getIdentifier("yo", "id", context!!.getPackageName())

        choosePayment.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, ChoosePayement::class.java)
            startActivity(intent)
        })

        getArticles()

        setupGrid(view)

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
                val articles = Gson().fromJson(response.body()!!.string(),  Array<Article>::class.java)
                for(article in articles){
                    articlesList.addAll(listOf(article))
                }
                getCart()
            }
        })
        return articles
    }

    fun getCart() {
        val client = OkHttpClient.Builder()
            .build()

        val request = Request.Builder()
            .url(Prefs.getApiUrl(this.requireContext()) + "/api/cart")
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
                val cart = Gson().fromJson(response.body()!!.string(), Cart::class.java)

                updateArticleCartQuantity(cart)

                activity!!.runOnUiThread(Runnable {
                    articleAdapter.notifyDataSetChanged()
                })
            }
        })
    }

    private fun updateArticleCartQuantity(cart: Cart) {
        var total: Int = 0
        for(articleQuantity in cart.articlesWithQuantity!!) {
            var article = articlesList.filter { a -> a.id == articleQuantity.article!!.id }.first()
            article.cartQuantity = articleQuantity.quantity
            total += article.cartQuantity
        }
        activity!!.runOnUiThread(Runnable {
            choosePayment.text = "PAY NOW (total: " + cart.totalAmount.split('.')[0] + "€)"
        })
    }

    private fun setupGrid(view: View){
        val recycler_view_articles: RecyclerView = view.findViewById(R.id.recycler_view_articles)

        articlesList = ArrayList(articles)
        recycler_view_articles.layoutManager = LinearLayoutManager(context)
        // You can use GridLayoutManager if you want multiple columns. Enter the number of columns as a parameter.
        recycler_view_articles.layoutManager = GridLayoutManager(context, 2)
        articleAdapter = ArticleAdapter(choosePayment, articlesList, this.requireContext())
        recycler_view_articles.adapter = articleAdapter
    }


}


