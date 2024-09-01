package com.example.textilejobs.presentation.auth.signup.state

import android.net.Uri

sealed class SignUpUiEvent{
    data class FirstNameChanged(val newValue: String) : SignUpUiEvent()
    data class LastNameChanged(val newValue: String) : SignUpUiEvent()
    data class EmailChanged(val newEmailValue: String) : SignUpUiEvent()
    data class PasswordChanged(val newPasswordValue: String): SignUpUiEvent()
    data class ConfirmPasswordChanged(val newConfirmPasswordChanged: String): SignUpUiEvent()
    data object SignUpClick: SignUpUiEvent()
    data class PickedImage(val uri: Uri): SignUpUiEvent()
}