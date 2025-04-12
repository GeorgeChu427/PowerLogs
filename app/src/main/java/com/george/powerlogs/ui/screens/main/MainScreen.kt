package com.george.powerlogs.ui.screens.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.george.powerlogs.ui.screens.training.TrainingScreen
import com.george.powerlogs.ui.screens.history.HistoryScreen
import com.george.powerlogs.ui.screens.profile.ProfileScreen

enum class TabItem(
    val title: String,
    val icon: ImageVector,
    val route: String
) {
    HISTORY(
        title = "歷史",
        icon = Icons.Filled.DateRange,
        route = "history"
    ),
    TRAINING(
        title = "訓練",
        icon = Icons.Filled.Add,
        route = "training"
    ),
    PROFILE(
        title = "我",
        icon = Icons.Filled.Person,
        route = "profile"
    )
}

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val navController = rememberNavController()
    val selectedItem by viewModel.selectedTab.collectAsState()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showBottomBar = currentRoute !in listOf("training_detail")

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    TabItem.entries.forEachIndexed { index, tabItem ->
                        NavigationBarItem(
                            icon = { Icon(tabItem.icon, contentDescription = tabItem.title) },
                            label = { Text(tabItem.title) },
                            selected = selectedItem == index,
                            onClick = {
                                if (tabItem == TabItem.TRAINING) {
                                    navController.navigate("training_detail")
                                } else {
                                    viewModel.onTabSelected(index)
                                    navController.navigate(tabItem.route)
                                }
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color.White,
                                selectedTextColor = MaterialTheme.colorScheme.primary,
                                indicatorColor = MaterialTheme.colorScheme.primary
                            )
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = TabItem.HISTORY.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(TabItem.HISTORY.route) { HistoryScreen() }
            composable(TabItem.PROFILE.route) { ProfileScreen() }
            composable("training_detail") { TrainingScreen(navController) }
        }
    }
} 