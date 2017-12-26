package com.danielmalone.android_gson

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.danielmalone.android_gson.model.Story

class StoriesAdapter(val stories: List<Story>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val AD_INSERTION_INTERVAL = 3
        const val TYPE_AD = 0
        const val TYPE_POST = 1
        const val TYPE_VIDEO = 2
    }

    override fun getItemCount(): Int {
        val additionalRows: Int
        if (stories.isNotEmpty() && AD_INSERTION_INTERVAL > 0 && stories.size > AD_INSERTION_INTERVAL) {
            additionalRows = stories.size / AD_INSERTION_INTERVAL
        } else {
            additionalRows = 0
        }
        return stories.size + additionalRows
    }

    override fun getItemViewType(position: Int): Int {
        if (position % AD_INSERTION_INTERVAL == 0) {
            return TYPE_AD
        }
        return TYPE_POST
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemsViewHolder).bind(stories[getRealPosition(position)])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_POST) {
            return StoriesViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.row_story, parent, false))
        }
        return AdViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.row_ad, parent, false))
    }

    fun getRealPosition(position: Int): Int {
        if (AD_INSERTION_INTERVAL == 0) {
            return position
        }
        return position - position / AD_INSERTION_INTERVAL
    }

    class StoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), ItemsViewHolder {
        override fun bind(item: Any) {
        }
    }

    class AdViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), ItemsViewHolder {
        override fun bind(item: Any) {
        }
    }

    interface ItemsViewHolder {
        fun bind(item: Any)
    }
}
