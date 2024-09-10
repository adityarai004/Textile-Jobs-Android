package com.example.textilejobs.presentation.auth.signup.state

import android.net.Uri
import com.example.textilejobs.core.utils.ErrorState

data class SignUpState(
    val email: String = "",
    val name: String = "",
    val mobileNumber: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isSignUpSuccessful: Boolean = false,
    val signUpInProgress: Boolean = false,
    val isSignUpError: Boolean = false,
    val signUpErrorString: String = "",
    val errorState: SignUpErrorState = SignUpErrorState(),
    val isDobDialogOpen: Boolean = false,
    val pickedPhoto: Uri? = null
)

data class SignUpErrorState(
    val emailErrorState: ErrorState = ErrorState(),
    val firstNameErrorState: ErrorState = ErrorState(),
    val lastNameErrorState: ErrorState = ErrorState(),
    val mobileNumberErrorState: ErrorState = ErrorState(),
    val passwordErrorState: ErrorState = ErrorState(),
    val confirmPasswordErrorState: ErrorState = ErrorState()
)