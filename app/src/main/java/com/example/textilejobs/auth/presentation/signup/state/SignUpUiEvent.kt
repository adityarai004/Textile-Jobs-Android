package com.example.textilejobs.auth.presentation.signup.state

import android.net.Uri

sealed class SignUpUiEvent{
    data class FirstNameChanged(val newValue: String) : SignUpUiEvent()
    data class MobileNoChanged(val newValue: String) : SignUpUiEvent()
    data class EmailChanged(val newEmailValue: String) : SignUpUiEvent()
    data class PasswordChanged(val newPasswordValue: String): SignUpUiEvent()
    data class ConfirmPasswordChanged(val newConfirmPasswordChanged: String): SignUpUiEvent()
    data class SignUpClick(val isCompany: Boolean): SignUpUiEvent()
    data class PickedImage(val uri: Uri): SignUpUiEvent()
}