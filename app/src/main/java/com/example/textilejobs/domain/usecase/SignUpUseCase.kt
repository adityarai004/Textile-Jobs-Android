package com.example.textilejobs.domain.usecase

import com.example.textilejobs.data.dto.auth.AuthResponseDTO
import com.example.textilejobs.core.utils.Resource
import com.example.textilejobs.data.dto.auth.SignUpRequestDTO
import com.example.textilejobs.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(
        name: String,
        email: String,
        password: String,
        mobileNumber: String,
        role: Int
    ): Flow<Resource<AuthResponseDTO>> {
        val signUpRequestDTO = SignUpRequestDTO(name, email, password, "https://www.google.com", mobileNumber, role)
        return authRepository.signUp(signUpRequestDTO)
    }
}