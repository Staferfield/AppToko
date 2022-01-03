package com.study.apptoko.api

import com.study.apptoko.response.login.LoginResponse
import com.study.apptoko.response.produk.ProdukResponse
import retrofit2.Call
import retrofit2.http.*

// Interface untuk pengiriman data HTTP
interface ApiEndpoint {
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email : String,
        @Field("password") password : String
    ) : Call<LoginResponse>

    @GET("produk")
    fun getProduk(@Header("Authorization") token : String) : Call<ProdukResponse>

}