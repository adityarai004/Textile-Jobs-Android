package com.example.textilejobs.core.network.ktor

import com.example.textilejobs.core.constants.NetworkConstants
import com.example.textilejobs.home.data.dto.JobListingRequestDTO
import com.example.textilejobs.home.data.dto.JobListingResponseDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.parameters
import javax.inject.Inject

class HomeService @Inject constructor(private val client: HttpClient) {

    suspend fun fetchJobs(jobListingRequestDTO: JobListingRequestDTO): JobListingResponseDTO {
        val response = client.get(NetworkConstants.JOB_LISTING) {
            parameters {
                append("page", jobListingRequestDTO.page.toString())
                append("perPage", jobListingRequestDTO.perPage.toString())
            }
        }.body<JobListingResponseDTO>()
        return response
    }

}