package com.store.example.data.api

import com.store.example.data.model.RequestResponse
import com.store.example.utils.Constants
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("api/order/create.php")
    suspend fun sendData(@Field("api_key") api_key: String,
                         @Field("goods_id") goods_id: Int,
                         @Field("msisdn") msisdn: String,
                         @Field("name") name: String,
                         @Field("country") country: String,
                         @Field("ip") ip: String): Response<RequestResponse>
    companion object {
        val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}