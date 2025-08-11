package com.v2.anilibrary.anime.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.v2.anilibrary.anime.presentation.detail.screens.DetailScreenRoot
import com.v2.anilibrary.anime.presentation.home.screens.HomeScreenRoot
import com.v2.anilibrary.core.presentation.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    AppTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Route.RouteGraph
        ) {
            navigation<Route.RouteGraph>(
                startDestination = Route.HomePage
            ) {
                composable<Route.HomePage> {
                    HomeScreenRoot(
                        onAnimeCardClicked = { id ->
                            navController.navigate(Route.DetailPage(id))
                        }
                    )
                }

                composable<Route.DetailPage> { entry ->
                    val args = entry.toRoute<Route.DetailPage>()
                    DetailScreenRoot(
                        animeId = args.animeId,
                    )
                }
            }
        }
    }
}