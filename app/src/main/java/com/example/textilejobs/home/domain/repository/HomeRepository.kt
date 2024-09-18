package com.example.textilejobs.home.domain.repository

import com.example.textilejobs.core.utils.Resource
import com.example.textilejobs.home.data.dto.JobListingRequestDTO
import com.example.textilejobs.home.data.dto.JobListingResponseDTO
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getJobList(jobListingRequestDTO: JobListingRequestDTO): Flow<Resource<JobListingResponseDTO>>
}