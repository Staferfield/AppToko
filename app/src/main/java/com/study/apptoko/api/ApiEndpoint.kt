package com.study.apptoko.api

import com.study.apptoko.response.login.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

// Interface untuk pengiriman data HTTP
interface ApiEndpoint {
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email : String,
        @Field("password") password : String
    ) : Call<LoginResponse>

}