package com.example.textilejobs.data.dto.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthResponseDTO(
    @SerialName("success")
    val success: Boolean,
    @SerialName("message")
    val message: String,
)