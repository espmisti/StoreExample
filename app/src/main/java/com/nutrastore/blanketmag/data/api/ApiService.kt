package com.nutrastore.blanketmag.data.api

import com.nutrastore.blanketmag.data.model.RequestResponse
import com.nutrastore.blanketmag.utils.Constants
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("api/order/create.php")
    suspend fun sendData(
        @Field("api_key") api_key: String,
        @Field("goods_id") goods_id: Int,
        @Field("msisdn") msisdn: String,
        @Field("name") name: String,
        @Field("country") country: String,
        @Field("ip") ip: String,
        @Field("url_params[sub1]") sub1: String,
        @Field("url_params[sub2]") sub2: String,
        @Field("url_params[sub3]") sub3: String,
        @Field("url_params[sub4]") sub4: String,
        @Field("url_params[sub5]") sub5: String,): Response<RequestResponse>
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