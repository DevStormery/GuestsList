package dev.stormery.pyrkon.app.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.stormery.pyrkon.app.R
import dev.stormery.pyrkon.app.feature_Guests.presentation.GuestsListScreen
import dev.stormery.pyrkon.app.navigation.components.TopBar

@Composable
fun NavigationHost(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopBar(stringResource(R.string.guests_list))
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding).background(MaterialTheme.colorScheme.surface)
        ){
            NavHost(
                navController = navController,
                startDestination = NavigationScreens.GuestsList.route,
            ){
                composable(
                    route = NavigationScreens.GuestsList.route,
                ){
                    GuestsListScreen()
                }
            }
        }
    }
}