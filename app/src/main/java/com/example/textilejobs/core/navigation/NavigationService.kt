package com.example.textilejobs.core.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.textilejobs.auth.presentation.AuthViewModel
import com.example.textilejobs.auth.presentation.login.LoginRoute
import com.example.textilejobs.auth.presentation.signup.SignUpRoute
import com.example.textilejobs.dashboard.presentation.DashboardRoute
import com.example.textilejobs.language.presentation.LanguageRoute

@Composable
fun NavigationService(navHostController: NavHostController, startDestination: Any) {
    val navBarNavController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()
    NavHost(navController = navHostController, startDestination = startDestination) {
        composable<NavLanguageScreen> {
            LanguageRoute(onNavigateToLoginScreen = {
                navHostController.navigate(NavLoginScreen) {
                    popUpTo(NavLanguageScreen) {
                        inclusive = true
                    }
                }
            })
        }
        composable<NavLoginScreen> {
            LoginRoute(
                onNavigateToForgotPassword = {},
                onNavigateToHome = {
                    navHostController.navigate(NavDashboardScreen) {
                        popUpTo(NavLoginScreen) {
                            inclusive = true
                        }
                    }
                },
                onNavigateToSignUp = { navHostController.navigate(NavSignUp) },
                authViewModel = authViewModel
            )
        }
        composable<NavSignUp> {
            SignUpRoute(
                onClickAlreadyHaveAccount = { navHostController.popBackStack() },
                navigateToHome = {
                    navHostController.navigate(NavDashboardScreen) {
                        navHostController.popBackStack(it.destination, true)
                    }
                },
                authViewModel = authViewModel
            )
        }
        composable<NavDashboardScreen> {
            DashboardRoute(navController = navBarNavController)
        }

        composable<NavForgotPassword> {

        }
    }
}