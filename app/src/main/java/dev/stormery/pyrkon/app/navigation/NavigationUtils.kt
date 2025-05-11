package dev.stormery.pyrkon.app.navigation

import androidx.navigation.NavController

object NavigationUtils {

    private lateinit var navController: NavController

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    fun navigateToGuestDetail(guestName: String) {
        navController.navigate("${NavigationScreens.GuestDetail.route}/$guestName")
    }
}