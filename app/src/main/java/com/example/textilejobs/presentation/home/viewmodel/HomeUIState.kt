package com.example.textilejobs.presentation.home.viewmodel

data class HomeUIState(
    val loadingJobs: Boolean = true,
    val homeError: Boolean = false,
    val profileCompletionReminder: Boolean = false,
)