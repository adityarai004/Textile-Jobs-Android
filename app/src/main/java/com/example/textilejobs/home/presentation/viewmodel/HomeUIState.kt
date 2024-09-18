package com.example.textilejobs.home.presentation.viewmodel

data class HomeUIState(
    val loadingJobs: Boolean = true,
    val homeError: Boolean = false,
    val profileCompletionReminder: Boolean = false,
)