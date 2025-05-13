package dev.stormery.pyrkon.app.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.stormery.pyrkon.app.feature_Guests.presentation.GuestDetailScreen
import dev.stormery.pyrkon.app.feature_Guests.presentation.GuestsListScreen
import dev.stormery.pyrkon.app.feature_Guests.presentation.GuestsListViewModel

@Composable
fun NavigationHost(navController: NavHostController) {

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
            ){ navBackStackEntry ->
                val parentEntry = remember(navBackStackEntry) {
                    navController.getBackStackEntry(NavigationScreens.GuestsList.route)
                }
                val viewModel: GuestsListViewModel = hiltViewModel(parentEntry)
                val guestName = navBackStackEntry.arguments?.getString("guestName")?:""
                GuestDetailScreen(guestName,viewModel, onBackPressed = {navController.popBackStack(route = NavigationScreens.GuestsList.route, inclusive = false)})
            }
        }

}