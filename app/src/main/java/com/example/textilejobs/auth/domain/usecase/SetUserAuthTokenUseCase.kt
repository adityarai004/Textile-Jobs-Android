package com.example.textilejobs.auth.domain.usecase

import com.example.textilejobs.core.data.repository.UserDataRepository
import javax.inject.Inject

class SetUserAuthTokenUseCase @Inject constructor(private val userPrefsRepository: UserDataRepository) {
    suspend operator fun invoke(token: String) = userPrefsRepository.setUserAuthKey(token)
}