package com.nutrastore.blanketmag.screens.main

import android.content.Context
import android.os.Bundle
import android.telephony.TelephonyManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.nutrastore.blanketmag.R
import com.nutrastore.blanketmag.databinding.FragmentMainBinding
import com.nutrastore.blanketmag.utils.adapters.*

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var geo: TelephonyManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        geo = context?.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

        val adapter = ImageAdapter(context!!, activity?.intent?.getIntegerArrayListExtra("Image")!!, activity?.intent?.getIntegerArrayListExtra("ProductsPopular")!!)
        binding.viewpager.adapter=adapter
        binding.circleIndicator.setViewPager(binding.viewpager)

        return binding.root
    }
}