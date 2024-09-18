package com.example.textilejobs.auth.data.repository

import com.example.textilejobs.auth.data.datasources.AuthService
import com.example.textilejobs.auth.data.auth.AuthResponseDTO
import com.example.textilejobs.core.utils.Resource
import com.example.textilejobs.auth.data.auth.LoginRequestDTO
import com.example.textilejobs.auth.data.auth.SignUpRequestDTO
import com.example.textilejobs.auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService
) :
    AuthRepository {
    override suspend fun login(loginRequestDTO: LoginRequestDTO): Flow<Resource<AuthResponseDTO>> =
        flow {
            emit(Resource.Loading)
            try {
                val response = authService.login(loginRequestDTO)
                if (response.success == true) {
                    emit(Resource.Success(response))
                } else {
                    emit(Resource.Error(response.message ?: "An error occurred while login"))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: "An error occurred while login"))
            }
        }

    override suspend fun signUp(signUpRequestDTO: SignUpRequestDTO): Flow<Resource<AuthResponseDTO>> =
        flow {
            emit(Resource.Loading)
            try {
                val response = authService.signup(signUpRequestDTO)
                if (response.success == true) {
                    emit(Resource.Success(response))
                } else {
                    emit(Resource.Error(response.message ?: "An error occurred while sign up"))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage ?: "An error occurred while sign up"))
            }
        }

    override suspend fun continueWithGoogle(googleAuthId: String, role: Int): Flow<Resource<AuthResponseDTO>> {
        return flow {
            emit(Resource.Loading)
            try {
                val response = authService.continueWithGoogle(googleAuthId,role)
                if (response.success == true) {
                    emit(Resource.Success(response))
                } else {
                    emit(Resource.Error(response.message ?: "An error occurred while login"))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage ?: "An error occurred while google Sign In"))
            }
        }
    }
}