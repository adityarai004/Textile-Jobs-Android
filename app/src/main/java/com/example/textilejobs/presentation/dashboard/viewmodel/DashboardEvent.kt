package com.example.textilejobs.presentation.dashboard.viewmodel

sealed class DashboardEvent {
    data class OnChangeScreen(val index: Int): DashboardEvent()
}