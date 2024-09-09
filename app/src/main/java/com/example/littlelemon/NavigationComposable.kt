package com.example.littlelemon

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(context: Context, menuItems: List<MenuItemRoom>) {
    val navController = rememberNavController()

    // Using SharedPreferences in Android Jetpack Compose | by Chintan Joshi | Medium, 2023 Jul 10,
    // https://chintanjoshi1.medium.com/using-sharedpreferences-in-android-jetpack-compose-f8e970ffbf06
    val preferencesManager = remember { SharedPreferenceManager(context) }

    // Determine the startDestination.
    // - If user data is stored in shared preferences, then the start destination is Onboarding,
    // - otherwise, the start destination is Home.
    val hasRegisteredUser : String = preferencesManager.getData("REGISTERED_USER_STATUS", "N")
    val startDestination : Destinations
    if (hasRegisteredUser.equals("N")) {
        startDestination = Onboarding
    } else {
        startDestination = Home
    }

    // Day 21 Compose Navigation, ChuLin, 2022 Sep 27,
    // https://ithelp.ithome.com.tw/articles/10299916
    NavHost(
        navController = navController,
        startDestination = startDestination.route
    ) {
        composable(Home.route) {
            Home(navController, menuItems)
        }
        composable(Onboarding.route) {
            Onboarding(navController)
        }
        composable(Profile.route) {
            Profile(navController)
        }
    }
}