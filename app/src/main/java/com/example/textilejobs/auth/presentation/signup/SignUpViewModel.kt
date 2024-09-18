package com.example.textilejobs.auth.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.textilejobs.core.constants.LocalPrefsConstants.PROFILE_COMPLETION_STATUS
import com.example.textilejobs.core.utils.ErrorState
import com.example.textilejobs.core.utils.Resource
import com.example.textilejobs.auth.domain.usecase.SignUpUseCase
import com.example.textilejobs.auth.presentation.emailEmptyErrorState
import com.example.textilejobs.auth.presentation.invalidEmailErrorState
import com.example.textilejobs.auth.presentation.invalidPasswordErrorState
import com.example.textilejobs.auth.presentation.passwordEmptyErrorState
import com.example.textilejobs.auth.presentation.signup.state.SignUpState
import com.example.textilejobs.auth.presentation.signup.state.SignUpUiEvent
import com.example.textilejobs.auth.presentation.signup.state.emptyFirstNameErrorState
import com.example.textilejobs.auth.presentation.signup.state.emptyMobileNoErrorState
import com.example.textilejobs.auth.presentation.signup.state.invalidFirstNameErrorState
import com.example.textilejobs.auth.presentation.signup.state.invalidMobileNoErrorState
import com.example.textilejobs.auth.presentation.signup.state.mismatchPasswordErrorState
import com.example.textilejobs.core.constants.RegexConstants
import com.example.textilejobs.auth.domain.usecase.SetUserAuthTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val setUserAuthTokenUseCase: SetUserAuthTokenUseCase,
) : ViewModel() {
    private val _signUpState = MutableStateFlow(SignUpState())
    val signUpState = _signUpState.asStateFlow()

    fun onUiEvent(event: SignUpUiEvent) {
        when (event) {
            is SignUpUiEvent.FirstNameChanged -> {
                _signUpState.update { newState ->
                    newState.copy(
                        name = event.newValue,
                        errorState = signUpState.value.errorState.copy(
                            firstNameErrorState = if (event.newValue.isEmpty())
                                emptyFirstNameErrorState
                            else if (!event.newValue.matches(Regex(RegexConstants.NAME_REGEX)))
                                invalidFirstNameErrorState
                            else
                                ErrorState()
                        )
                    )
                }
            }

            is SignUpUiEvent.EmailChanged -> {
                _signUpState.update { newState ->
                    newState.copy(
                        email = event.newEmailValue,
                        errorState = signUpState.value.errorState.copy(
                            emailErrorState = if (event.newEmailValue.isEmpty())
                                emailEmptyErrorState
                            else if (!event.newEmailValue.matches(Regex(RegexConstants.EMAIL_REGEX)))
                                invalidEmailErrorState
                            else
                                ErrorState()
                        )
                    )
                }
            }

            is SignUpUiEvent.PasswordChanged -> {
                _signUpState.update { newState ->
                    newState.copy(
                        password = event.newPasswordValue,
                        confirmPassword = signUpState.value.confirmPassword,
                        errorState = signUpState.value.errorState.copy(
                            passwordErrorState = if (event.newPasswordValue.isEmpty())
                                passwordEmptyErrorState
                            else if (!event.newPasswordValue.matches(Regex(RegexConstants.PASSWORD_REGEX)))
                                invalidPasswordErrorState
                            else
                                ErrorState()
                        )
                    )
                }

            }

            is SignUpUiEvent.ConfirmPasswordChanged -> {
                _signUpState.update { newState ->
                    newState.copy(
                        confirmPassword = event.newConfirmPasswordChanged,
                        password = signUpState.value.password,
                        errorState = signUpState.value.errorState.copy(
                            confirmPasswordErrorState = if (event.newConfirmPasswordChanged.isEmpty())
                                passwordEmptyErrorState
                            else if (event.newConfirmPasswordChanged != signUpState.value.password)
                                mismatchPasswordErrorState
                            else
                                ErrorState()
                        )
                    )
                }
            }

            is SignUpUiEvent.PickedImage -> {
                _signUpState.update { newState ->
                    newState.copy(
                        pickedPhoto = event.uri
                    )
                }
            }
            is SignUpUiEvent.MobileNoChanged -> {
                _signUpState.update { newState ->
                    newState.copy(
                        mobileNumber = event.newValue,
                        errorState = signUpState.value.errorState.copy(
                            mobileNumberErrorState = if (event.newValue.isEmpty())
                                emptyMobileNoErrorState
                            else if (!event.newValue.matches(Regex(RegexConstants.PHONE_REGEX)))
                                invalidMobileNoErrorState
                            else
                                ErrorState()
                        )
                    )
                }
            }

            is SignUpUiEvent.SignUpClick -> {
                signUpApiCall(isCompany = event.isCompany)
            }
        }
    }

    private fun signUpApiCall(isCompany: Boolean) {
        if (!validateInputs()) {
            return
        }

        viewModelScope.launch {
            signUpUseCase(
                signUpState.value.name,
                signUpState.value.email,
                signUpState.value.password,
                signUpState.value.mobileNumber,
                if (isCompany) 3 else 1
            ).collect {
                when (it) {
                    is Resource.Error -> {
                        _signUpState.update { newState ->
                            newState.copy(
                                signUpInProgress = false,
                                isSignUpError = true,
                                signUpErrorString = it.message
                            )
                        }
                    }

                    Resource.Loading -> {
                        _signUpState.update { newState ->
                            newState.copy(
                                signUpInProgress = true
                            )
                        }
                    }

                    is Resource.Success -> {
                        if (it.data.success == true) {
                            viewModelScope.launch(Dispatchers.IO) {
                                setUserAuthTokenUseCase(it.data.authData?.accessToken ?: "")
//                                setIntUseCase(
//                                    PROFILE_COMPLETION_STATUS,
//                                    it.data.authData?.user?.role ?: 1
//                                )
                            }
                            _signUpState.update { newState ->
                                newState.copy(
                                    signUpInProgress = false,
                                    isSignUpSuccessful = true,
                                )
                            }
                        } else {
                            _signUpState.update { newState ->
                                newState.copy(
                                    signUpInProgress = false,
                                    isSignUpError = true,
                                    signUpErrorString = it.data.message ?: "Something Went Wrong"
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun validateInputs(): Boolean {
        val state = signUpState.value
        val errorState = signUpState.value.errorState
        if (state.email.isEmpty()) {
            _signUpState.update { newState ->
                newState.copy(
                    errorState = newState.errorState.copy(
                        emailErrorState = emailEmptyErrorState
                    )
                )
            }
            return false
        }
        if (state.name.isEmpty()) {
            _signUpState.update { newState ->
                newState.copy(
                    errorState = newState.errorState.copy(
                        firstNameErrorState = emptyFirstNameErrorState
                    )
                )
            }
            return false
        }
        if (state.mobileNumber.isEmpty()) {
            _signUpState.update { newState ->
                newState.copy(
                    errorState = newState.errorState.copy(
                        emailErrorState = emailEmptyErrorState
                    )
                )
            }
            return false
        }
        if (state.password.isEmpty()) {
            _signUpState.update { newState ->
                newState.copy(
                    errorState = newState.errorState.copy(
                        mobileNumberErrorState = emptyMobileNoErrorState
                    )
                )
            }
            return false
        }
        if (state.confirmPassword.isEmpty()) {
            _signUpState.update { newState ->
                newState.copy(
                    errorState = newState.errorState.copy(
                        confirmPasswordErrorState = passwordEmptyErrorState
                    )
                )
            }
            return false
        }
        return !(errorState.emailErrorState.hasError || errorState.firstNameErrorState.hasError || errorState.passwordErrorState.hasError || errorState.confirmPasswordErrorState.hasError || errorState.mobileNumberErrorState.hasError)
    }

    fun resetError() {
        _signUpState.update { newState ->
            newState.copy(
                isSignUpError = false,
                signUpErrorString = ""
            )
        }
    }

    fun resetSuccess() {
        _signUpState.update { newState ->
            newState.copy(
                isSignUpError = false,
                signUpErrorString = "",
                isSignUpSuccessful = false
            )
        }
    }
}