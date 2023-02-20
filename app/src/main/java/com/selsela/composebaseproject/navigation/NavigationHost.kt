package com.selsela.composebaseproject.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType.Companion.IntType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.selsela.composebaseproject.ui.screens.auth.view.LoginScreen
import com.selsela.composebaseproject.ui.screens.auth.view.VerifyScreen
import com.selsela.composebaseproject.ui.screens.categories.CategoryViewModel
import com.selsela.composebaseproject.ui.screens.categories.details.CategoryDetailsScreen
import com.selsela.composebaseproject.ui.screens.categories.list.CategoriesScreen
import com.selsela.composebaseproject.ui.screens.home.HomeScreen
import com.selsela.composebaseproject.ui.screens.splash.SplashScreen

@Composable
fun NavigationHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screens.Splash.name,
    navActions: NavigationActions = remember(navController) {
        NavigationActions(navController)
    },
) {
    NavHost(navController = navController, startDestination = startDestination, modifier) {
        composable(Screens.Splash.name) {
            SplashScreen(
                onFinish = navActions::navigateToHome
            )
        }
        composable(Screens.Home.name) {
            HomeScreen(
                onDataClick = navActions::navigateToCategories,
                onAuthClick = navActions::navigateToLogin
            )
        }
        composable(Screens.Login.name) {
            LoginScreen(
                goToHome = navActions::navigateToHome,
                goToVerify = navActions::navigateToVerify
            )
        }
        composable(Screens.Categories.name) {
            CategoriesScreen(onClick = navActions::navigateToCategoryDetails)
        }
        composable(Screens.VerifyCode.name) {
            VerifyScreen(
                goToHome = navActions::navigateToHome
            )
        }

        // Note: You shouldn't be passing Parcelables at all as arguments
        // and never has been a recommended pattern
        // instead pass an unique key that represent your data and handle it
        // inside you vm or repository
        composable(
            Screens.Details.name + "/{index}",
            arguments = listOf(navArgument("index") {
                type = IntType
            })
        ) {
            val mainEntity = remember(it) {
                navController.getBackStackEntry(Screens.Categories.name)
            }
            val parentViewModel = hiltViewModel<CategoryViewModel>(mainEntity)
            val itemIndex = it.arguments?.getInt("index")
            CategoryDetailsScreen(
                index = itemIndex ?: -1,
                viewModel = parentViewModel
            )
        }
    }
}