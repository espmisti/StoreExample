package com.store.example.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.store.example.R
import com.store.example.databinding.ActivitySplashBinding
import com.store.example.utils.Constants

class SplashActivity : AppCompatActivity() {
    private lateinit var model: SplashViewModel
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        initDataBinding()
        observeSplashLiveData()
    }
    private fun initViewModel(){
        model = ViewModelProvider(this)[SplashViewModel::class.java]
    }
    private fun initDataBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
    }
    private fun observeSplashLiveData() {
        model.initSplashScreen()
        val observer = Observer<String>{
            startActivity(Intent(this, MainActivity::class.java)
                .putExtra("GEO", model.liveData.value)
                .putExtra("IPAddress", model.ip.value)
                .putExtra("ProductsPopular", model.array.value)
            )
            finish()
        }
        model.liveData.observe(this, observer)
    }
}