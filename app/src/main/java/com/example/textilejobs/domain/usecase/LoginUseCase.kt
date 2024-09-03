package com.example.textilejobs.domain.usecase

import com.example.textilejobs.data.dto.auth.LoginRequestDTO
import com.example.textilejobs.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(username: String, password: String) = authRepository.login(
        LoginRequestDTO(username, password)
    )
}