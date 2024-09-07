package com.example.textilejobs.presentation.dashboard

sealed class DashboardEvent {
    data class OnChangeScreen(val index: Int): DashboardEvent()
}