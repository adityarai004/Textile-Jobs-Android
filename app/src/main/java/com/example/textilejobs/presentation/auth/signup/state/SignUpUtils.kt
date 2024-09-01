package com.example.textilejobs.presentation.auth.signup.state

import com.example.textilejobs.R
import com.example.textilejobs.core.utils.ErrorState

val emptyFirstNameErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.signup_error_msg_empty_name
)

val emptyLastNameErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.signup_error_msg_empty_last_name
)

val invalidFirstNameErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.signup_invalid_msg_empty_name
)

val invalidLastNameErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.signup_invalid_msg_empty_last_name
)

val mismatchPasswordErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.signup_error_msg_mismatch_password
)

val emptyGenderErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.signup_error_msg_empty_gender
)

val emptyDobErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.signup_error_msg_empty_dob
)

val invalidDobErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.signup_error_msg_invalid_dob
)