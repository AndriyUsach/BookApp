package com.books.app.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.books.app.presentation.details.DetailsScreenRoute
import com.books.app.presentation.home.HomeScreenRoute
import com.books.app.presentation.splash.SplashScreen


private enum class Route {
    Splash,
    Home,
    Detail,
}

@Composable
fun NavigationHost(
    navController: NavHostController = rememberNavController()
) {

    NavHost(
        navController = navController,
        startDestination = Route.Splash.name,
        modifier = Modifier.fillMaxSize()
    ) {

        composable(route = Route.Splash.name) {

            SplashScreen {
                navController.navigate(Route.Home.name) {
                    popUpTo(Route.Splash.name) {
                        inclusive = true
                    }
                }
            }

        }

        composable(route = Route.Home.name) {
            HomeScreenRoute() { bookId: Int ->
                navController.navigate("${Route.Detail.name}/$bookId")
            }
        }

        composable(
            route = "${Route.Detail.name}/{bookId}",
            arguments = listOf(navArgument("bookId") { type = NavType.IntType })
        ) {backStackEntry ->
            val bookId = backStackEntry.arguments?.getInt("bookId")
            DetailsScreenRoute(
                bookId = bookId,
                onBookClicked = { bookId: Int ->
                    navController.navigate("${Route.Detail.name}/$bookId")
                },
                navUp = {
                    navController.navigateUp()
                }
            )
        }

    }
}