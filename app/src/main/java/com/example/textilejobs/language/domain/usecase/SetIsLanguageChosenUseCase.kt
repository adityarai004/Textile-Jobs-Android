package com.example.textilejobs.language.domain.usecase

import com.example.textilejobs.core.data.repository.UserDataRepository
import javax.inject.Inject

class SetIsLanguageChosenUseCase @Inject constructor(private val userDataRepository: UserDataRepository) {
    suspend operator fun invoke(isLanguageChosen: Boolean) = userDataRepository.setIsLanguageChosen(isLanguageChosen)
}