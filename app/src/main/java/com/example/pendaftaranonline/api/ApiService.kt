package com.example.pendaftaranonline.api

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<AuthResponse>

    @FormUrlEncoded
    @POST("simpanDataDiri")
    fun simpanDataDiri(
        @Field("nama") nama: String,
        @Field("nik") nik: String,
        @Field("alamat") alamat: String,
        @Field("hp") hp: String,
        @Field("tanggal_lahir") tanggalLahir: String,
        @Field("jenis_kelamin") jenisKelamin: String
    ): Call<BasicResponse>

}
