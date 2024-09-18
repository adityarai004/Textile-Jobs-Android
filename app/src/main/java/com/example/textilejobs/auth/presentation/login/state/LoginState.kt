package com.example.textilejobs.auth.presentation.login.state

import com.example.textilejobs.R
import com.example.textilejobs.core.utils.ErrorState


data class LoginState(
    val email: String = "",
    val password: String = "",
    val errorState: LoginErrorState = LoginErrorState(),
    val isLoginSuccessful: Boolean = false,
    val loginInProgress: Boolean = false,
    val isLoginError: Boolean = false,
    val loginErrorString: String = "",
    val continueWithGoogleInProgress: Boolean = false,
    val continueWithGoogleError: Boolean = false,
    val isCompany: Boolean = false,
    val dialogState: DialogState = DialogState()
)

data class LoginErrorState(
    val emailOrMobileErrorState: ErrorState = ErrorState(),
    val passwordErrorState: ErrorState = ErrorState()
)

data class DialogState(
    val userType: List<Int> = listOf(R.string.job_seeker, R.string.recruiter),
    val selectedUserType: Int = 0,
    val showDialog: Boolean = false
)