package dev.stormery.pyrkon.app.navigation



sealed class NavigationScreens(val route: String) {
    object GuestsList : NavigationScreens("guests_list")
}