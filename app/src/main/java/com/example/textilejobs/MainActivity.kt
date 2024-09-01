package com.example.textilejobs

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.textilejobs.core.navigation.NavLanguageScreen
import com.example.textilejobs.core.navigation.NavigationService
import com.example.textilejobs.ui.theme.TextileJobsTheme

class MainActivity : AppCompatActivity() {
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