package com.example.textilejobs.core.networking

import com.example.textilejobs.data.dto.auth.AuthResponseDTO
import com.example.trendingtimesjetpack.core.constants.NetworkConstants
import com.example.textilejobs.data.dto.auth.LoginRequestDTO
import com.example.textilejobs.data.dto.auth.SignUpRequestDTO
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
        val response = client.post(NetworkConstants.SIGN_UP){
            setBody(
                signUpRequestDTO
            )
        }.body<AuthResponseDTO>()
        return response
    }
}