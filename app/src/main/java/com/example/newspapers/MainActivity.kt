package com.example.newspapers

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = NewsAdapter(Handler(Looper.getMainLooper()), this)

        mRecyclerView = findViewById(R.id.recyclerView)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = adapter

        adapter.refresh()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.drawer_main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.app_Sport -> Toast.makeText(this, "Поиск: Спорт", Toast.LENGTH_SHORT).show()
            R.id.app_Politics -> Toast.makeText(this, "Поиск: Политика", Toast.LENGTH_SHORT).show()
            R.id.app_Money -> Toast.makeText(this, "Поиск: Валюты", Toast.LENGTH_SHORT).show()
            R.id.menu_icons -> Toast.makeText(this, "Категории", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    fun itemOnClickList(title: String, image: String, mainActivity: MainActivity){
        val intent = Intent(mainActivity, ReadActivity::class.java)
        intent.putExtra("name", title)
        intent.putExtra("image", image)
        startActivity(intent)
    }
    override fun onDestroy() {
        super.onDestroy()
        adapter.shutdown()
    }
}
