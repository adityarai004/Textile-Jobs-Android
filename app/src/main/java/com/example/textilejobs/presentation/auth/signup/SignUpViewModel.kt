package com.example.textilejobs.presentation.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.textilejobs.core.utils.ErrorState
import com.example.textilejobs.core.utils.Resource
import com.example.textilejobs.domain.usecase.SignUpUseCase
import com.example.textilejobs.presentation.auth.emailEmptyErrorState
import com.example.textilejobs.presentation.auth.invalidEmailErrorState
import com.example.textilejobs.presentation.auth.invalidPasswordErrorState
import com.example.textilejobs.presentation.auth.passwordEmptyErrorState
import com.example.textilejobs.presentation.auth.signup.state.SignUpState
import com.example.textilejobs.presentation.auth.signup.state.SignUpUiEvent
import com.example.textilejobs.presentation.auth.signup.state.emptyFirstNameErrorState
import com.example.textilejobs.presentation.auth.signup.state.emptyLastNameErrorState
import com.example.textilejobs.presentation.auth.signup.state.emptyMobileNoErrorState
import com.example.textilejobs.presentation.auth.signup.state.invalidFirstNameErrorState
import com.example.textilejobs.presentation.auth.signup.state.invalidLastNameErrorState
import com.example.textilejobs.presentation.auth.signup.state.invalidMobileNoErrorState
import com.example.textilejobs.presentation.auth.signup.state.mismatchPasswordErrorState
import com.example.trendingtimesjetpack.core.constants.RegexConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val signUpUseCase: SignUpUseCase) : ViewModel() {
    private val _signUpState = MutableStateFlow(SignUpState())
    val signUpState = _signUpState.asStateFlow()

    fun onUiEvent(event: SignUpUiEvent) {
        when (event) {
            is SignUpUiEvent.FirstNameChanged -> {
                _signUpState.update { newState ->
                    newState.copy(
                        firstName = event.newValue,
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


            SignUpUiEvent.SignUpClick -> {
                signUpApiCall()
            }


            is SignUpUiEvent.PickedImage -> {
                _signUpState.update { newState ->
                    newState.copy(
                        pickedPhoto = event.uri
                    )
                }
            }

            is SignUpUiEvent.LastNameChanged -> {
                _signUpState.update { newState ->
                    newState.copy(
                        lastName = event.newValue,
                        errorState = signUpState.value.errorState.copy(
                            lastNameErrorState = if (event.newValue.isEmpty())
                                emptyLastNameErrorState
                            else if (!event.newValue.matches(Regex(RegexConstants.NAME_REGEX)))
                                invalidLastNameErrorState
                            else
                                ErrorState()
                        )
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
        }
    }

    private fun signUpApiCall() {
        if (!validateInputs()) {
            return
        }

        viewModelScope.launch {
            signUpUseCase(
                signUpState.value.firstName,
                signUpState.value.lastName,
                signUpState.value.email,
                signUpState.value.password,
                signUpState.value.mobileNumber
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
                        if (it.data.success) {
                            _signUpState.update { newState ->
                                newState.copy(
                                    isSignUpSuccessful = true,
                                )
                            }
                        } else {
                            _signUpState.update { newState ->
                                newState.copy(
                                    signUpInProgress = false,
                                    isSignUpError = true,
                                    signUpErrorString = it.data.message
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
        if (state.firstName.isEmpty()) {
            _signUpState.update { newState ->
                newState.copy(
                    errorState = newState.errorState.copy(
                        firstNameErrorState = emptyFirstNameErrorState
                    )
                )
            }
            return false
        }
        if (state.lastName.isEmpty()) {
            _signUpState.update { newState ->
                newState.copy(
                    errorState = newState.errorState.copy(
                        lastNameErrorState = emptyLastNameErrorState
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