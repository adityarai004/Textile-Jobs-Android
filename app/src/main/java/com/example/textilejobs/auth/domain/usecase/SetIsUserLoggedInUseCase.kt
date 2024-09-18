package com.example.textilejobs.auth.domain.usecase

import com.example.textilejobs.core.data.repository.UserDataRepository
import javax.inject.Inject

class SetIsUserLoggedInUseCase @Inject constructor(private val userPrefsRepository: UserDataRepository ) {
    suspend operator fun invoke(value: Boolean) = userPrefsRepository.setIsUserLoggedIn(value)
}