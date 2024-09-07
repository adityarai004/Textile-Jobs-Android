package com.example.textilejobs.presentation.auth.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.textilejobs.presentation.auth.login.state.LoginUiEvent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialCancellationException
import com.example.textilejobs.R
import com.example.textilejobs.core.constants.NetworkConstants
import com.example.textilejobs.core.ui.TJCircularProgress
import com.example.textilejobs.presentation.auth.components.AuthTextField
import com.example.textilejobs.presentation.auth.components.CustomButton
import com.example.textilejobs.presentation.auth.components.MediumTitleText
import com.example.textilejobs.presentation.auth.components.PasswordTextField
import com.example.textilejobs.presentation.auth.login.components.AccountRow
import com.example.textilejobs.presentation.auth.login.state.LoginState
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun LoginRoute(
    loginViewModel: LoginViewModel = hiltViewModel(),
    onNavigateToHome: () -> Unit,
    onNavigateToSignUp: () -> Unit,
    onNavigateToForgotPassword: () -> Unit
) {
    val context = LocalContext.current
    val loginState by remember {
        loginViewModel.loginState
    }
    val state = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val loginErrorString = stringResource(id = R.string.failed_to_continue_with_google)
    LaunchedEffect(key1 = loginState.isLoginError, key2 = loginState.continueWithGoogleError) {
        if (loginState.isLoginError) {
            Toast.makeText(context, loginState.loginErrorString, Toast.LENGTH_LONG).show()
        }
        if (loginState.continueWithGoogleError) {
            Toast.makeText(context, loginErrorString, Toast.LENGTH_LONG).show()
        }
        loginViewModel.resetLoginError()
    }
    LaunchedEffect(key1 = loginState.isLoginSuccessful) {
        if (loginState.isLoginSuccessful) {
            onNavigateToHome()
        }
    }
    LaunchedEffect(loginState.continueWithGoogleInProgress) {
        if (loginState.continueWithGoogleInProgress) {
            val signInWithGoogleOption: GetSignInWithGoogleOption =
                GetSignInWithGoogleOption.Builder(NetworkConstants.WEB_CLIENT_ID)
                    .build()

            val getCredentialRequest = GetCredentialRequest.Builder()
                .addCredentialOption(signInWithGoogleOption)
                .build()


            coroutineScope.launch(Dispatchers.IO) {
                try {
                    val result = CredentialManager.create(context).getCredential(
                        context = context,
                        request = getCredentialRequest
                    )
                    loginViewModel.onUiEvent(loginUiEvent = LoginUiEvent.HandleGoogleAuth(result))
                } catch (e: GetCredentialCancellationException) {
                    loginViewModel.onUiEvent(LoginUiEvent.GoogleSignInFailed)
                    Log.e("GoogleSignIn","Failed Google Sign In due to $e")
                } catch (e: Exception){
                    loginViewModel.onUiEvent(LoginUiEvent.GoogleSignInFailed)
                    Log.e("GoogleSignIn","Failed Google Sign In due to $e")
                }
            }
        }
    }

    Scaffold { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column(modifier = Modifier.verticalScroll(state)) {
                MainScreen(
                    loginState = loginState, onEmailChange = { inputString ->
                        loginViewModel.onUiEvent(
                            loginUiEvent = LoginUiEvent.EmailChanged(
                                inputString
                            )
                        )
                    },
                    onPasswordChange = { inputString ->
                        loginViewModel.onUiEvent(LoginUiEvent.PasswordChanged(inputValue = inputString))
                    },
                    onSubmit = {
                        loginViewModel.onUiEvent(LoginUiEvent.Submit)
                    },
                    onSignUpClick = onNavigateToSignUp,
                    onForgotPasswordClick = onNavigateToForgotPassword,
                    onContinueWithGoogleClick = {
                        loginViewModel.onUiEvent(LoginUiEvent.GoogleSignInTap)
                    }
                )
            }
            if (loginState.loginInProgress) {
                TJCircularProgress()
            }
        }
    }
}

@Composable
private fun MainScreen(
    loginState: LoginState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignUpClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onSubmit: () -> Unit,
    onContinueWithGoogleClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MediumTitleText(
            text = stringResource(id = R.string.login_with),
            style = TextStyle(fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 26.sp),
            modifier = Modifier.padding(32.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(50.dp)
                .clickable {
                    onContinueWithGoogleClick()
                }
                .border(
                    width = 2.dp,
                    color = colorResource(id = R.color.muted_gray),
                    shape = RoundedCornerShape(8.dp)
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_google),
                contentDescription = "",
                tint = Color.Unspecified,
                modifier = Modifier.size(26.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = stringResource(id = R.string.continue_with_google), style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(id = R.color.text_gray)
                )
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 22.dp, horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            HorizontalDivider(modifier = Modifier.fillMaxWidth(), thickness = 2.dp)
            Text(
                text = "Or",
                modifier = Modifier
                    .background(Color.White)
                    .padding(horizontal = 8.dp),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.text_gray)
                )
            )
        }

        Column(modifier = Modifier.padding(10.dp)) {
            MediumTitleText(
                text = stringResource(id = R.string.please_login_with_credentials),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                ),
                modifier = Modifier.padding(6.dp)
            )
            AuthTextField(
                modifier = Modifier.fillMaxWidth(),
                value = loginState.email,
                onValueChange = onEmailChange,
                label = stringResource(id = R.string.enter_your_email),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Email,
                    showKeyboardOnFocus = true
                ),
                errorText = stringResource(id = loginState.errorState.emailOrMobileErrorState.errorMessageStringResource),
                isError = loginState.errorState.emailOrMobileErrorState.hasError
            )
            PasswordTextField(
                modifier = Modifier.fillMaxWidth(),
                value = loginState.password,
                onValueChange = onPasswordChange,
                label = stringResource(id = R.string.enter_your_password),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done,
                    showKeyboardOnFocus = true
                ),
                errorText = stringResource(id = loginState.errorState.passwordErrorState.errorMessageStringResource),
                isError = loginState.errorState.passwordErrorState.hasError
            )
            CustomButton(
                onClick = onSubmit,
                text = stringResource(id = R.string.login),
                modifier = Modifier.fillMaxWidth()
            )
            AccountRow(
                modifier = Modifier.fillMaxWidth(),
                onSignUpClick = onSignUpClick,
                onForgotPasswordClick = onForgotPasswordClick
            )
        }
    }
}

@Composable
@Preview
private fun LoginScreenPrev() {
    MainScreen(
        onPasswordChange = {},
        onSubmit = {},
        onEmailChange = {},
        loginState = LoginState(),
        onSignUpClick = {},
        onForgotPasswordClick = {},
        onContinueWithGoogleClick = {},
    )
}