package com.stevdza.san.mongodemo.navigation

sealed class Screen(val route: String) {
    object Authentication : Screen(route = "authentication_screen")
    object Home : Screen(route = "home_screen")
}
