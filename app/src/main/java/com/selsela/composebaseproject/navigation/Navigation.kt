package com.selsela.composebaseproject.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder

object Navigation {

    fun NavOptionsBuilder.navigateWithClearBackStack(navController: NavController) {
        popUpTo(navController.graph.id) {
            inclusive = true
        }
    }

    fun showingTopBar(currentRoute: Screens?): Boolean {
        return currentRoute?.title != null
    }
}