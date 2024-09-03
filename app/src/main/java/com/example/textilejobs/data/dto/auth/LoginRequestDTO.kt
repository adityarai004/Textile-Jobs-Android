package com.example.textilejobs.data.dto.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestDTO(
    @SerialName("email")
    val email: String,
    @SerialName("password")
    val password: String,
)