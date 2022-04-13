package com.store.example.activities

import android.app.Application
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.CountDownTimer
import android.telephony.TelephonyManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatViewInflater
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.creative.ipfyandroid.Ipfy
import com.creative.ipfyandroid.IpfyClass
import com.store.example.utils.Constants
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import android.content.Context.WIFI_SERVICE

import androidx.core.content.ContextCompat.getSystemService

import android.net.wifi.WifiManager
import android.text.format.Formatter.formatIpAddress
import androidx.core.content.ContextCompat
import com.store.example.R
import java.util.*
import kotlin.collections.ArrayList


class SplashViewModel(application: Application) : AndroidViewModel(application) {

    private val geo: TelephonyManager by lazy { application.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager }
    private val mPrefs: SharedPreferences by lazy { application.getSharedPreferences("setting", MODE_PRIVATE) }

    var liveData: MutableLiveData<String> = MutableLiveData()
    var ip: MutableLiveData<String> = MutableLiveData()
    var array: MutableLiveData<ArrayList<Int>> = MutableLiveData()

    private var GEO = ""
    private var IP = ""

    fun initSplashScreen(){
        viewModelScope.launch {
            if(geo.networkCountryIso.isNotEmpty()){
                when(geo.networkCountryIso){
                    "ru" -> {
                        array.value = arrayListOf(R.array.product_mexico, R.array.product_mexico, R.array.product_mexico)
                    }
                    "pe" -> {
                        array.value = arrayListOf(R.array.product_peru, R.array.product_peru_2, R.array.product_1)
                    }
                    "mx" -> {
                        array.value = arrayListOf(R.array.product_mexico, R.array.product_1, R.array.product_2)
                    }
                    "co" -> {
                        array.value = arrayListOf(R.array.product_mexico, R.array.product_mexico, R.array.product_mexico)
                    }
                    "cl" -> {
                        array.value = arrayListOf(R.array.product_mexico, R.array.product_mexico, R.array.product_mexico)
                    }
                }
            } else {
                Log.e(Constants.APP_CHECK, "[GEO]: Не удалось определить гео пользователя")
            }
            updateLiveData()
        }
    }
    private fun updateLiveData(){
        liveData.value = GEO
        ip.value = IP
    }
    private fun appsflyerInit(){
        val conversionListener: AppsFlyerConversionListener = object : AppsFlyerConversionListener {
            override fun onConversionDataSuccess(conversionData: Map<String, Any>) {
                Log.i(Constants.APP_CHECK+"_APPSFLYER", "onConversionDataSuccess: ")
            }

            override fun onConversionDataFail(errorMessage: String) { Log.d(Constants.APP_CHECK+"_APPSFLYER", "error getting conversion data: $errorMessage") }
            override fun onAppOpenAttribution(attributionData: Map<String, String>) { for (attrName in attributionData.keys) { Log.d(Constants.APP_CHECK+"_APPSFLYER", "attribute: " + attrName + " = " + attributionData[attrName]) } }
            override fun onAttributionFailure(errorMessage: String) { Log.d(Constants.APP_CHECK+"_APPSFLYER", "error onAttributionFailure : $errorMessage") }
        }
        AppsFlyerLib.getInstance().init(Constants.AF_KEY, conversionListener, getApplication())
        AppsFlyerLib.getInstance().start(getApplication())
    }
}