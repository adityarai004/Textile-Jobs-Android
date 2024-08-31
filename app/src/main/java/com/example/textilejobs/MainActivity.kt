package com.example.textilejobs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.textilejobs.core.navigation.NavLanguageScreen
import com.example.textilejobs.core.navigation.NavigationService
import com.example.textilejobs.ui.theme.TextileJobsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            val navHostController = rememberNavController()
            TextileJobsTheme {
                NavigationService(navHostController, NavLanguageScreen)
            }
        }
    }
}