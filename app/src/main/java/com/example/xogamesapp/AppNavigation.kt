package com.example.xogamesapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.xogamesapp.game.GameNavEvent
import com.example.xogamesapp.game.gameScreen
import com.example.xogamesapp.game.navigateToGameScreen
import com.example.xogamesapp.history.HistoryNavEvent
import com.example.xogamesapp.history.historyScreen
import com.example.xogamesapp.history.navigateToHistoryScreen
import com.example.xogamesapp.home.HomeNavEvent
import com.example.xogamesapp.home.homeScreen

object Routes {
    const val HOME_ROUTE = "home_screen"
    const val HISTORY_ROUTE = "history_screen"
    const val GAME_ROUTE = "game_screen"
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun XoGamesApp() {
    val navController = rememberNavController()
    AppNavigationGraph(
        navController = navController
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigationGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Routes.HOME_ROUTE
    ) {
        homeScreen(
            navEvent = HomeNavEvent(
                onClickStartGame = {
                    navController.navigateToGameScreen()
                },
                onClickHistory = {
                    navController.navigateToHistoryScreen()
                }
            )
        )
        historyScreen(
            navEvent = HistoryNavEvent(

            )
        )
        gameScreen(
            navEvent = GameNavEvent(

            )
        )

    }
}