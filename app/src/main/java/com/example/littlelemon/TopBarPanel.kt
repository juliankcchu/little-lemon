package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun TopBarPanel(navController : NavHostController) {
    val context = LocalContext.current
    // Using SharedPreferences in Android Jetpack Compose | by Chintan Joshi | Medium, 2023 Jul 10,
    // https://chintanjoshi1.medium.com/using-sharedpreferences-in-android-jetpack-compose-f8e970ffbf06
    val preferencesManager = remember { SharedPreferenceManager(context) }
    val hasRegisteredUser : String = preferencesManager.getData("REGISTERED_USER_STATUS", "N")

    Row(
        //verticalAlignment = Alignment.CenterVertically,
        //horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp)
    ) {
        Box(Modifier.fillMaxSize()) {// https://foso.github.io/Jetpack-Compose-Playground/layout/box/
            Image( // https://developer.android.com/develop/ui/compose/graphics/images/customize
                contentScale = ContentScale.Fit,
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Little Lemon logo image",
                modifier = Modifier
                    .fillMaxHeight()
                    .size(185.dp) // https://stackoverflow.com/questions/70476714/jetpack-compose-make-image-scale-to-the-available-size-in-column-row
                    // https://medium.com/@abhineshchandra1234/alignment-in-jetpack-compose-e18b4a78ef8a
                    .align(Alignment.TopCenter)
            )
            if (hasRegisteredUser.equals("N")) {
                // do not show "Profile" when no Login user session
            } else {
                // show "Profile" when there is Login user session
                Image(
                    contentScale = ContentScale.Fit,
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "profile image",
                    modifier = Modifier
                        .fillMaxHeight()
                        .align(Alignment.CenterEnd)
                        .clickable {
                            // navigate to the Profile screen
                            navController.navigate(Profile.route)
                        }
                )
            }
        }
    }
}