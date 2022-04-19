package com.nutrastore.blanketmag.activities

import android.app.Application
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.telephony.TelephonyManager
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.nutrastore.blanketmag.utils.Constants
import kotlinx.coroutines.launch

import com.nutrastore.blanketmag.R
import com.onesignal.OneSignal
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.collections.ArrayList
import kotlin.coroutines.coroutineContext
import kotlin.coroutines.resume


class SplashViewModel(application: Application) : AndroidViewModel(application) {

    private val geo: TelephonyManager by lazy { application.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager }

    var GEO: MutableLiveData<String> = MutableLiveData()
    var ip: MutableLiveData<String> = MutableLiveData()
    var array_popular: MutableLiveData<ArrayList<Int>> = MutableLiveData()
    var array_image: MutableLiveData<ArrayList<Int>> = MutableLiveData()
    var array_price: MutableLiveData<Array<String>> = MutableLiveData()

    var result: MutableLiveData<String> = MutableLiveData()

    private var campaign: String? = null

    fun initSplashScreen(){
        viewModelScope.launch {
            initOneSignal(Constants.ONE_SIGNAL_APP_ID)
            awaitAppsflyer()
            if(geo.networkCountryIso.isNotEmpty()){
                when("pe"){
                    "ru" -> {
                        GEO.value = "ru"
                        if(campaign != ""){
                            array_popular.value = arrayListOf(R.array.product_mexico, R.array.product_zalastara, R.array.product_BioCetaNol)
                            array_image.value = arrayListOf(R.drawable.pe_idealica)
                        }

                    }
                    "pe" -> {
                        GEO.value = "pe"
                        array_price.value = arrayOf("512 Soles", "609 Soles", "1 024 Soles", "419 Soles", "711 Soles", "455 Soles", "612 Soles")
                        if(campaign != null){
                            array_popular.value = arrayListOf(R.array.product_peru_biolica, R.array.product_peru_idelica, R.array.product_FreeBioGas)
                            array_image.value = arrayListOf(R.drawable.pe_idealica, R.drawable.pe_biolica)
                        } else {
                            array_popular.value = arrayListOf(R.array.product_fake_1, R.array.product_fake_2)
                            array_image.value = arrayListOf(R.drawable.fake1, R.drawable.fake2)
                        }
                    }
                    "mx" -> {
                        GEO.value = "mx"
                        array_price.value = arrayOf("2 300 PESOS", "5 399 PESOS", "2 512 PESOS", " 2 999 PESOS", "3 152 PESOS", "4 912 PESOS", "2 111 PESOS")
                        array_popular.value = arrayListOf(R.array.product_mexico, R.array.product_zalastara, R.array.product_BioCetaNol)
                        array_image.value = arrayListOf(R.drawable.mx_idealica)
                    }
                    "co" -> {
                        GEO.value = "co"
                        array_price.value = arrayOf("\$309 231", "\$360 399", "\$512 999", "\$399 122", "\$499 999", "\$299 512", "\$441 222")
                        array_popular.value = arrayListOf(R.array.product_co_biolica, R.array.product_co_idelica, R.array.product_zalastara)
                        array_image.value = arrayListOf(R.drawable.co_idealica, R.drawable.co_biolica)
                    }
                    "cl" -> {
                        GEO.value = "cl"
                        array_price.value = arrayOf("89 999 PESOS", "99 232 PESOS", "121 922 PESOS", " 168 999 PESOS", "132 000 PESOS", "116 999 PESOS", "100 500 PESOS")
                        array_popular.value = arrayListOf(R.array.product_chilly_biolica, R.array.product_chilly_idelica, R.array.product_SlimBioDave)
                        array_image.value = arrayListOf(R.drawable.chilly_idealica, R.drawable.chilly_biolica)
                    }
                }
            } else {
                array_popular.value = arrayListOf(R.array.product_zalastara, R.array.product_SlimBioDave, R.array.product_Lipastack)
                Log.e(Constants.APP_CHECK, "[GEO]: Не удалось определить гео пользователя")
            }
            result.value = campaign
        }
    }
    suspend fun awaitAppsflyer() = suspendCancellableCoroutine<Unit> { cont ->
        if(campaign != "") {
            cont.resume(Unit)
            return@suspendCancellableCoroutine
        }
        val conversionListener: AppsFlyerConversionListener = object : AppsFlyerConversionListener {
            override fun onConversionDataSuccess(conversionData: Map<String, Any>) {
                cont.resume(Unit)
                campaign = conversionData["campaign"].toString()
                Log.i(Constants.APP_CHECK+"_APPSFLYER", "onConversionDataSuccess: $campaign")
            }
            override fun onConversionDataFail(errorMessage: String) {
                cont.resume(Unit)
                Log.d(Constants.APP_CHECK+"_APPSFLYER", "error getting conversion data: $errorMessage")
            }
            override fun onAppOpenAttribution(attributionData: Map<String, String>) { for (attrName in attributionData.keys) { Log.d(Constants.APP_CHECK+"_APPSFLYER", "attribute: " + attrName + " = " + attributionData[attrName]) } }
            override fun onAttributionFailure(errorMessage: String) { Log.d(Constants.APP_CHECK+"_APPSFLYER", "error onAttributionFailure : $errorMessage") }
        }
        AppsFlyerLib.getInstance().init(Constants.AF_KEY, conversionListener, getApplication())
        AppsFlyerLib.getInstance().start(getApplication())
    }

    private fun initOneSignal(value: String){
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        OneSignal.initWithContext(getApplication())
        OneSignal.setAppId(value)
    }
}