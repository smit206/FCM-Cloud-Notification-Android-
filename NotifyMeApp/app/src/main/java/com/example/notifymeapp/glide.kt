package com.example.notifymeapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class glide : AppCompatActivity() {
    var image:ImageView? = null
    var button:Button? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glide)

        image = findViewById(R.id.imageView)
        button = findViewById(R.id.button)

        button!!.setOnClickListener {
            val url = "https://picsum.photos/300"

            Glide.with(this)
                .load(url)
                .fitCenter()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(R.drawable.tweets)
                .into(image!!)
        }
    }
}