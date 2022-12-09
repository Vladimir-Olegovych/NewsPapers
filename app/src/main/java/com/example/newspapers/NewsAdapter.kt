package com.example.newspapers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private var data: List<News> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.cardview_news, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = data[position]
        holder.title.text = news.title
        Picasso.get().load(news.image).into(holder.image)
    }

    override fun getItemCount(): Int = data.size

    fun update() {
        data = News.getNews()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var image: ImageView = itemView.findViewById(R.id.news_image)
        var title: TextView = itemView.findViewById(R.id.news_tv_description)
    }
}