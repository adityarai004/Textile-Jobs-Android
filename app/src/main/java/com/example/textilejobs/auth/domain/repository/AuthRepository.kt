package com.example.textilejobs.auth.domain.repository

import com.example.textilejobs.auth.data.auth.AuthResponseDTO
import com.example.textilejobs.core.utils.Resource
import com.example.textilejobs.auth.data.auth.LoginRequestDTO
import com.example.textilejobs.auth.data.auth.SignUpRequestDTO
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(loginRequestDTO: LoginRequestDTO): Flow<Resource<AuthResponseDTO>>
    suspend fun signUp(signUpRequestDTO: SignUpRequestDTO): Flow<Resource<AuthResponseDTO>>
    suspend fun continueWithGoogle(googleAuthId: String, role: Int): Flow<Resource<AuthResponseDTO>>
}