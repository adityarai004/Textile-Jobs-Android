package com.example.textilejobs.auth.domain.usecase

import com.example.textilejobs.auth.data.model.LoginRequestDTO
import com.example.textilejobs.auth.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(username: String, password: String) = authRepository.login(
        LoginRequestDTO(username, password)
    )
}