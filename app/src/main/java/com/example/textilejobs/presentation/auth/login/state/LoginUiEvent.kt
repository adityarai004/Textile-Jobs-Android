package com.example.textilejobs.presentation.auth.login.state

import androidx.credentials.GetCredentialResponse

sealed class LoginUiEvent {
    data class EmailChanged(val inputValue: String) : LoginUiEvent()
    data class PasswordChanged(val inputValue: String) : LoginUiEvent()
    data object GoogleSignInTap : LoginUiEvent()
    data class HandleGoogleAuth(val result: GetCredentialResponse) : LoginUiEvent()
    data object Submit : LoginUiEvent()
    data object GoogleSignInFailed: LoginUiEvent()
}