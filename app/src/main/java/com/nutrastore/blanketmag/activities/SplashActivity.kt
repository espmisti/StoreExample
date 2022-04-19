package com.nutrastore.blanketmag.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nutrastore.blanketmag.R

class SplashActivity : AppCompatActivity() {
    private val model: SplashViewModel by lazy { ViewModelProvider(this)[SplashViewModel::class.java] }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        observeSplashLiveData()
    }
    private fun observeSplashLiveData() {
        model.initSplashScreen()
        val observer = Observer<String>{
            startActivity(Intent(this, MainActivity::class.java)
                .putExtra("GEO", model.GEO.value)
                .putExtra("IPAddress", model.ip.value)
                .putExtra("ProductsPopular", model.array_popular.value)
                .putExtra("Image", model.array_image.value)
                .putExtra("PriceOther", model.array_price.value)
                .putExtra("campaign", model.result.value)
            )
            finish()
        }
        model.result.observe(this, observer)
    }
}