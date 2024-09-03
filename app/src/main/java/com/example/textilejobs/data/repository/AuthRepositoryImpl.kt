package com.example.textilejobs.data.repository

import com.example.textilejobs.core.networking.AuthService
import com.example.textilejobs.data.dto.auth.AuthResponseDTO
import com.example.textilejobs.core.utils.Resource
import com.example.textilejobs.data.dto.auth.LoginRequestDTO
import com.example.textilejobs.data.dto.auth.SignUpRequestDTO
import com.example.textilejobs.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authService: AuthService):
    AuthRepository {
    override suspend fun login(loginRequestDTO: LoginRequestDTO): Flow<Resource<AuthResponseDTO>> = flow{
        emit(Resource.Loading)
        try {
            val response = authService.login(loginRequestDTO)
            emit(Resource.Success(response))
        }catch (e: Exception){
            emit(Resource.Error(e.message ?: "An error occurred while login"))
        }
    }

    override suspend fun signUp(signUpRequestDTO: SignUpRequestDTO): Flow<Resource<AuthResponseDTO>> = flow {
        emit(Resource.Loading)
        try {
            val response = authService.signup(signUpRequestDTO)
            emit(Resource.Success(response))
        } catch (e: Exception){
            emit(Resource.Error(e.message ?: "An error occurred while sign up"))
        }
    }
}