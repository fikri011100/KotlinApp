package com.santriprogrammer.kotlinapp

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * Created by fikriimaduddin on 11/2/17.
 */
class Adapter : RecyclerView.Adapter<Adapter.ViewHolder> {

    var list_data: List<gson.ResultsBean>? = null
    var c: Context? = null

    constructor(list_data: Context, c: List<gson.ResultsBean>?){
        this.list_data = c
        this.c = list_data
    }

    override fun onBindViewHolder(holder: Adapter.ViewHolder, position: Int) {
        val data: gson.ResultsBean = list_data!!.get(position)
        Picasso.with(c)
                .load(Helper.IMAGE_URL_POSTER + data.poster_path)
                .placeholder(R.mipmap.ic_launcher)
                .into(holder!!.imgItem)
        holder!!.imgItem?.setOnClickListener({
            var intent = Intent(c?.applicationContext, DetailActivity::class.java)
            intent.putExtra("title", data.original_title)
            intent.putExtra("image", data.poster_path)
            intent.putExtra("overview", data.overview)
            c!!.startActivity(intent)
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(c).inflate(R.layout.list_item, parent, false)

        return ViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return list_data!!.size
    }

    class ViewHolder(itemView:View?) : RecyclerView.ViewHolder(itemView) {
        var imgItem = itemView?.findViewById<ImageView>(R.id.imgItem)
    }

}