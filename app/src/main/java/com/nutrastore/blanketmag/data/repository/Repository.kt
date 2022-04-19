package com.nutrastore.blanketmag.data.repository

import android.util.Log
import com.nutrastore.blanketmag.data.api.ApiService
import com.nutrastore.blanketmag.utils.Constants

class Repository {

    suspend fun getResponse(name: String, number: String, geo: String, ip: String, goods_id: Int, campaign: String){
        val sub: ArrayList<String>
        if(campaign.contains("_") && campaign != null) sub = campaign.split("_") as ArrayList<String>
        else sub = arrayListOf("nope", "nope", "nope", "nope", "nope")

        val response = ApiService.retrofit.sendData(Constants.API_KEY, goods_id, number, name, geo, ip, sub[0], sub[1], sub[2], sub[3], sub[4])
        if(response.isSuccessful){
            val result = response.body()!!
            Log.i("APP_CHECK", "[API]: $result")
        }
    }
}