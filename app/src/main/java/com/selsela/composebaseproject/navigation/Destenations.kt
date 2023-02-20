package com.selsela.composebaseproject.navigation

import androidx.annotation.StringRes
import androidx.navigation.NavController
import com.selsela.composebaseproject.R
import com.selsela.composebaseproject.navigation.Navigation.navigateWithClearBackStack
import com.selsela.composebaseproject.util.toJson

enum class Screens(
    @StringRes val title: Int? = null
) {
    Splash,
    Login(title = R.string.login),
    VerifyCode(title = R.string.verify_code),
    Home(title = R.string.home),
    Categories(title = R.string.categories),
    Details(title = R.string.details)
}

class NavigationActions(private val navController: NavController) {
    fun navigateToHome() {
        navController.navigate(Screens.Home.name) {
            navigateWithClearBackStack(navController)
        }
    }

    fun navigateToLogin() {
        navController.navigate(Screens.Login.name)
    }

    fun navigateToCategories() {
        navController.navigate(Screens.Categories.name)
    }

    fun navigateToVerify() {
        navController.navigate(Screens.VerifyCode.name)
    }

    fun navigateToCategoryDetails(index: Int) {
        navController.navigate(Screens.Details.name+"/$index")

    }

}