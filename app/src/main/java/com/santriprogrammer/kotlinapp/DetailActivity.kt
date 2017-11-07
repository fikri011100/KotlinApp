package com.santriprogrammer.kotlinapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        val title = intent.getStringExtra("title")
        val image = intent.getStringExtra("image")
        val overview = intent.getStringExtra("overview")

        Picasso.with(applicationContext)
                .load(Helper.IMAGE_URL_BACKDROP + image)
                .placeholder(R.mipmap.ic_launcher)
                .into(imgDetail)
        setTitle(title)
        txtDetail.setText(overview)
    }
}
