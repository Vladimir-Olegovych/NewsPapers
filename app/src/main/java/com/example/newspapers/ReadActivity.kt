package com.example.newspapers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class ReadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read)
        val name = intent.extras?.getString("name")
        val image = intent.extras?.getString("image")
        val text = findViewById<TextView>(R.id.textName)
        val text2 = findViewById<TextView>(R.id.textNew)
        val imageView = findViewById<ImageView>(R.id.imageNew)
        Picasso.get().load(image).into(imageView)
        text.text = name
    }
}