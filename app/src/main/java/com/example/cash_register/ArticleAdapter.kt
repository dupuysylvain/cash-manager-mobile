package com.example.cash_register

import android.app.Activity
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import android.widget.*
import com.example.cash_register.modele.Article
import com.squareup.picasso.Picasso
import com.example.cash_register.R
import com.example.cash_register.modele.Cart
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import okhttp3.RequestBody




class ArticleAdapter(val button : Button, val items : ArrayList<Article>, val context: Context) : RecyclerView.Adapter<ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(LayoutInflater.from(context), parent)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article: Article = items.get(position)

        holder.buttonViewOption.setOnClickListener(View.OnClickListener {
            //will show popup menu here
            //creating a popup menu
            val popup = PopupMenu(context, holder.buttonViewOption)
            //inflating menu from xml resource
            popup.inflate(R.menu.options_menu)
            //adding click listener
            popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->

                when (item!!.itemId) {
                    R.id.menu1 -> {
                        addOrRemoveToCart(article, article.cartQuantity + 1)
                    }
                    R.id.menu2 -> {
                        // REMOVE FROM CART
                        var newQuantity = article.cartQuantity - 1

                        if (newQuantity > 0) {
                            addOrRemoveToCart(article, newQuantity)
                        } else if (newQuantity == 0) {
                            deleteArticleFromCart(article)
                        }
                    }
                }

                true
            })

            //displaying the popup
            popup.show()
        })


        holder.bind(article)
    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    private fun addOrRemoveToCart(article: Article, newQuantity: Int) {
        val client = OkHttpClient()

        val body = RequestBody.create(null, byteArrayOf())

        val request = Request.Builder()
            .url(Prefs.getApiUrl(context) + "/api/cart/" + article.id + "/" + newQuantity.toString())
            .header(
                "Authorization",
                Prefs.getString(context, Constants.SHARED_PREFS, Constants.TOKEN)
            )
            .post(body)
            .build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                Log.d("error", e.message)
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                var activity = context as Activity

                if(response.isSuccessful) {
                    val cart = Gson().fromJson(response.body()!!.string(), Cart::class.java)

                    activity.runOnUiThread(Runnable {
                        article.cartQuantity = newQuantity
                        button.text = "PAY NOW (total: " + cart.totalAmount.split('.')[0] + "€)"
                        notifyDataSetChanged()
                    })
                } else {
                    activity.runOnUiThread(Runnable {
                        Toast.makeText(
                            context,
                            "Une erreur est survenue",
                            Toast.LENGTH_SHORT
                        ).show()
                    })
                }
            }
        })
    }

    private fun deleteArticleFromCart(article: Article) {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url(Prefs.getApiUrl(context) + "/api/cart/" + article.id + "/1")
            .header(
                "Authorization",
                Prefs.getString(context, Constants.SHARED_PREFS, Constants.TOKEN)
            )
            .delete()
            .build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                Log.d("error", e.message)
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                var activity = context as Activity

                if(response.isSuccessful) {
                    val cart = Gson().fromJson(response.body()!!.string(), Cart::class.java)

                    activity.runOnUiThread(Runnable {
                        article.cartQuantity = 0
                        button.text = "PAY NOW (total: " + cart.totalAmount.split('.')[0] + "€)"
                        notifyDataSetChanged()
                    })
                } else {
                    activity.runOnUiThread(Runnable {
                        Toast.makeText(
                            context,
                            "Une erreur est survenue",
                            Toast.LENGTH_SHORT
                        ).show()
                    })
                }
            }
        })
    }
}

class ArticleViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.article_list_item, parent, false)) {
    private var mImageView: ImageView? = null
    var buttonViewOption: TextView
    private var quantity: TextView? = null
    private var price: TextView? = null

    init {
        mImageView = itemView.findViewById(R.id.articleImg)
        buttonViewOption = itemView.findViewById(R.id.textViewOptions) as TextView
        quantity = itemView.findViewById(R.id.quantity)
        price = itemView.findViewById(R.id.price)
    }

    fun bind(article: Article) {
        Picasso.get().load(article.image).into(mImageView)
        quantity!!.text = article.cartQuantity.toString() + " (" + article.quantity + " en stock)"
        price!!.text = article.price + "€"
    }

}