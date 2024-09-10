package com.example.textilejobs.presentation.dashboard.viewmodel

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.textilejobs.core.desginsystem.TjIcons
import com.example.textilejobs.core.navigation.NavHomeScreen
import com.example.textilejobs.core.navigation.NavProfileScreen
import com.example.textilejobs.core.navigation.NavStatusScreen

data class DashboardState(
    val screen: List<BottomNavItems> = arrayListOf(homeScreen, statusScreen, profileScreen),
    val selectedScreen: Int = 0
)

data class BottomNavItems(val route: Any, val selectedIcon: ImageVector)

val homeScreen = BottomNavItems(NavHomeScreen, TjIcons.home)
val statusScreen =  BottomNavItems(NavStatusScreen, TjIcons.status)
val profileScreen = BottomNavItems(NavProfileScreen, TjIcons.profile)
