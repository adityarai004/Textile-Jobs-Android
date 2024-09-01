package com.example.textilejobs.presentation.auth.login.state

sealed class LoginUiEvent {
    data class EmailChanged(val inputValue: String) : LoginUiEvent()
    data class PasswordChanged(val inputValue: String) : LoginUiEvent()
    data object Submit : LoginUiEvent()
}