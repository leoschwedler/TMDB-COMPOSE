package com.example.moviesapi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.moviesapi.data.remote.api.ApiService
import com.example.moviesapi.presentation.screens.DetailScreen
import com.example.moviesapi.presentation.screens.HomeScreen

@Composable
fun NavControllerApp(
    apiService: ApiService,
    modifier: Modifier = Modifier
) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "HomeScreen") {

        composable(route = "HomeScreen") {
            HomeScreen(apiService, navController, modifier = modifier)
        }
        composable(
            route = "DetailScreen" + "/{itemId}",
            arguments = listOf(navArgument("itemId"){
                type = NavType.StringType }
            )
        ) { navBackStackEntry ->
            val itemId = requireNotNull(navBackStackEntry.arguments?.getString("itemId"))
            DetailScreen( navHostController = navController, itemId = itemId, apiService = apiService)
        }
    }

}