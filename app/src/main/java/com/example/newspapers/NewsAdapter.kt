package com.example.newspapers

import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.util.concurrent.Executors

class NewsAdapter(private val mainHandler: Handler): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    @Volatile private var data: List<News> = emptyList()
    private var page = 1
    private var inProgress = false
    private val executor = Executors.newSingleThreadExecutor()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.cardview_news, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = data[position]
        holder.title.text = news.title
        Picasso.get().load(news.image).into(holder.image)

        holder.title.setOnClickListener {holder.title.text = "Ты какашка, не жми сюда!"}

        holder.image.setOnClickListener {Picasso.get().load(R.drawable.ic_launcher_foreground).into(holder.image)}

        if (inProgress || position != data.size - 1) return
        inProgress = true
        executor.execute {
            val newData = News.getNews(++page)
            if (newData.isNotEmpty()) {
                val size = data.size
                data = data + newData
                mainHandler.post {
                    notifyItemRangeInserted(size /* Возможно надо +1, Сак чей-то кок,
                     Илья или Влад, если кто то это из вас это прочитал - тот гей))), я прочитал и гей тоже :( */
                        ,newData.size)
                    inProgress = false
                }
            }
        }
    }

    override fun getItemCount(): Int = data.size

    fun refresh() {
        inProgress = true
        executor.execute {
            page = 1
            data = News.getNews(page)
            if (data.isNotEmpty())
                mainHandler.post {
                    notifyDataSetChanged()
                    inProgress = false
                }
        }
    }

    fun shutdown() {
        executor.shutdown()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var image: ImageView = itemView.findViewById(R.id.news_image)
        var title: TextView = itemView.findViewById(R.id.news_tv_description)
    }
}