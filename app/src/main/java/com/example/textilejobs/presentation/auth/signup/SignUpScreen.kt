package com.example.textilejobs.presentation.auth.signup

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.textilejobs.R
import com.example.textilejobs.core.ui.TJCircularProgress
import com.example.textilejobs.presentation.auth.components.CustomButton
import com.example.textilejobs.presentation.auth.components.LargeTitleText
import com.example.textilejobs.presentation.auth.components.MediumTitleText
import com.example.textilejobs.presentation.auth.signup.components.ImagePicker
import com.example.textilejobs.presentation.auth.signup.components.SignUpTextFields
import com.example.textilejobs.presentation.auth.signup.state.SignUpState
import com.example.textilejobs.presentation.auth.signup.state.SignUpUiEvent

@Composable
fun SignUpRoute(
    onClickAlreadyHaveAccount: () -> Unit,
    signUpViewModel: SignUpViewModel = hiltViewModel()
) {
    val signUpState by signUpViewModel.signUpState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val pickImageContract = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            if (uri != null) {
                signUpViewModel.onUiEvent(SignUpUiEvent.PickedImage(uri))
            }
        }
    )

    val requestPermissionContract = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                pickImageContract.launch(PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly))
            } else {
                Toast.makeText(context, "Please grant permissions from settings", Toast.LENGTH_LONG)
                    .show()
            }
        }
    )


    LaunchedEffect(key1 = signUpState.isSignUpError) {
        if (signUpState.isSignUpError) {
            Toast.makeText(context, signUpState.signUpErrorString, Toast.LENGTH_LONG).show()
        }
        signUpViewModel.resetError()
    }

    LaunchedEffect(key1 = signUpState.isSignUpSuccessful) {
        if (signUpState.isSignUpSuccessful) {
            Toast.makeText(
                context,
                "Sign Up Successful, Please Login With Your Credentials",
                Toast.LENGTH_LONG
            ).show()
            onClickAlreadyHaveAccount()
        }
        signUpViewModel.resetSuccess()
    }

    SignUpScreen(
        signUpState = signUpState,
        onFirstNameChange = { inputString ->
            signUpViewModel.onUiEvent(SignUpUiEvent.FirstNameChanged(inputString))
        },
        onEmailChange = { inputString ->
            signUpViewModel.onUiEvent(SignUpUiEvent.EmailChanged(inputString))
        },
        onPasswordChange = { inputString ->
            signUpViewModel.onUiEvent(SignUpUiEvent.PasswordChanged(inputString))
        },
        onConfirmPasswordChange = { inputString ->
            signUpViewModel.onUiEvent(SignUpUiEvent.ConfirmPasswordChanged(inputString))
        },
        onUploadImageClick = {
            val isTiramisu = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
            val hasMediaImagePermission: Boolean =
                if (isTiramisu) ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.READ_MEDIA_IMAGES
                ) == PackageManager.PERMISSION_GRANTED else ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            if ((isTiramisu && hasMediaImagePermission) || hasMediaImagePermission) {
                pickImageContract.launch(PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly))
            } else {
                requestPermissionContract.launch(if (isTiramisu) Manifest.permission.READ_MEDIA_IMAGES else Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        },
        onNavigateToLogin = onClickAlreadyHaveAccount,
        onSignUpClick = {
            signUpViewModel.onUiEvent(SignUpUiEvent.SignUpClick)
        },
        onLastNameChange = {
            signUpViewModel.onUiEvent(SignUpUiEvent.LastNameChanged(it))
        }
    )
}

@Composable
fun SignUpScreen(
    signUpState: SignUpState,
    onFirstNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onUploadImageClick: () -> Unit,
    onNavigateToLogin: () -> Unit,
    onSignUpClick: () -> Unit
) {
    val scrollState = rememberScrollState()
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    LargeTitleText(text = stringResource(id = R.string.sign_up))
                    ImagePicker(
                        modifier = Modifier.fillMaxWidth(),
                        filePath = signUpState.pickedPhoto, onClickUpload =
                        onUploadImageClick
                    )
                    MediumTitleText(text = stringResource(id = R.string.please_enter_your_information))
                    SignUpTextFields(
                        firstNameValue = signUpState.firstName,
                        lastNameValue = signUpState.lastName,
                        lastNameErrorState = signUpState.errorState.lastNameErrorState,
                        onLastNameChange = onLastNameChange,
                        emailValue = signUpState.email,
                        passwordValue = signUpState.password,
                        confirmPasswordValue = signUpState.confirmPassword,
                        onFirstNameChange = onFirstNameChange,
                        onEmailChange = onEmailChange,
                        onPasswordChange = onPasswordChange,
                        confirmPasswordChange = onConfirmPasswordChange,
                        firstNameErrorState = signUpState.errorState.firstNameErrorState,
                        emailErrorState = signUpState.errorState.emailErrorState,
                        passwordErrorState = signUpState.errorState.passwordErrorState,
                        confirmPasswordErrorState = signUpState.errorState.confirmPasswordErrorState,
                    )
                    MediumTitleText(
                        text = "Already Have An Account?",
                        modifier = Modifier
                            .clickable(interactionSource = null, indication = null) {
                                onNavigateToLogin()
                            }
                            .padding(start = 8.dp),
                        color = colorResource(id = R.color.black)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomButton(
                        onClick = onSignUpClick,
                        text = "Sign Up",
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            if (signUpState.signUpInProgress) {
                TJCircularProgress()
            }
        }
    }
}

@Preview
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen(
        onFirstNameChange = {},
        onEmailChange = {},
        onPasswordChange = {},
        onConfirmPasswordChange = {},
        signUpState = SignUpState(),
        onUploadImageClick = {},
        onNavigateToLogin = {},
        onSignUpClick = {},
        onLastNameChange = {}
    )
}