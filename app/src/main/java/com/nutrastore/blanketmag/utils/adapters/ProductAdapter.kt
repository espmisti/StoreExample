package com.nutrastore.blanketmag.utils.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nutrastore.blanketmag.R
import android.text.Html




class ProductAdapter(private val _item: ArrayList<Int>, private val _price: Array<String>, private val onClickListener: OnClickListener, private val context: Context) :
    RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name_product)
        val price: TextView = view.findViewById(R.id.price_product)
        val price_last: TextView = view.findViewById(R.id.price_last_product)
        val rating: TextView = view.findViewById(R.id.rating_product)
        val rating_bar: RatingBar = view.findViewById(R.id.ratingBard)
        val image: ImageView = view.findViewById(R.id.product_image)
        val bg_image: ImageView = view.findViewById(R.id.image)
        val button: Button = view.findViewById(R.id.button)
        val sale: TextView = view.findViewById(R.id.sale_product)
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
        when(item[0]){
            "Zalastara"->{
                holder.price.text = _price[0]
            }
            "BioCetaNol"->{
                holder.price.text = _price[1]
            }
            "Lipastack"->{
                holder.price.text = _price[2]
            }
            "FreeBioGas"->{
                holder.price.text = _price[3]
            }
            "Alquno"->{
                holder.price.text = _price[4]
            }
            "SlimBioDave"->{
                holder.price.text = _price[5]
            }
            "Chirazam"->{
                holder.price.text = _price[6]
            }
            "Biolica", "Idealica"->{
                if(item[2].contains("_")){
                    val temp = item[2].split("_").toTypedArray()
                    holder.price.text = temp[0]
                    holder.price_last.visibility = View.VISIBLE
                    holder.price_last.text = Html.fromHtml("<s>" + temp[1] + "</s>")
                    holder.price.setTextColor(Color.parseColor("#FFFF6161"))
                    holder.sale.visibility = View.VISIBLE
                } else holder.price.text = item[2]
            }
        }

        holder.rating.text = item[3]
        holder.rating_bar.rating = item[3].toFloat()

        holder.button.setOnClickListener { onClickListener.onClicked(item) }

        if(item[4].contains("_")){
            val i = item[4].split("_")
            holder.image.setImageResource(context.resources.getIdentifier(i[0], "drawable", context.packageName))
        } else {
            holder.image.setImageResource(context.resources.getIdentifier(item[4], "drawable", context.packageName))
        }

        holder.image.setOnClickListener { onClickListener.onClicked(item) }
        holder.bg_image.setOnClickListener { onClickListener.onClicked(item) }
    }
    override fun getItemCount() = _item.size
}