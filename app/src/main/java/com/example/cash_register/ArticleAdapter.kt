package com.example.cash_register

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cash_register.modele.Article
import kotlinx.android.synthetic.main.article_list_item.view.*

class ArticleAdapter(val items : ArrayList<Article>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.article_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.tvAnimalType?.text = items.get(position).toString()
    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }


}



class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val tvAnimalType = view.tv_article_type
}