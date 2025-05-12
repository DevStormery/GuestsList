package dev.stormery.pyrkon.app.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.stormery.pyrkon.app.feature_Guests.presentation.GuestDetailScreen
import dev.stormery.pyrkon.app.feature_Guests.presentation.GuestsListScreen

@Composable
fun NavigationHost(navController: NavHostController) {
    Box(
        modifier = Modifier.background(MaterialTheme.colorScheme.surface)
    ){
        NavHost(
            navController = navController,
            startDestination = NavigationScreens.GuestsList.route,
        ){
            NavigationUtils.setNavController(navController)
            composable(
                route = NavigationScreens.GuestsList.route,
            ){
                GuestsListScreen()
            }
            composable(
                route = NavigationScreens.GuestDetail.route+"/{guestName}",
                arguments = listOf(
                    navArgument("guestName"){
                        type = NavType.StringType
                        nullable = false
                    }
                )
            ){
                val guestName = it.arguments?.getString("guestName")?:""
                GuestDetailScreen(guestName, onBackPressed = {navController.popBackStack()})
            }
        }
    }
}