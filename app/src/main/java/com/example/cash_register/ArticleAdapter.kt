package com.example.cash_register

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.cash_register.modele.Article
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.article_list_item.view.*

class ArticleAdapter(val items : ArrayList<Article>, val context: Context) : RecyclerView.Adapter<ArticleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(LayoutInflater.from(context), parent)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article: Article = items.get(position)
        holder.bind(article)
    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }


}

class ArticleViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.article_list_item, parent, false)) {
    private var mImageView: ImageView? = null

    init {
        mImageView = itemView.findViewById(R.id.articleImg)
    }

    fun bind(article: Article) {
        Picasso.get().load(article.image).into(mImageView)
    }

}