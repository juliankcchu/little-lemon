package com.example.littlelemon

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun Profile(navController : NavHostController) {
    //val navController = rememberNavController()
    val context = LocalContext.current
    val preferencesManager = remember { SharedPreferenceManager(context) }
    var firstname: TextFieldValue by remember {
        mutableStateOf(
            TextFieldValue(
                preferencesManager.getData("REGISTERED_USER_FIRSTNAME", "")
            )
        )
    }
    var lastname: TextFieldValue by remember {
        mutableStateOf(
            TextFieldValue(
                preferencesManager.getData("REGISTERED_USER_LASTNAME", "")
            )
        )
    }
    var email: TextFieldValue by remember {
        mutableStateOf(
            TextFieldValue(
                preferencesManager.getData("REGISTERED_USER_EMAIL", "")
            )
        )
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp, top = 16.dp, bottom = 16.dp)
            .border(BorderStroke(2.dp, SolidColor(Color.Red))),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(2.dp, SolidColor(Color.Red))),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo Image"
        )
        Text(
            modifier = Modifier
                .background(color = Color(0xFF495E57))
                .fillMaxWidth()
                .border(BorderStroke(2.dp, SolidColor(Color.Red))),
            text = "Profile Information",
            color = Color(0xFFFFFFFF)
        )
        OutlinedTextField(
            modifier = Modifier.padding(10.dp),
            label = { Text(text = "First name") },
            value = firstname,
            onValueChange = {
                firstname = it
            }
        )
        OutlinedTextField(
            modifier = Modifier.padding(10.dp),
            label = { Text(text = "Last name") },
            value = lastname,
            onValueChange = {
                lastname = it
            }
        )
        OutlinedTextField(
            modifier = Modifier.padding(10.dp),
            label = { Text(text = "Email") },
            value = email,
            onValueChange = {
                email = it
            }
        )
        Button(
            modifier = Modifier.padding(10.dp),
            colors = ButtonDefaults.buttonColors(
                Color(0xFFF4CE14)
            ),
            onClick = {
                // When a user clicks on the Log out button, your app should:
                // - clear all the data from shared preferences and
                // - navigate to the Onboarding screen
                Log.d("AAA", "${firstname.text}")
                Log.d("AAA", "${lastname.text}")
                Log.d("AAA", "${email.text}")

                // clear all the data from shared preferences
                preferencesManager.clearAll()

                // navigate to the Onboarding screen
                navController.navigate(Onboarding.route)
            }
        ) {
            Text(
                text = "Logout",
                color = Color(0xFF000000)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    Profile(rememberNavController())
}