package com.store.example.utils.adapters

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedImageView
import com.store.example.R

class ImageAdapter(private val context: Context) : RecyclerView.Adapter<ImageAdapter.MyViewHolder>(){
    lateinit var list:List<Int>
    fun setContentList(list:List<Int>){
        this.list=list
        notifyDataSetChanged()
    }
    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        var image = itemView.findViewById<RoundedImageView>(R.id.list_item_image)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageAdapter.MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_image, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageAdapter.MyViewHolder, position: Int) {
        holder.image.setImageResource(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

}