package com.example.textilejobs.presentation.dashboard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DashboardViewModel : ViewModel() {
    private val _dashboardState = MutableStateFlow(DashboardState())
    val dashboardState = _dashboardState.asStateFlow()

    fun onSubmitEvent(event: DashboardEvent){
        when(event){
            is DashboardEvent.OnChangeScreen -> {
                _dashboardState.update { newState ->
                    newState.copy(selectedScreen = event.index)
                }
            }
        }
    }
}