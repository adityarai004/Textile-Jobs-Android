package com.example.textilejobs.domain.usecase

import com.example.textilejobs.core.utils.Resource
import com.example.textilejobs.data.dto.auth.AuthResponseDTO
import com.example.textilejobs.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignInWithGoogleUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(googleAuthId: String, role: Int): Flow<Resource<AuthResponseDTO>> {
        return authRepository.continueWithGoogle(googleAuthId,role);
    }
}