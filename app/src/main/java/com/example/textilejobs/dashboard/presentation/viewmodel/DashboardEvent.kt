package com.example.textilejobs.dashboard.presentation.viewmodel

sealed class DashboardEvent {
    data class OnChangeScreen(val index: Int): DashboardEvent()
}