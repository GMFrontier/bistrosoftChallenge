package com.frommetoyou.bistrosoft.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.frommetoyou.presentation.DogsScreen
import com.frommetoyou.presentation.EventsScreen
import com.frommetoyou.presentation.ui.FactorialScreen
import kotlinx.serialization.Serializable

@Serializable data object FactorialRoute
@Serializable data object DogsRoute
@Serializable data object EventsRoute


fun NavGraphBuilder.mainSection(navController: NavController) {
        composable<FactorialRoute>() { FactorialScreen() }
        composable<DogsRoute>() { DogsScreen() }
        composable<EventsRoute>() { EventsScreen() }
}