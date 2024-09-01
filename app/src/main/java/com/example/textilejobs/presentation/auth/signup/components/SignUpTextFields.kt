package com.example.textilejobs.presentation.auth.signup.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.textilejobs.R
import com.example.textilejobs.core.utils.ErrorState
import com.example.textilejobs.presentation.auth.components.AuthTextField
import com.example.textilejobs.presentation.auth.components.PasswordTextField


@Composable
fun SignUpTextFields(
    modifier: Modifier = Modifier,
    firstNameValue: String,
    lastNameValue: String,
    onFirstNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    firstNameErrorState: ErrorState,
    lastNameErrorState: ErrorState,
    emailErrorState: ErrorState,
    passwordErrorState: ErrorState,
    confirmPasswordErrorState: ErrorState,
    emailValue: String,
    onEmailChange: (String) -> Unit,
    passwordValue: String,
    onPasswordChange: (String) -> Unit,
    confirmPasswordValue: String,
    confirmPasswordChange: (String) -> Unit,
) {
    Column(modifier = modifier) {
        AuthTextField(
            modifier = Modifier.fillMaxWidth(),
            value = emailValue,
            onValueChange = onEmailChange,
            label = stringResource(id = R.string.enter_your_email),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            isError = emailErrorState.hasError,
            errorText = stringResource(id = emailErrorState.errorMessageStringResource)
        )
        AuthTextField(
            modifier = Modifier.fillMaxWidth(),
            value = firstNameValue,
            onValueChange = onFirstNameChange,
            label = stringResource(id = R.string.enter_your_first_name),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            isError = firstNameErrorState.hasError,
            errorText = stringResource(firstNameErrorState.errorMessageStringResource)
        )
        AuthTextField(
            modifier = Modifier.fillMaxWidth(),
            value = lastNameValue,
            onValueChange = onLastNameChange,
            label = stringResource(id = R.string.enter_your_last_name),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            isError = lastNameErrorState.hasError,
            errorText = stringResource(lastNameErrorState.errorMessageStringResource)
        )
        PasswordTextField(
            modifier = Modifier.fillMaxWidth(),
            value = passwordValue,
            onValueChange = onPasswordChange,
            label = stringResource(id = R.string.enter_your_password),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            isError = passwordErrorState.hasError,
            errorText = stringResource(passwordErrorState.errorMessageStringResource)
        )
        PasswordTextField(
            modifier = Modifier.fillMaxWidth(),
            value = confirmPasswordValue,
            onValueChange = confirmPasswordChange,
            label = stringResource(id = R.string.confirm_your_password),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            isError = confirmPasswordErrorState.hasError,
            errorText = stringResource(id = confirmPasswordErrorState.errorMessageStringResource)
        )
    }
}
