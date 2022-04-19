package com.nutrastore.blanketmag.utils.adapters

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedImageView
import com.nutrastore.blanketmag.R

class ImageAdapter(private val context: Context, private val _item_image: ArrayList<Int>, private val _item_string: ArrayList<Int>) : RecyclerView.Adapter<ImageAdapter.MyViewHolder>(){
    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        var image = itemView.findViewById<RoundedImageView>(R.id.list_item_image)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageAdapter.MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_image_main, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageAdapter.MyViewHolder, position: Int) {
        holder.image.setImageResource(_item_image[position])
        holder.image.setOnClickListener {

            when(position){
                0->{
                    val bundle = Bundle()
                    bundle.putStringArray("product", context.resources.getStringArray(_item_string[1]))
                    Navigation.findNavController(context as Activity, R.id.fragmentContainerView).navigate(R.id.productFragment, bundle)
                }
                1-> {
                    val bundle = Bundle()
                    bundle.putStringArray("product", context.resources.getStringArray(_item_string[0]))
                    Navigation.findNavController(context as Activity, R.id.fragmentContainerView).navigate(R.id.productFragment, bundle)
                }

            }
        }
    }

    override fun getItemCount(): Int {
        return _item_image.size
    }

}