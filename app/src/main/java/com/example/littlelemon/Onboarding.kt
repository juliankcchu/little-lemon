package com.example.littlelemon

import android.util.Log
import android.widget.Toast
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
fun Onboarding(navController : NavHostController) {
    Column {
        TopBarPanel(navController)
        OnboardingContentPanel(navController)
    }
}

@Composable
fun OnboardingContentPanel(navController : NavHostController) {
    val context = LocalContext.current
    val preferencesManager = remember { SharedPreferenceManager(context) }
    var firstname: TextFieldValue by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var lastname: TextFieldValue by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var email: TextFieldValue by remember {
        mutableStateOf(TextFieldValue(""))
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
            text = "Let's get to know you",
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
            // - If any of the input fields are empty, then the app should display this text:
            //   "Registration unsuccessful. Please enter all data."
            // - If all three fields contain the required text, then save the
            //   - first name,
            //   - last name and
            //   - email
            //   in the SharedPreferences and display the text "Registration successful!".
            // - When a user presses the Register button they should be logged into your app
            //   and the app must navigate to the Home screen.
            Log.d("LittleLemon", firstname.text)
            Log.d("LittleLemon", lastname.text)
            Log.d("LittleLemon", email.text)
            if (isValidRegistration(firstname.text, lastname.text, email.text)) {
                doRegistration(preferencesManager,firstname.text, lastname.text, email.text);
                Toast.makeText(context,
                    "Registration successful!",
                    Toast.LENGTH_LONG
                ).show()
                navController.navigate(Home.route)
            } else {
                Toast.makeText(context,
                    "Registration unsuccessful. Please enter all data.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    ) {
        Text(
            text = "Register",
            color = Color(0xFF000000)
        )
    }
}

fun isValidRegistration(firstname: String, lastname: String, email: String) : Boolean {
    return isValidFirstname((firstname)) &&
            isValidLastname((lastname)) &&
            isValidEmail((email));
}

fun isValidFirstname(str: String): Boolean {
    return isValidInputString(str);
}

fun isValidLastname(str: String): Boolean {
    return isValidInputString(str);
}

fun isValidEmail(str: String): Boolean {
    return isValidInputString(str);
}

fun isValidInputString(str: String) : Boolean {
    if (str.isBlank() || str.isEmpty()) {
        return false;
    }
    return true;
}

fun doRegistration(preferencesManager: SharedPreferenceManager, firstname: String, lastname: String, email: String) {
    preferencesManager.saveData("REGISTERED_USER_STATUS", "Y");
    preferencesManager.saveData("REGISTERED_USER_FIRSTNAME", firstname);
    preferencesManager.saveData("REGISTERED_USER_LASTNAME", lastname);
    preferencesManager.saveData("REGISTERED_USER_EMAIL", email);
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    Onboarding(rememberNavController())
}