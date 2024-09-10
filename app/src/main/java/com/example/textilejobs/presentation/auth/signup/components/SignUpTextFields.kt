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
    mobileNumber: String,
    onMobileNumberChange: (String) -> Unit,
    mobileErrorState: ErrorState,
    onFirstNameChange: (String) -> Unit,
    firstNameErrorState: ErrorState,
    emailErrorState: ErrorState,
    passwordErrorState: ErrorState,
    confirmPasswordErrorState: ErrorState,
    emailValue: String,
    onEmailChange: (String) -> Unit,
    passwordValue: String,
    onPasswordChange: (String) -> Unit,
    confirmPasswordValue: String,
    confirmPasswordChange: (String) -> Unit,
    isCompany: Boolean
) {
    var emailLabel = R.string.enter_your_email
    var nameLabel = R.string.enter_your_name
    var mobileLabel = R.string.enter_your_mobile_number

    if(isCompany){
        emailLabel = R.string.enter_your_company_email
        nameLabel = R.string.enter_your_company_name
        mobileLabel = R.string.enter_your_company_mobile_number
    }
    Column(modifier = modifier) {
        AuthTextField(
            modifier = Modifier.fillMaxWidth(),
            value = emailValue,
            onValueChange = onEmailChange,
            label = stringResource(id = emailLabel),
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
            label = stringResource(id = nameLabel),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            isError = firstNameErrorState.hasError,
            errorText = stringResource(firstNameErrorState.errorMessageStringResource)
        )
        AuthTextField(
            modifier = Modifier.fillMaxWidth(),
            value = mobileNumber,
            onValueChange = onMobileNumberChange,
            label = stringResource(id = mobileLabel),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            ),
            isError = mobileErrorState.hasError,
            errorText = stringResource(mobileErrorState.errorMessageStringResource)
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
