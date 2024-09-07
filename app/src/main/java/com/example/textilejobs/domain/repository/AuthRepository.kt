package com.example.textilejobs.domain.repository

import androidx.credentials.GetCredentialResponse
import com.example.textilejobs.data.dto.auth.AuthResponseDTO
import com.example.textilejobs.core.utils.Resource
import com.example.textilejobs.data.dto.auth.LoginRequestDTO
import com.example.textilejobs.data.dto.auth.SignUpRequestDTO
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(loginRequestDTO: LoginRequestDTO): Flow<Resource<AuthResponseDTO>>
    suspend fun signUp(signUpRequestDTO: SignUpRequestDTO): Flow<Resource<AuthResponseDTO>>
    suspend fun continueWithGoogle(googleAuthId: String): Flow<Resource<AuthResponseDTO>>
}