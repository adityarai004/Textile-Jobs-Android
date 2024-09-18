package com.example.textilejobs.auth.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthResponseDTO(
    @SerialName("status")
    val success: Boolean? = null,
    @SerialName("message")
    val message: String? = null,
    @SerialName("data")
    val authData: AuthDataDTO? = null
)

@Serializable
data class AuthDataDTO(
    @SerialName("accessToken")
    val accessToken: String? = null,
    @SerialName("user")
    val user: UserDTO? = null
)

@Serializable
data class UserDTO(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("firstName")
    val firstName: String? = null,
    @SerialName("lastName")
    val lastName: String? = null,
    @SerialName("email")
    val email: String? = null,
    @SerialName("avatar")
    val profilePhoto: String? = null,
    @SerialName("role")
    // 0 -> Admin
    // 1 -> User
    // 2 -> Recruiter
    // 3 -> None
    val role: Int? = null,
    @SerialName("isActive")
    val isActive: Boolean? = null
)