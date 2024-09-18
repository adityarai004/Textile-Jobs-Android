package com.example.textilejobs.home.domain.usecase

import com.example.textilejobs.home.data.dto.JobListingRequestDTO
import com.example.textilejobs.home.domain.repository.HomeRepository
import javax.inject.Inject

class GetJobListingUseCase @Inject constructor(private val homeRepository: HomeRepository) {
    suspend operator fun invoke(page: Int, perPage: Int) = homeRepository.getJobList(
        JobListingRequestDTO(page, perPage)
    )
}