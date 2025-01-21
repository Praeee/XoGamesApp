package com.example.xogamesapp.history

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.xogamesapp.Routes

fun NavController.navigateToHistoryScreen(
    navOptions: NavOptions? = null,
) {
    this.navigate(
        route = Routes.HISTORY_ROUTE,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.historyScreen(
    navEvent: HistoryNavEvent,
) {
    composable(route = Routes.HISTORY_ROUTE) {
        HistoryRoute(
            navEvent = navEvent
        )
    }
}

@Composable
fun HistoryRoute(
    navEvent: HistoryNavEvent,
) {
    HistoryScreen(
        navEvent = navEvent
    )
}