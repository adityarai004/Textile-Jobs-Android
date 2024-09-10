package com.example.textilejobs.presentation.auth.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialResponse
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.textilejobs.core.constants.LocalPrefsConstants.PROFILE_COMPLETION_STATUS
import com.example.textilejobs.core.constants.RegexConstants
import com.example.textilejobs.core.utils.ErrorState
import com.example.textilejobs.core.utils.Resource
import com.example.textilejobs.data.dto.auth.AuthResponseDTO
import com.example.textilejobs.domain.usecase.LoginUseCase
import com.example.textilejobs.domain.usecase.SetIntUseCase
import com.example.textilejobs.domain.usecase.SetUserAuthTokenUseCase
import com.example.textilejobs.domain.usecase.SignInWithGoogleUseCase
import com.example.textilejobs.presentation.auth.emailEmptyErrorState
import com.example.textilejobs.presentation.auth.invalidEmailErrorState
import com.example.textilejobs.presentation.auth.login.state.LoginErrorState
import com.example.textilejobs.presentation.auth.login.state.LoginState
import com.example.textilejobs.presentation.auth.login.state.LoginUiEvent
import com.example.textilejobs.presentation.auth.passwordEmptyErrorState
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val setUserAuthTokenUseCase: SetUserAuthTokenUseCase,
    private val setIntUseCase: SetIntUseCase,
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase
) : ViewModel() {
    var loginState = mutableStateOf(LoginState())
        private set

    fun resetLoginError() {
        loginState.value =
            loginState.value.copy(isLoginError = false, continueWithGoogleError = false)
    }

    fun onUiEvent(loginUiEvent: LoginUiEvent) {
        when (loginUiEvent) {
            is LoginUiEvent.EmailChanged -> {
                loginState.value = loginState.value.copy(
                    email = loginUiEvent.inputValue,
                    errorState = loginState.value.errorState.copy(
                        emailOrMobileErrorState = if (loginUiEvent.inputValue.trim().isEmpty())
                            emailEmptyErrorState
                        else if (!loginUiEvent.inputValue.matches(Regex(RegexConstants.EMAIL_REGEX)))
                            invalidEmailErrorState
                        else
                            ErrorState()
                    )
                )
            }

            is LoginUiEvent.PasswordChanged -> {
                loginState.value = loginState.value.copy(
                    password = loginUiEvent.inputValue,
                    errorState = loginState.value.errorState.copy(
                        passwordErrorState = if (loginUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            passwordEmptyErrorState
                    )
                )
            }

            is LoginUiEvent.Submit -> handleSubmitButton()

            is LoginUiEvent.GoogleSignInTap -> {
                loginState.value = loginState.value.copy(
                    dialogState = loginState.value.dialogState.copy(
                        showDialog = true
                    )
                )
            }

            is LoginUiEvent.HandleGoogleAuth -> {
                loginState.value = loginState.value.copy(
                    loginInProgress = true,
                    continueWithGoogleInProgress = false
                )
                handleSignIn(loginUiEvent.result)
            }

            LoginUiEvent.GoogleSignInFailed -> {
                loginState.value = loginState.value.copy(
                    loginInProgress = false,
                    continueWithGoogleError = true,
                    continueWithGoogleInProgress = false
                )
            }

            LoginUiEvent.DialogDismiss -> {
                loginState.value = loginState.value.copy(
                    continueWithGoogleInProgress = false,
                    dialogState = loginState.value.dialogState.copy(
                        showDialog = false
                    )
                )
            }

            is LoginUiEvent.OnDialogSelectionChange -> {
                loginState.value = loginState.value.copy(
                    dialogState = loginState.value.dialogState.copy(
                        selectedUserType = loginUiEvent.newSelection
                    )
                )
            }

            LoginUiEvent.DialogSubmit -> {
                loginState.value = loginState.value.copy(
                    continueWithGoogleInProgress = true,
                    dialogState = loginState.value.dialogState.copy(
                        showDialog = false
                    )
                )
            }
        }
    }

    private fun handleSignIn(result: GetCredentialResponse) {
        when (val credential = result.credential) {
            is CustomCredential -> {
                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    try {
                        val googleIdTokenCredential =
                            GoogleIdTokenCredential.createFrom(credential.data)
                        Log.i("TAG", "handleSignIn: ${googleIdTokenCredential.idToken}")
                        val role = if (loginState.value.dialogState.selectedUserType == 0) 1 else 3
                        viewModelScope.launch(Dispatchers.IO) {
                            signInWithGoogleUseCase.invoke(
                                googleIdTokenCredential.idToken,
                                role
                            ).collect {
                                handleLoginProgress(it)
                            }
                        }
                    } catch (e: GoogleIdTokenParsingException) {
                        loginState.value = loginState.value.copy(
                            loginInProgress = false,
                            continueWithGoogleError = true,
                        )
                        Log.e("TAG", "Received an invalid google id token response", e)
                    }
                } else {
                    loginState.value = loginState.value.copy(
                        loginInProgress = false,
                        continueWithGoogleError = true,
                    )
                    Log.e("TAG", "Unexpected type of credential")
                }
            }

            else -> {
                loginState.value = loginState.value.copy(
                    loginInProgress = false,
                    continueWithGoogleError = true,
                )
            }
        }
    }


    private fun handleSubmitButton() {
        val email = loginState.value.email
        val password = loginState.value.password
        val inputsValidated = validateInput(email, password)

        if (inputsValidated) {
            callLoginApi(email, password)
        }
    }

    private fun callLoginApi(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            loginUseCase(email, password).collect {
                handleLoginProgress(it)
            }
        }
    }

    private fun validateInput(email: String, password: String): Boolean {
        if (email.isEmpty()) {
            loginState.value = loginState.value.copy(
                errorState = LoginErrorState(
                    emailOrMobileErrorState = emailEmptyErrorState
                )
            )
            return false
        }

        if (password.isEmpty()) {
            loginState.value = loginState.value.copy(
                errorState = LoginErrorState(
                    passwordErrorState = passwordEmptyErrorState
                )
            )
            return false
        }

        return !(loginState.value.errorState.passwordErrorState.hasError || loginState.value.errorState.emailOrMobileErrorState.hasError)
    }

    private fun handleLoginProgress(resource: Resource<AuthResponseDTO>) {
        when (resource) {
            is Resource.Error -> {
                loginState.value = loginState.value.copy(
                    loginInProgress = false,
                    isLoginError = true,
                    loginErrorString = resource.message
                )
            }

            is Resource.Loading -> {
                loginState.value = loginState.value.copy(loginInProgress = true)
            }

            is Resource.Success -> {
                if (resource.data.success == true) {
                    viewModelScope.launch(Dispatchers.IO) {
                        setUserAuthTokenUseCase(resource.data.authData?.accessToken ?: "")
                        setIntUseCase(
                            PROFILE_COMPLETION_STATUS,
                            resource.data.authData?.user?.role ?: 3
                        )
                    }
                    loginState.value = loginState.value.copy(
                        loginInProgress = false,
                        isLoginError = false,
                        loginErrorString = "",
                        isLoginSuccessful = true
                    )
                } else {
                    loginState.value = loginState.value.copy(
                        loginInProgress = false,
                        isLoginError = true,
                        loginErrorString = resource.data.message ?: "Something Went Wrong",
                        isLoginSuccessful = false
                    )
                }
            }
        }
    }
}