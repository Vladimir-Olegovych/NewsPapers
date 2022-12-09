package com.example.newspapers

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private lateinit var mRecyclerView: RecyclerView
    private          val adapter = NewsAdapter()
    private lateinit var executor: ExecutorService
    private          val mainHandler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        executor = Executors.newSingleThreadExecutor()

        mRecyclerView = findViewById(R.id.recyclerView)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = adapter

        executor.execute {
            adapter.update()
            mainHandler.post { adapter.notifyDataSetChanged() }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        executor.shutdown()
    }

}
