package com.frommetoyou.presentation.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.frommetoyou.presentation.ui.FactorialScreen
import kotlinx.serialization.Serializable

@Serializable data object FactorialRoute
@Serializable data object FactorialBaseRoute


fun NavGraphBuilder.factorialSection(
) {
    navigation<FactorialBaseRoute>(startDestination = FactorialRoute) {
        composable<FactorialRoute>() { FactorialScreen() }
    }
}