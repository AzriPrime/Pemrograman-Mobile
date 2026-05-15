package com.example.scrollablelistcompose.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.scrollablelistcompose.view.DetailScreen
import com.example.scrollablelistcompose.view.HomeScreen
import com.example.scrollablelistcompose.viewmodel.FishViewModel
import com.example.scrollablelistcompose.viewmodel.FishViewModelFactory

@Composable
fun FishApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    // Membuat ViewModel melalui ViewModelFactory dengan parameter String (ketentuan b)
    val viewModel: FishViewModel = viewModel(
        factory = FishViewModelFactory(owner = "Azri")
    )

    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { fullWidth -> fullWidth },
                animationSpec = tween(300)
            ) + fadeIn(animationSpec = tween(300))
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { fullWidth -> -fullWidth },
                animationSpec = tween(300)
            ) + fadeOut(animationSpec = tween(300))
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { fullWidth -> -fullWidth },
                animationSpec = tween(300)
            ) + fadeIn(animationSpec = tween(300))
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { fullWidth -> fullWidth },
                animationSpec = tween(300)
            ) + fadeOut(animationSpec = tween(300))
        }
    ) {
        composable("home") {
            HomeScreen(
                viewModel = viewModel,
                onNavigateToDetail = { fishIndex ->
                    // Log navigasi ke detail (ketentuan d.c)
                    viewModel.onDetailClick(fishIndex)
                    navController.navigate("detail/$fishIndex")
                }
            )
        }

        composable(
            route = "detail/{fishIndex}",
            arguments = listOf(
                navArgument("fishIndex") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val fishIndex = backStackEntry.arguments?.getInt("fishIndex") ?: 0
            DetailScreen(
                viewModel = viewModel,
                fishIndex = fishIndex,
                onBack = { navController.navigateUp() }
            )
        }
    }
}
