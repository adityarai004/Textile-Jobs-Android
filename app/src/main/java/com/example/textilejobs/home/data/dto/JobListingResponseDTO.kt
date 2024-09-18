package com.example.textilejobs.home.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JobListingResponseDTO(
    @SerialName("status")
    val success: Boolean? = null,
    @SerialName("message")
    val message: String? = null,
    @SerialName("data")
    val jobData: JobDataDTO? = null
)

@Serializable
data class JobDataDTO(
    @SerialName("jobs")
    val jobs: List<JobDTO>? = null,
    @SerialName("totalCount")
    val totalCount: Int? = null // Total number of jobs for pagination
)

@Serializable
data class JobDTO(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("company")
    val company: CompanyDTO? = null,
    @SerialName("location")
    val location: String? = null,
    @SerialName("salary")
    val salary: String? = null,  // This can also be a more complex type depending on salary format (e.g., range, currency)
    @SerialName("jobType")
    val jobType: String? = null, // e.g., "Full-time", "Part-time"
    @SerialName("isRemote")
    val isRemote: Boolean? = null,
    @SerialName("postedAt")
    val postedAt: String? = null, // Date when the job was posted
    @SerialName("description")
    val description: String? = null
)

@Serializable
data class CompanyDTO(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("logo")
    val logoUrl: String? = null
)
