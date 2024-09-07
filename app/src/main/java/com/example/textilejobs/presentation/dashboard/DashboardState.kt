package com.example.textilejobs.presentation.dashboard

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.textilejobs.R
import com.example.textilejobs.core.navigation.NavHomeScreen
import com.example.textilejobs.core.navigation.NavProfileScreen
import com.example.textilejobs.core.navigation.NavStatusScreen

data class DashboardState(
    val screen: List<BottomNavItems> = arrayListOf(homeScreen, statusScreen, profileScreen),
    val selectedScreen: Int = 0
)

data class BottomNavItems(val route: Any, val selectedIcon: ImageVector)

val homeScreen = BottomNavItems(NavHomeScreen, Icons.Default.Home)
val statusScreen =  BottomNavItems(NavStatusScreen, Icons.Outlined.Search)
val profileScreen = BottomNavItems(NavProfileScreen, Icons.Default.Person)
