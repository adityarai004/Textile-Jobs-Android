package com.example.textilejobs.home.presentation.viewmodel

import com.example.textilejobs.home.data.dto.JobDTO

data class HomeUIState(
    val loadingJobs: Boolean = true,
    val homeError: Boolean = false,
    val homeErrorString: String = "",
    val profileCompletionReminder: Boolean = false,
    val currentPage: Int = 1,
    val jobsList: List<JobDTO> = emptyList()
)