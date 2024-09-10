package com.example.textilejobs.presentation.auth.signup

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.textilejobs.R
import com.example.textilejobs.core.desginsystem.TJCircularProgress
import com.example.textilejobs.presentation.auth.AuthViewModel
import com.example.textilejobs.presentation.auth.components.CustomButton
import com.example.textilejobs.presentation.auth.components.MediumTitleText
import com.example.textilejobs.presentation.auth.signup.components.ImagePicker
import com.example.textilejobs.presentation.auth.signup.components.SignUpTextFields
import com.example.textilejobs.presentation.auth.signup.state.SignUpState
import com.example.textilejobs.presentation.auth.signup.state.SignUpUiEvent

@Composable
fun SignUpRoute(
    onClickAlreadyHaveAccount: () -> Unit,
    navigateToHome: () -> Unit,
    signUpViewModel: SignUpViewModel = hiltViewModel(),
    authViewModel: AuthViewModel
) {
    val signUpState by signUpViewModel.signUpState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val isCompany = authViewModel.isCompany.collectAsState()
    val pickImageContract = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            if (uri != null) {
                signUpViewModel.onUiEvent(SignUpUiEvent.PickedImage(uri))
            }
        }
    )
    val settingsToast = stringResource(id = R.string.grant_permission_from_settings)
    val accountCreationToast = stringResource(id = R.string.account_created_successfully)

    val requestPermissionContract = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                pickImageContract.launch(PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly))
            } else {
                Toast.makeText(context, settingsToast, Toast.LENGTH_LONG).show()
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
                accountCreationToast,
                Toast.LENGTH_LONG
            ).show()
            navigateToHome()
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
            signUpViewModel.onUiEvent(SignUpUiEvent.SignUpClick(isCompany = isCompany.value))
        },
        onMobileNumberChange = {
            signUpViewModel.onUiEvent(SignUpUiEvent.MobileNoChanged(it))
        },
        isCompany = isCompany.value
    )
}

@Composable
fun SignUpScreen(
    signUpState: SignUpState,
    onFirstNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onMobileNumberChange: (String) -> Unit,
    onUploadImageClick: () -> Unit,
    onNavigateToLogin: () -> Unit,
    onSignUpClick: () -> Unit,
    isCompany: Boolean
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
                    if(!isCompany){
                        ImagePicker(
                            modifier = Modifier.fillMaxWidth(),
                            filePath = signUpState.pickedPhoto,
                            onClickUpload = onUploadImageClick
                        )
                    }
                    MediumTitleText(
                        text = stringResource(id = R.string.sign_up),
                        style = TextStyle(fontSize = 26.sp, fontWeight = FontWeight.Medium),
                        modifier = Modifier.padding(vertical = 12.dp)
                    )

                    SignUpTextFields(
                        firstNameValue = signUpState.name,
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
                        mobileNumber = signUpState.mobileNumber,
                        mobileErrorState = signUpState.errorState.mobileNumberErrorState,
                        onMobileNumberChange = onMobileNumberChange,
                        isCompany = isCompany
                    )
                    TextButton(
                        onClick = onNavigateToLogin
                    ) {
                        Text(
                            AnnotatedString(stringResource(id = R.string.already_have_an_account)),
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Black
                            ),
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomButton(
                        onClick = onSignUpClick,
                        text = "Sign Up",
                        modifier = Modifier.fillMaxWidth(),
                        containerColor = R.color.success_green
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
        onMobileNumberChange = {},
        isCompany = true
    )
}