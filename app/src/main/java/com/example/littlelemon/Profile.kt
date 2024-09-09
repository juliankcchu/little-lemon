package com.example.littlelemon

import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun Profile(navController : NavHostController) {
    Column {
        TopBarPanel(navController)
        ProfileContentPanel(navController)
    }
}

@Composable
fun ProfileContentPanel(navController : NavHostController) {
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
        //horizontalAlignment = Alignment.CenterHorizontally,
        //verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF495E57))
            .padding(start = 8.dp, end = 8.dp, top = 18.dp, bottom = 18.dp)
    ) {
        Text(
            text = "My Profile",
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            color = Color(0xFFFFFFFF),
            modifier = Modifier
                .fillMaxWidth()
        )
    }

    Text(
        text = "Personal Information",
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            //.padding(8.dp)
            .padding(start = 8.dp, end = 8.dp, top = 18.dp, bottom = 18.dp)
    )

    OutlinedTextField(
        singleLine = true,
        value = firstname,
        label = { Text(text = "First name") },
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFFFFFF))
            .padding(start = 10.dp, end = 10.dp, top = 0.dp, bottom = 10.dp),
        onValueChange = {
            firstname = it
        }
    )
    OutlinedTextField(
        singleLine = true,
        value = lastname,
        label = { Text(text = "Last name") },
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFFFFFF))
            .padding(start = 10.dp, end = 10.dp, top = 0.dp, bottom = 10.dp),
        onValueChange = {
            lastname = it
        }
    )
    OutlinedTextField(
        singleLine = true,
        value = email,
        label = { Text(text = "Email") },
        //visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFFFFFF))
            .padding(start = 10.dp, end = 10.dp, top = 0.dp, bottom = 10.dp),
        onValueChange = {
            email = it
        }
    )

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFFFFFF))
            .padding(start = 10.dp, end = 10.dp, top = 0.dp, bottom = 10.dp),
        colors = ButtonDefaults.buttonColors(
            Color(0xFFF4CE14)
        ),
        onClick = {
            // When a user clicks on the Log out button, your app should:
            // - clear all the data from shared preferences and
            // - navigate to the Onboarding screen
            Log.d("LittleLemon", firstname.text)
            Log.d("LittleLemon", lastname.text)
            Log.d("LittleLemon", email.text)

            // clear all the data from shared preferences
            preferencesManager.clearAll()

            // navigate to the Onboarding screen
            navController.navigate(Onboarding.route)
        }
    ) {
        Text(
            text = "Log out",
            color = Color(0xFF000000)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    val context = LocalContext.current
    val preferencesManager = remember { SharedPreferenceManager(context) }
    preferencesManager.saveData("REGISTERED_USER_STATUS", "Y");
    preferencesManager.saveData("REGISTERED_USER_FIRSTNAME", "Julian");
    preferencesManager.saveData("REGISTERED_USER_LASTNAME", "Chu");
    preferencesManager.saveData("REGISTERED_USER_EMAIL", "julian.chu@email.com");
    Profile(rememberNavController())
}