package com.example.newspapers

import android.util.Log
import org.jsoup.Jsoup

class News(val title: String,
           val image: String) {

    companion object {

        fun getNews(page: Int): List<News> {
            try {
                val table = Jsoup
                    .connect("https://tltgorod.ru/news/theme-0/$page/")
                    .get().getElementById("myTable1") ?: return emptyList()
                val elements = table.child(0).children()
                val result = ArrayList<News>()

                for (element in elements) {
                    if (element.attr("height") == "1") continue
                    val columns = element.children()
                    val image = columns[0].child(0).attr("src")
                    val title = columns[1].child(0).child(1).child(0).text()

                    result.add(News(title, "https://tltgorod.ru$image"))
                }

                return result

            } catch (e: Throwable) {
                Log.d("lol", "Error", e)
                return emptyList()
            }
        }
    }
}