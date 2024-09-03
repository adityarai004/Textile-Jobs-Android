package com.example.textilejobs

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.textilejobs.core.navigation.NavHomeScreen
import com.example.textilejobs.core.navigation.NavLanguageScreen
import com.example.textilejobs.core.navigation.NavLoginScreen
import com.example.textilejobs.core.navigation.NavigationService
import com.example.textilejobs.ui.theme.TextileJobsTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition {
            mainViewModel.isLoading.value
        }
        setContent {
            val isLoggedIn = mainViewModel.isLoggedIn.collectAsState()
            val isLanguageChosen = mainViewModel.isLanguageChosen.collectAsState()
            val isLoading = mainViewModel.isLoading.collectAsState()
            val navHostController = rememberNavController()
            TextileJobsTheme {
                if (!isLoading.value) {
                    val startDestination: Any =
                        if (isLoggedIn.value) NavHomeScreen else if (!isLanguageChosen.value) NavLanguageScreen else NavLoginScreen
                    NavigationService(navHostController, startDestination)
                }
            }
        }
    }
}