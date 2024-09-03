package com.example.textilejobs.domain.usecase

import com.example.textilejobs.domain.repository.LocalPrefsRepository
import javax.inject.Inject

class SetStringUseCase @Inject constructor(private val localPrefsRepository: LocalPrefsRepository) {
    suspend operator fun invoke(key: String, value: String) = localPrefsRepository.setString(key, value)
}