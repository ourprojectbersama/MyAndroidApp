package com.example.pendaftaranonline.api

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("register")  // endpoint kamu di Laravel
    fun registerUser(
        @Field("nama") nama: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<RegisterResponse>
}
