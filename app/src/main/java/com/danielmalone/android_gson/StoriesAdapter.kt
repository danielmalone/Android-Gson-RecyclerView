package com.danielmalone.android_gson

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.danielmalone.android_gson.model.Story
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_story.view.*

class StoriesAdapter(val stories: List<Story>, val asdf: Context) : RecyclerView.Adapter<StoriesAdapter.StoriesViewHolder>() {

    override fun getItemCount() = stories.size

    override fun onBindViewHolder(holder: StoriesViewHolder, position: Int) {
        holder.bindStory(stories[position], asdf)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoriesViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.row_story, parent, false)
        return StoriesViewHolder(view)
    }

    class StoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindStory(story: Story, context: Context) {
            itemView.headline.text = story.title
            Picasso.with(context).load("http://lorempixel.com/600/400/?" + adapterPosition).into(itemView.photo)
        }
    }
}
