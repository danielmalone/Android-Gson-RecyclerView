package com.danielmalone.android_gson

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.danielmalone.android_gson.model.Story
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    companion object {
        const val URL: String = "https://jsonplaceholder.typicode.com/posts"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        doAsync {
            val json: String = java.net.URL(URL).readText()

            uiThread {
                showStories(json)
            }
        }
    }

    fun showStories(json: String) {
        val gson: Gson = GsonBuilder().create()
        val stories: List<Story> = gson.fromJson(json, Array<Story>::class.java).toList()
        showRecyclerView(stories)
    }

    fun showRecyclerView(stories: List<Story>) {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = StoriesAdapter(stories, this@MainActivity)
    }
}
