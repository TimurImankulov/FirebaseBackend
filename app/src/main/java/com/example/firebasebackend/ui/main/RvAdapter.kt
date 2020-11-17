package com.example.firebasebackend.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasebackend.R
import com.example.firebasebackend.data.model.NewsItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.rv_item.view.*

class RvAdapter : RecyclerView.Adapter<RvHolder>() {
    private val list = arrayListOf<NewsItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_item, parent, false)
        return RvHolder(view)
    }

    fun update(list: ArrayList<NewsItem>) {
        if (list != null) {
            this.list.clear()
            this.list.addAll(list)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: RvHolder, position: Int) {
        holder.bind(list[position])
    }
}

class RvHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(
        newsItem: NewsItem
    ) {
        itemView.title.text = newsItem.title
        itemView.desc.text = newsItem.desc

        val image = newsItem.image
        Picasso.get().load(image).into(itemView.image)
    }
}

