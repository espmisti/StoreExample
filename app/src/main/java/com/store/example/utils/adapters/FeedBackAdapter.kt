package com.store.example.utils.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedImageView
import com.store.example.R

class FeedBackAdapter(private val _item: ArrayList<Int>, private val context: Context) : RecyclerView.Adapter<FeedBackAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.fb_name)
        val date: TextView = view.findViewById(R.id.fb_date)
        val rating: RatingBar = view.findViewById(R.id.fb_ratingbar)
        val response: TextView = view.findViewById(R.id.fb_textResponse)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedBackAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.feedback_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FeedBackAdapter.MyViewHolder, position: Int) {
        val item = context.resources.getStringArray(_item[position])
        holder.name.text = item[0]
        holder.date.text = item[1]
        holder.rating.rating = item[2].toFloat()
        holder.response.text = item[3]
    }

    override fun getItemCount(): Int {
        return _item.size
    }
}