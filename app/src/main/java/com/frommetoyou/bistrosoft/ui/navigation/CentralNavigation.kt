package com.frommetoyou.bistrosoft.ui.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.frommetoyou.bistrosoft.R
import com.frommetoyou.common.UiText
import kotlinx.serialization.Serializable

@Composable
fun CentralNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomScreens.Factorial.route
    ) {
        mainSection(navController)
    }
}

@Composable
fun AppBottomNavigation(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomScreens = getBottomScreens()

    val bottomBarState by remember(navBackStackEntry) {
        derivedStateOf {
            when (navBackStackEntry?.destination?.route) {
                null -> true
                FactorialRoute::class.qualifiedName, DogsRoute::class.qualifiedName,  EventsRoute::class.qualifiedName  -> true
                else -> false
            }
        }
    }

    if (bottomBarState)
        NavigationBar {

            bottomScreens.forEach { screen ->
                val isSelected = currentDestination?.hierarchy?.any {
                    it.route == screen.route::class.qualifiedName.toString()
                } == true

                NavigationBarItem(
                    icon = {
                        if (isSelected) Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = screen.selectedIcon),
                            contentDescription = screen.name.asString(
                                LocalContext.current
                            )
                        )
                        else Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = screen.unselectedIcon),
                            contentDescription = screen.name.asString(
                                LocalContext.current
                            )
                        )
                    },
                    label = { Text(screen.name.asString(LocalContext.current)) },
                    selected = isSelected,
                    onClick = {
                        if (isSelected.not())
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                    }
                )
            }
        }
}

@Composable
fun getBottomScreens(): List<BottomScreens<out Any>> {
    return listOf(
        BottomScreens.Factorial,
        BottomScreens.Dogs,
        BottomScreens.Events,
    )
}

@Serializable
sealed class BottomScreens<T>(
    val name: UiText, // the name of the tab
    val selectedIcon: Int, // filled icon when selected
    val unselectedIcon: Int, // unfilled icon when not selected
    val route: T //graphs defined in feature module for each tab

) {

    @Serializable
    data object Factorial : BottomScreens<FactorialRoute>(
        name = UiText.DynamicString("Factorial"),
        unselectedIcon = R.drawable.ic_numbers,
        selectedIcon = R.drawable.ic_numbers,
        route = FactorialRoute
    )

    @Serializable
    data object Dogs : BottomScreens<DogsRoute>(
        name = UiText.DynamicString("Dogs"),
        unselectedIcon = R.drawable.ic_paw_outline,
        selectedIcon = R.drawable.ic_paw_filled,
        route = DogsRoute
    )

    @Serializable
    data object Events : BottomScreens<EventsRoute>(
        name = UiText.DynamicString("Events"),
        unselectedIcon = R.drawable.ic_details_outline,
        selectedIcon = R.drawable.ic_details_filled,
        route = EventsRoute
    )

}