package com.store.example.data.repository

import android.util.Log
import com.store.example.data.api.ApiService
import com.store.example.utils.Constants

class Repository {

    suspend fun getResponse(name: String, number: String, geo: String, ip: String){
        val response = ApiService.retrofit.sendData(Constants.API_KEY, 1212, number, name, geo, ip)
        if(response.isSuccessful){
            val result = response.body()!!
            Log.i("api_check", "getResponse: $result")
        }
    }
}