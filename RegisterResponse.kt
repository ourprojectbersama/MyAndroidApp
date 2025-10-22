package com.example.pendaftaranonline

data class RegisterResponse(
    val success: Boolean,
    val message: String,
    val data: UserData? = null
)

data class UserData(
    val id: Int,
    val nama: String,
    val email: String
)
