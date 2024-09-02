package com.example.textilejobs.data.dto.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthResponseDTO(
    @SerialName("status")
    val success: Boolean,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val authData: AuthDataDTO?
)

@Serializable
data class AuthDataDTO(
    @SerialName("accessToken")
    val accessToken: String?,
    @SerialName("user")
    val user: UserDTO?
)

@Serializable
data class UserDTO(
    @SerialName("id")
    val id: Int?,
    @SerialName("firstName")
    val firstName: String?,
    @SerialName("lastName")
    val lastName: String?,
    @SerialName("email")
    val email: String?,
    @SerialName("avatar")
    val profilePhoto: String?,
    @SerialName("role")
    val role: String?
)