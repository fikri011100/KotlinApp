package com.santriprogrammer.kotlinapp

import android.content.Context
import android.content.Intent
import android.media.Image
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso

/**
 * Created by fikriimaduddin on 11/2/17.
 */
class Adapter : RecyclerView.Adapter<Adapter.ViewHolder> {

    var list_data: List<gsonMovie.ResultsBean>? = null
    var c: Context? = null
    var lastPosition = -1

    constructor(list_data: Context, c: List<gsonMovie.ResultsBean>?){
        this.list_data = c
        this.c = list_data
    }

    override fun onBindViewHolder(holder: Adapter.ViewHolder?, position: Int) {
        val data: gsonMovie.ResultsBean = list_data!!.get(position)
        Picasso.with(c)
                .load(Helper.IMAGE_URL_POSTER + data.poster_path)
                .placeholder(R.mipmap.ic_launcher)
                .into(holder?.imgItem)
        holder?.txtTitle?.text = data.original_title
        holder?.txt_vote?.text = data.vote_average.toString()
        holder?.rb_list?.rating = data.vote_count.toFloat() / 2

        holder?.consItem?.setOnClickListener({
            var intent = Intent(c?.applicationContext, DetailActivity::class.java)
            intent.putExtra("title", data.original_title)
            intent.putExtra("image", data.poster_path)
            intent.putExtra("overview", data.overview)
            c?.startActivity(intent)
        })
        setAnimation(holder!!.itemView, position)
    }
    private fun setAnimation(viewToAnimate: View, position: Int) {
        if (position > lastPosition) {
            var animation: Animation = AnimationUtils.loadAnimation(c, R.anim.item_animation_fall_down)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var inflater = LayoutInflater.from(c).inflate(R.layout.list_item, parent, false)

        return ViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return list_data?.size!!
    }


    class ViewHolder(itemView:View?) : RecyclerView.ViewHolder(itemView) {
        var imgItem = itemView?.findViewById<ImageView>(R.id.imgItem)
        var txtTitle = itemView?.findViewById<TextView>(R.id.txt_title)
        var rb_list = itemView?.findViewById<RatingBar>(R.id.rb_votes)
        var consItem = itemView?.findViewById<ConstraintLayout>(R.id.consItem)
        var txt_vote = itemView?.findViewById<TextView>(R.id.votes_result)
    }
}