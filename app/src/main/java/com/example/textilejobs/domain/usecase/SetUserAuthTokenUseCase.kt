package com.example.textilejobs.domain.usecase

import com.example.textilejobs.domain.repository.LocalPrefsRepository
import javax.inject.Inject

class SetUserAuthTokenUseCase @Inject constructor(private val localPrefsRepository: LocalPrefsRepository) {
    suspend operator fun invoke(token: String) = localPrefsRepository.setUserAuthKey(token)
}