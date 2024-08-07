package com.example.littlelemon

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()
//    Determine the startDestination. If user data is
//    stored in shared preferences, then the start
//    destination is Onboarding, otherwise, the start
//    destination is Home.
    NavHost(
        navController = navController,
        startDestination = Onboarding.route
    ) {
        composable(Home.route) {
//            Home(navController = navController)
            Home()
        }
        composable(Onboarding.route) {
            Onboarding()
        }
        composable(Profile.route) {
            Profile()
        }
    }
}