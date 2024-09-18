package com.example.textilejobs.dashboard.presentation

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel;
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.textilejobs.core.navigation.NavHomeScreen
import com.example.textilejobs.core.navigation.NavProfileScreen
import com.example.textilejobs.core.navigation.NavStatusScreen
import com.example.textilejobs.dashboard.presentation.viewmodel.DashboardEvent
import com.example.textilejobs.dashboard.presentation.viewmodel.DashboardState
import com.example.textilejobs.dashboard.presentation.viewmodel.DashboardViewModel

@Composable
fun DashboardRoute(
    navController: NavController,
    viewModel: DashboardViewModel = viewModel()
) {
    val dashboardState by viewModel.dashboardState.collectAsStateWithLifecycle()
    DashboardScreen(
        dashboardState = dashboardState,
        onChangeScreen = { viewModel.onSubmitEvent(DashboardEvent.OnChangeScreen(it)) },
        navController = navController
    )

}

@Composable
fun DashboardScreen(
    dashboardState: DashboardState,
    onChangeScreen: (Int) -> Unit,
    navController: NavController
) {
    Scaffold(
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.075f)
                    .background(Color.Black.copy(alpha = 0.88f)),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                dashboardState.screen.forEach {
                    val color =
                        if (dashboardState.selectedScreen == dashboardState.screen.indexOf(it)) {
                            Color.White
                        } else {
                            Color.Gray
                        }
                    IconButton(onClick = {
                        onChangeScreen(dashboardState.screen.indexOf(it))
                        navController.navigate(it.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    },
                        modifier = Modifier.padding(12.dp)) {
                        Icon(
                            imageVector = it.selectedIcon,
                            contentDescription = "",
                            tint = color
                        )
                    }
                }

            }
        }
    ) { innerPadding ->
       NavHost(
            navController = navController as NavHostController,
            startDestination = NavHomeScreen,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<NavHomeScreen>(
                enterTransition = {
                    slideIn(tween(durationMillis = 100, easing = FastOutLinearInEasing)){ fullSize ->
                        IntOffset(fullSize.width / 4, 100)
                    }
                }
            ) {
                Text(text = "Home Screen")
            }
            composable<NavStatusScreen> {
                Text(text = "Status Screen")
            }
            composable<NavProfileScreen> {
                Text(text = "Profile Screen")
            }
        }
    }
}

@Preview
@Composable
private fun DashboardPreview() {
    DashboardScreen(
        dashboardState = DashboardState(selectedScreen = 2),
        onChangeScreen = {},
        navController = NavHostController(
            LocalContext.current
        )
    )
}