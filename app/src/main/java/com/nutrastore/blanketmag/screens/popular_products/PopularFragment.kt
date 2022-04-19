package com.nutrastore.blanketmag.screens.popular_products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nutrastore.blanketmag.R
import com.nutrastore.blanketmag.databinding.FragmentPopularBinding
import com.nutrastore.blanketmag.utils.adapters.OnClickListener
import com.nutrastore.blanketmag.utils.adapters.ProductAdapter

class PopularFragment : Fragment() {
    private lateinit var binding: FragmentPopularBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_popular, container, false)

        val lm = LinearLayoutManager(context)
        lm.orientation = LinearLayoutManager.HORIZONTAL
        binding.popularRV.layoutManager = lm

        val array = activity?.intent?.getIntegerArrayListExtra("ProductsPopular")
        val price_array = activity?.intent?.getStringArrayExtra("PriceOther")

        binding.popularRV.isNestedScrollingEnabled = false
        binding.popularRV.adapter = ProductAdapter(array!!, price_array!!, object : OnClickListener{
            override fun onClicked(array_string: Array<String>?, array_image: Array<Int>?) {
                val bundle = Bundle()
                bundle.putStringArray("product", array_string)
                findNavController().navigate(R.id.productFragment, bundle)
            }
        }, context!!)
        return binding.root
    }
}