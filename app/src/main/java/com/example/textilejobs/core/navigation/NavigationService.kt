package com.example.textilejobs.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.textilejobs.presentation.language.LanguageRoute

@Composable
fun NavigationService(navHostController: NavHostController, startDestination: Any) {
    NavHost(navController = navHostController, startDestination = startDestination) {
        composable<NavLanguageScreen> {
            LanguageRoute(onNavigateToLoginScreen = { navHostController.navigate(NavLoginScreen) })
        }
        composable<NavLoginScreen> {

        }
    }
}