package com.store.example.screens.all_products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.store.example.R
import com.store.example.databinding.FragmentAllProductsBinding
import com.store.example.utils.adapters.OnClickListener
import com.store.example.utils.adapters.ProductAdapter

class AllProductsFragment : Fragment() {
    private lateinit var binding: FragmentAllProductsBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_all_products, container, false)

        val array = arrayListOf(R.array.product_4,R.array.product_5,R.array.product_6,R.array.product_7,R.array.product_8)
        val gridLayoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)

        binding.allProducts.layoutManager = gridLayoutManager
        binding.allProducts.isNestedScrollingEnabled = false
        binding.allProducts.adapter = ProductAdapter(array, object : OnClickListener{
            override fun onClicked(array: Array<String>) {
                val bundle = Bundle()
                bundle.putStringArray("product", array)
                findNavController().navigate(R.id.productFragment, bundle)
            }
        }, context!!)
        return binding.root
    }
}