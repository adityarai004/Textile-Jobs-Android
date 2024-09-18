package com.example.textilejobs.auth.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequestDTO(
    @SerialName("firstName")
    val firstName: String,
    @SerialName("email")
    val email: String,
    @SerialName("password")
    val password: String,
    @SerialName("avatar")
    val avatar: String = "https://masti.com",
    @SerialName("phoneNo")
    val mobileNumber: String,
    @SerialName("role")
    val role: Int
)