package com.nutrastore.blanketmag.utils.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedImageView
import com.nutrastore.blanketmag.R

class ImageProductAdapter(private val context: Context, private val _item_image: ArrayList<String>) : RecyclerView.Adapter<ImageProductAdapter.MyViewHolder>(){
    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        var image = itemView.findViewById<RoundedImageView>(R.id.list_item_image)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageProductAdapter.MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_image, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageProductAdapter.MyViewHolder, position: Int) {
        holder.image.setImageResource(context.resources.getIdentifier(_item_image[position], "drawable", context.packageName))
    }

    override fun getItemCount(): Int {
        return _item_image.size
    }

}