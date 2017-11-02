package com.santriprogrammer.kotlinapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var nowPlayingGson: gson? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getData()
    }

    private fun getData() {
        val reqMain: RequestQueue = Volley.newRequestQueue(applicationContext)
        val strReqMain = StringRequest(Request.Method.GET, Helper.BASE_URL, Response.Listener<String> { response ->
            Log.i("response", response)
            var gsonBuilder = GsonBuilder()
            var gson: Gson = gsonBuilder.create()

            nowPlayingGson = gson.fromJson(response, gson::class.java)

            val adapter = Adapter(applicationContext, nowPlayingGson!!.getResults())
            recyclerNowPlaying.adapter = adapter
            var gridlayoutmanager = GridLayoutManager(applicationContext, 2)

            recyclerNowPlaying.layoutManager = gridlayoutmanager
        }, Response.ErrorListener {
            error -> Log.e("NowPlayingFragment", error.toString()) })
        reqMain.add<String>(strReqMain)
    }
}