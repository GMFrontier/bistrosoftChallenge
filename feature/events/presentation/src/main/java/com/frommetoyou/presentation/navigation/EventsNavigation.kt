package com.frommetoyou.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.frommetoyou.presentation.EventsScreen
import kotlinx.serialization.Serializable

@Serializable data object EventsRoute
@Serializable data object EventsBaseRoute


fun NavGraphBuilder.eventsSection(
) {
    navigation<EventsBaseRoute>(startDestination = EventsRoute) {
        composable<EventsRoute>() { EventsScreen() }
    }
}