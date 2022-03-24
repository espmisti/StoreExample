package com.store.example.screens.main

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Context.WIFI_SERVICE
import android.content.DialogInterface
import android.net.wifi.WifiManager
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.store.example.R
import com.store.example.databinding.FragmentMainBinding
import com.store.example.repository.Repository
import com.store.example.utils.adapters.*
import kotlinx.coroutines.runBlocking
import java.net.Inet4Address
import java.net.InetAddress
import java.util.*
import java.util.logging.Formatter
import kotlin.concurrent.thread

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var geo: TelephonyManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        geo = context?.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

        val array = arrayListOf(R.drawable.test_image2, R.drawable.test_image3)
        val adapter = ImageAdapter(context!!)
        adapter.setContentList(array)
        binding.viewpager.adapter=adapter

        return binding.root
    }

}