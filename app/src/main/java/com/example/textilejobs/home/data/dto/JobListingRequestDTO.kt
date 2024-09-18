package com.example.textilejobs.home.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JobListingRequestDTO(
    @SerialName("page")
    val page: Int,
    @SerialName("perPage")
    val perPage: Int,
)