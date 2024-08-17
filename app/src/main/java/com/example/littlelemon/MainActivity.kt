package com.example.littlelemon

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.littlelemon.ui.theme.LittleLemonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LittleLemonTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                }
                AppScreen(applicationContext)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LittleLemonTheme {
        Greeting("Android")
    }
}

@Composable
private fun AppScreen(context: Context) {
//    Scaffold(
//        topBar = {
//            TopAppBar()
//        }
//    ) {
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(it)
//        ) {
//            NavigationComposable()
//        }
//    }
    Box(
        modifier = Modifier
            .fillMaxSize()
//            .padding(it)
    ) {
        Navigation(context)
    }
}

@Preview(showBackground = true)
@Composable
fun AppScreenPreview() {
    LittleLemonTheme {
        // https://stackoverflow.com/questions/58743541/how-to-get-context-in-jetpack-compose
        val context = LocalContext.current
        AppScreen(context)
    }
}
