package com.example.textilejobs.auth.data.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestDTO(
    @SerialName("email")
    val email: String,
    @SerialName("password")
    val password: String,
)