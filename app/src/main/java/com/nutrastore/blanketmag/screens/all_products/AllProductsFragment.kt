package com.nutrastore.blanketmag.screens.all_products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.nutrastore.blanketmag.R
import com.nutrastore.blanketmag.databinding.FragmentAllProductsBinding
import com.nutrastore.blanketmag.utils.adapters.OnClickListener
import com.nutrastore.blanketmag.utils.adapters.ProductAdapter

class AllProductsFragment : Fragment() {
    private lateinit var binding: FragmentAllProductsBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_all_products, container, false)

        val arrayList: ArrayList<Int>
        if(activity?.intent?.getStringExtra("campaign") != null){
            arrayList = arrayListOf(
                R.array.product_zalastara,
                R.array.product_BioCetaNol,
                R.array.product_Lipastack,
                R.array.product_FreeBioGas,
                R.array.product_Alquno,
                R.array.product_SlimBioDave,
                R.array.product_Chirazam)
        } else {
            arrayList = arrayListOf(R.array.product_fake_1, R.array.product_fake_2)
        }


        val gridLayoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        val price_array = activity?.intent?.getStringArrayExtra("PriceOther")

        binding.allProducts.layoutManager = gridLayoutManager
        binding.allProducts.isNestedScrollingEnabled = false
        binding.allProducts.adapter = ProductAdapter(arrayList, price_array!!, object : OnClickListener{
            override fun onClicked(array_string: Array<String>?, array_image: Array<Int>?) {
                val bundle = Bundle()
                bundle.putStringArray("product", array_string)
                findNavController().navigate(R.id.productFragment, bundle)
            }
        }, requireContext())
        return binding.root
    }

    override fun onPause() {
        super.onPause()
        binding.allProducts.adapter = null
    }
}