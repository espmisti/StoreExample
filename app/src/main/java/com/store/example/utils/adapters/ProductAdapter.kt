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
import com.store.example.R

class ProductAdapter(private val _item: ArrayList<Int>, private val onClickListener: OnClickListener, private val context: Context) :
    RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name_product)
        val price: TextView = view.findViewById(R.id.price_product)
        val rating: TextView = view.findViewById(R.id.rating_product)
        val rating_bar: RatingBar = view.findViewById(R.id.ratingBard)
        val image: ImageView = view.findViewById(R.id.product_image)
        val bg_image: ImageView = view.findViewById(R.id.image)
        val button: Button = view.findViewById(R.id.button)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductAdapter.MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductAdapter.MyViewHolder, position: Int) {
        val item = context.resources.getStringArray(_item[position])
        holder.name.text = item[0]
        holder.price.text = item[2]
        holder.rating.text = item[3]
        holder.rating_bar.rating = item[3].toFloat()
        holder.button.setOnClickListener { onClickListener.onClicked(item) }
        holder.image.setImageResource(context.resources.getIdentifier(item[4], "drawable", context.packageName))
        holder.image.setOnClickListener { onClickListener.onClicked(item) }
        holder.bg_image.setOnClickListener { onClickListener.onClicked(item) }
    }
    override fun getItemCount() = _item.size
}