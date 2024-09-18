package com.example.textilejobs.core.use_case

import com.example.textilejobs.core.data.repository.UserDataRepository
import javax.inject.Inject

class GetIsUserLoggedInUseCase @Inject constructor(private val userDataRepository: UserDataRepository) {
    suspend operator fun invoke() = userDataRepository.getIsUserLoggedIn()
}