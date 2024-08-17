package com.example.littlelemon

import android.util.Log
import android.widget.Toast
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
import androidx.compose.material3.TextField
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
fun Onboarding(navController : NavHostController) {
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
            text = "Let's get to know you",
            color = Color(0xFFFFFFFF)
        )
        Text(
            modifier = Modifier
                .background(color = Color(0xFF495E57))
                .fillMaxWidth()
                .border(BorderStroke(2.dp, SolidColor(Color.Red))),
            text = "Personal Information",
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
        TextField(
            modifier = Modifier.padding(10.dp),
            label = { Text(text = "Email") },
            value = email,
            onValueChange = {
                email = it
            }
        )
        //TextField(
        //    modifier = Modifier.padding(10.dp),
        //    label = { Text(text = "Password") },
        //    visualTransformation = PasswordVisualTransformation(),
        //    value = password,
        //    onValueChange = {
        //        password = it
        //    }
        //)
        Button(
            modifier = Modifier.padding(10.dp),
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
                Log.d("AAA", "${firstname.text}")
                Log.d("AAA", "${lastname.text}")
                Log.d("AAA", "${email.text}")
                if (isValidRegistration("${firstname.text}", "${lastname.text}", "${email.text}")) {
                    doRegistration(preferencesManager,"${firstname.text}", "${lastname.text}", "${email.text}");
                    Toast.makeText(
                        context,
                        "Registration successful!",
                        Toast.LENGTH_LONG
                    ).show()
                    navController.navigate(Home.route)
                } else {
                    Toast.makeText(
                        context,
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

//@Composable
fun doRegistration(preferencesManager: SharedPreferenceManager, firstname: String, lastname: String, email: String) {
    //val context = LocalContext.current
    //val preferencesManager = remember { SharedPreferenceManager(context) }
    //val data = remember { mutableStateOf(preferencesManager.getData("REGISTERED_USER", "N")) }
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