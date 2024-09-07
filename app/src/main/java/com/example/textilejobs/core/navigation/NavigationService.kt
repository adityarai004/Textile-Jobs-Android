package com.example.textilejobs.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.textilejobs.presentation.auth.login.LoginRoute
import com.example.textilejobs.presentation.auth.signup.SignUpRoute
import com.example.textilejobs.presentation.dashboard.DashboardRoute
import com.example.textilejobs.presentation.language.LanguageRoute

@Composable
fun NavigationService(navHostController: NavHostController, startDestination: Any) {
    NavHost(navController = navHostController, startDestination = startDestination) {
        composable<NavLanguageScreen> {
            LanguageRoute(onNavigateToLoginScreen = { navHostController.navigate(NavLoginScreen){
                popUpTo(NavLanguageScreen){
                    inclusive = true
                }
            } })
        }
        composable<NavLoginScreen> {
            LoginRoute(
                onNavigateToForgotPassword = {},
                onNavigateToHome = {
                    navHostController.navigate(NavDashboardScreen) {
                        popUpTo(NavLoginScreen){
                            inclusive = true
                        }
                    }
                },
                onNavigateToSignUp = { navHostController.navigate(NavSignUp) }
            )
        }
        composable<NavSignUp> {
            SignUpRoute(
                onClickAlreadyHaveAccount = { navHostController.popBackStack() },
                navigateToHome = {
                    navHostController.navigate(NavDashboardScreen) {
                        navHostController.popBackStack(it.destination, true)
                    }
                })
        }
        composable<NavDashboardScreen> {
            DashboardRoute(navController = navHostController)
        }

        composable<NavForgotPassword> {

        }
    }
}