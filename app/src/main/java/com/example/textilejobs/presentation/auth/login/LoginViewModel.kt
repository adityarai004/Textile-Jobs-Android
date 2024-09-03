package com.example.textilejobs.presentation.auth.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.textilejobs.core.utils.ErrorState
import com.example.textilejobs.core.utils.Resource
import com.example.textilejobs.domain.usecase.LoginUseCase
import com.example.textilejobs.domain.usecase.SetUserAuthTokenUseCase
import com.example.textilejobs.presentation.auth.emailEmptyErrorState
import com.example.textilejobs.presentation.auth.invalidEmailErrorState
import com.example.textilejobs.presentation.auth.login.state.LoginErrorState
import com.example.textilejobs.presentation.auth.login.state.LoginState
import com.example.textilejobs.presentation.auth.login.state.LoginUiEvent
import com.example.textilejobs.presentation.auth.passwordEmptyErrorState
import com.example.trendingtimesjetpack.core.constants.RegexConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val setUserAuthTokenUseCase: SetUserAuthTokenUseCase,
) : ViewModel() {
    var loginState = mutableStateOf(LoginState())
        private set

    fun resetLoginError() {
        loginState.value = loginState.value.copy(isLoginError = false)
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
            loginUseCase.invoke(email, password).collect {
                when (it) {
                    is Resource.Error -> {
                        loginState.value = loginState.value.copy(
                            loginInProgress = false,
                            isLoginError = true,
                            loginErrorString = it.message
                        )
                    }

                    is Resource.Loading -> {
                        loginState.value = loginState.value.copy(loginInProgress = true)
                    }

                    is Resource.Success -> {
                        if (it.data.success) {
                            viewModelScope.launch(Dispatchers.IO) {
                                setUserAuthTokenUseCase(it.data.message)
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
                                loginErrorString = it.data.message,
                                isLoginSuccessful = false
                            )
                        }
                    }
                }
            }
        }
    }

    private fun validateInput(email: String, password: String): Boolean {

        if(email.isEmpty()) {
            loginState.value = loginState.value.copy(
                errorState = LoginErrorState(
                    emailOrMobileErrorState = emailEmptyErrorState
                )
            )
            return false
        }

        if(password.isEmpty() ) {
            loginState.value = loginState.value.copy(
                errorState = LoginErrorState(
                    passwordErrorState = passwordEmptyErrorState
                )
            )
            return false
        }

        return !(loginState.value.errorState.passwordErrorState.hasError || loginState.value.errorState.emailOrMobileErrorState.hasError)
    }
}