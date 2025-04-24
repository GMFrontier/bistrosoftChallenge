package com.frommetoyou.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.frommetoyou.presentation.DogsScreen
import kotlinx.serialization.Serializable

@Serializable
data object DogsRoute
@Serializable
data object DogsBaseRoute


fun NavGraphBuilder.dogsSection(
) {
    navigation<DogsBaseRoute>(startDestination = DogsRoute) {
        composable<DogsRoute>() { DogsScreen() }
    }
}