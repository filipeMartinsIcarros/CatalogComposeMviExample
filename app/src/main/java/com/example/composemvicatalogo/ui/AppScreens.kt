package com.example.composemvicatalogo.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composemvicatalogo.ui.home.lifestyles.LifeStyleListScreen
import com.example.composemvicatalogo.ui.home.lifestyles.LifeStyleViewModel
import com.example.composemvicatalogo.ui.home.lifestyles.search.SearchLifeStyleScreen
import com.example.composemvicatalogo.ui.home.lifestyles.search.SearchLifeStyleViewModel
import com.example.composemvicatalogo.ui.navigation.Arguments.SearchLifeStyleArgument
import com.example.composemvicatalogo.ui.navigation.Route
import com.example.composemvicatalogo.ui.navigation.Route.SearchLifeStyle
import com.example.composemvicatalogo.ui.theme.AppTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppScreens() {
    val navController = rememberNavController()
    AppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Top App Bar")
                    },
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = Color.White,
                    elevation = 10.dp
                )
            },
            content = {
                NavHost(
                    navController = navController,
                    startDestination = Route.MainRoute,
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    mainRoute(navController = navController)
                    searchLifeStyle(navController = navController)
                }
            }
        )
    }
}

private fun NavGraphBuilder.mainRoute(navController: NavController) {
    composable(Route.MainRoute) {
        val lifeStyleViewModel = koinViewModel<LifeStyleViewModel>()
        LifeStyleListScreen(viewModel = lifeStyleViewModel, navController = navController)
    }
}

private fun NavGraphBuilder.searchLifeStyle(navController: NavController) {
    composable(
        route = "$SearchLifeStyle/{$SearchLifeStyleArgument}",
        arguments = listOf(navArgument(SearchLifeStyleArgument) { type = NavType.StringType })
    ) {
        val searchLifeStyleViewModel = koinViewModel<SearchLifeStyleViewModel>()
        SearchLifeStyleScreen(viewModel = searchLifeStyleViewModel)
    }
}
