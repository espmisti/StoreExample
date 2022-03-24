package com.store.example.screens.popular_products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.store.example.R
import com.store.example.databinding.FragmentPopularBinding
import com.store.example.utils.adapters.OnClickListener
import com.store.example.utils.adapters.ProductAdapter

class PopularFragment : Fragment() {
    private lateinit var binding: FragmentPopularBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_popular, container, false)

        val array = arrayListOf(R.array.product_1, R.array.product_2, R.array.product_3)
        val lm = LinearLayoutManager(context)
        lm.orientation = LinearLayoutManager.HORIZONTAL
        binding.popularRV.layoutManager = lm

        binding.popularRV.isNestedScrollingEnabled = false
        binding.popularRV.adapter = ProductAdapter(array, object : OnClickListener{
            override fun onClicked(array: Array<String>) {
                val bundle = Bundle()
                bundle.putStringArray("product", array)
                findNavController().navigate(R.id.productFragment, bundle)
            }
        }, context!!)
        return binding.root
    }
}