package com.example.xogamesapp.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.xogamesapp.Routes

fun NavController.navigateToHomeScreen(
    navOptions: NavOptions? = null,
) {
    this.navigate(
        route = Routes.HOME_ROUTE,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.homeScreen(
    navEvent: HomeNavEvent,
) {
    composable(route = Routes.HOME_ROUTE) {
        HomeRoute(
            navEvent = navEvent
        )
    }
}

@Composable
fun HomeRoute(
    navEvent: HomeNavEvent,
) {
    HomeScreen(
        navEvent = navEvent
    )
}