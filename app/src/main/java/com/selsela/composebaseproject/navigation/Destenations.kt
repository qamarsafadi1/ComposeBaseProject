package com.selsela.composebaseproject.navigation

import androidx.annotation.StringRes
import androidx.navigation.NavController
import com.selsela.composebaseproject.R
import com.selsela.composebaseproject.navigation.Navigation.navigateWithClearBackStack

enum class Screens(
    @StringRes val title: Int? = null
) {
    Splash,
    Login(title = R.string.login),
    VerifyCode(title = R.string.verify_code),
    Home(title = R.string.home),
    Notifications(title = R.string.notifications)
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

    fun navigateToNotifications() {
        navController.navigate(Screens.Notifications.name)
    }

    fun navigateToVerify() {
        navController.navigate(Screens.VerifyCode.name)
    }

    fun navigateToOrderDetails(id: Int) {
        navController.navigate("notifications/${id}")
    }

}