package com.example.xogamesapp.game

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.xogamesapp.Routes

fun NavController.navigateToGameScreen(
    navOptions: NavOptions? = null,
) {
    this.navigate(
        route = Routes.GAME_ROUTE,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.gameScreen(
    navEvent: GameNavEvent,
) {
    composable(route = Routes.GAME_ROUTE) {
        GameRoute(
            navEvent = navEvent
        )
    }
}

@Composable
fun GameRoute(
    navEvent: GameNavEvent,
) {
    GameScreen(
        navEvent = navEvent
    )
}