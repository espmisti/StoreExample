package com.nutrastore.blanketmag.utils.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedImageView
import com.nutrastore.blanketmag.R
import android.widget.FrameLayout
import androidx.core.view.marginBottom


class FeedBackAdapter(private val _item: ArrayList<Int>, private val context: Context, private val type: String) : RecyclerView.Adapter<FeedBackAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.fb_name)
        val date: TextView = view.findViewById(R.id.fb_date)
        val rating: RatingBar = view.findViewById(R.id.fb_ratingbar)
        val response: TextView = view.findViewById(R.id.fb_textResponse)
        val image: RoundedImageView = view.findViewById(R.id.feedback_image)
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
        when(type){
            "Biolica", "Idealica" -> holder.image.setImageResource(context.resources.getIdentifier(item[4], "drawable", context.packageName))
            else ->{
                val params = holder.image.layoutParams
                params.height = 0
                holder.image.layoutParams = params
                val param_text = holder.response.layoutParams as ViewGroup.MarginLayoutParams
                param_text.bottomMargin = 0
            }
        }

    }

    override fun getItemCount(): Int {
        return _item.size
    }
}