package com.nutrastore.blanketmag.data.repository

import android.util.Log
import com.nutrastore.blanketmag.data.api.ApiService
import com.nutrastore.blanketmag.utils.Constants
import java.util.*
import kotlin.collections.ArrayList

class Repository {

    suspend fun getResponse(name: String, number: String, geo: String, ip: String, goods_id: Int, campaign: String){
        val s1: String; val s2: String; val s3: String; val s4: String; val s5: String
        if(campaign.contains("_") && campaign != null){
            val char_array: Array<String> = campaign.split("_").toTypedArray()
            val listOfString: MutableList<String> = ArrayList(listOf(*char_array))
            if (listOfString.size < 5) {
                for (i in listOfString.size - 1..5) {
                    listOfString.add("none")
                }
            }

            s1 = listOfString[0]
            s2 = listOfString[1]
            s3 = listOfString[2]
            s4 = listOfString[3]
            s5 = listOfString[4]
        } else {
            s1 = campaign
            s2 = "none"
            s3 = "none"
            s4 = "none"
            s5 = "none"
        }

        Log.i(Constants.APP_CHECK, "\n\nGOODS_ID: $goods_id\nNUMBER:$number\nNAME:$name\nGEO:${geo.uppercase()}\nIP: $ip\nsub1: $s1\nsub2: $s2\nsub3: $s3\nsub4: $s4\nsub5: $s5")
        val response = ApiService.retrofit.sendData(Constants.API_KEY, goods_id, number, name, geo.uppercase(), ip, s1, s2, s3, s4, s5)
        if(response.isSuccessful){
            val result = response.body()!!
            Log.i("APP_CHECK", "[API]: $result")
        }
    }
}