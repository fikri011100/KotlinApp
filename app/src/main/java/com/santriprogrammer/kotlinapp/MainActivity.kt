package com.santriprogrammer.kotlinapp

import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.Response.Listener
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var nowPlayingGson: gsonMovie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getData()
    }

    private fun getData() {
        val reqMain: RequestQueue = Volley.newRequestQueue(applicationContext)
        val strReqMain: StringRequest? = StringRequest(Request.Method.GET, Helper.BASE_URL, Listener<String> { response ->
            Log.i("response", response)
            var gsonBuilder = GsonBuilder()
            var gson: Gson = gsonBuilder.create()

            nowPlayingGson = gson.fromJson(response, gsonMovie::class.java)

            val adapter = Adapter(this, nowPlayingGson?.getResults())
            recyclerNowPlaying.adapter = adapter
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                recyclerNowPlaying.layoutManager = GridLayoutManager(this@MainActivity, 2)
            } else {
                recyclerNowPlaying.layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }, Response.ErrorListener {

        })
        reqMain.add<String>(strReqMain)
    }
}