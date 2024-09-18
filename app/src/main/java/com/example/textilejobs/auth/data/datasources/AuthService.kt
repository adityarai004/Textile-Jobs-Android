package com.example.textilejobs.auth.data.datasources

import com.example.textilejobs.auth.data.auth.AuthResponseDTO
import com.example.textilejobs.core.constants.NetworkConstants
import com.example.textilejobs.auth.data.auth.LoginRequestDTO
import com.example.textilejobs.auth.data.auth.SignUpRequestDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import javax.inject.Inject

class AuthService @Inject constructor(private val client: HttpClient) {

    suspend fun login(loginRequestDTO: LoginRequestDTO): AuthResponseDTO {
        val response = client.post(NetworkConstants.LOGIN) {
            setBody(
                loginRequestDTO
            )
        }.body<AuthResponseDTO>()
        return response
    }

    suspend fun signup(signUpRequestDTO: SignUpRequestDTO): AuthResponseDTO {
        val response = client.post(NetworkConstants.SIGN_UP) {
            setBody(
                signUpRequestDTO
            )
        }.body<AuthResponseDTO>()
        return response
    }

    suspend fun continueWithGoogle(googleAuthId: String, role: Int): AuthResponseDTO {
        val response = client.post(NetworkConstants.CONTINUE_WITH_GOOGLE) {
            setBody(
                mapOf(
                    "idToken" to googleAuthId,
                    "role" to role
                )
            )
        }.body<AuthResponseDTO>()
        return response
    }
}