package com.example.littlelemon

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.littlelemon.ui.theme.LittleLemonTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    // @ref https://www.coursera.org/learn/working-with-data-in-android/supplement/zhOyC/room-in-detail
    private val database by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "little-lemon-capstone.db").build()
    }

    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }

    private suspend fun fetchMenu(): List<MenuItemNetwork> {
        // this fetchMenu() is called by onCreate with lifecycleScope.launch{}
        val response: MenuNetwork = httpClient
            .get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
            .body<MenuNetwork>()
        //Log.d("LittleLemon", response.toString())
        return response.menu ?: emptyList() // default empty list, @ref https://stackoverflow.com/questions/39400238/list-of-or-collections-emptylist
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LittleLemonTheme {
                // add databaseMenuItems code here
                var databaseMenuItems = database.menuItemDao().getAll()
                    .observeAsState(emptyList()) // default empty list
                    .value

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppScreen(applicationContext, databaseMenuItems)
                }

            }
        }

        lifecycleScope.launch(Dispatchers.IO) {
            if (database.menuItemDao().isEmpty()) {
                // TODO Step 3: add code here
                val allMenuItem: List<MenuItemNetwork> = fetchMenu()

                //saveMenuToDatabase(menuItems)
                val allMenuItemRoom = allMenuItem.map { it.toMenuItemRoom() }
                database.menuItemDao().insertAll(*allMenuItemRoom.toTypedArray())
            }
        }

    }
}

@Composable
private fun AppScreen(context: Context, menuItems: List<MenuItemRoom>) {
    // Passing Data Between Screens: A Guide for Jetpack Compose Navigation, 2024 May 19
    // @ref https://medium.com/@tenigada/passing-data-between-screens-a-guide-for-jetpack-compose-navigation-7b556902b027
    //Scaffold(
    //    topBar = {
    //        TopAppBar()
    //    }
    //) {
    //    Box(
    //        modifier = Modifier
    //            .fillMaxSize()
    //            .padding(it)
    //    ) {
    //        NavigationComposable()
    //    }
    //}
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Navigation(context, menuItems)
    }
}

@Preview(showBackground = true)
@Composable
fun AppScreenPreview() {
    LittleLemonTheme {
        // @ref https://stackoverflow.com/questions/58743541/how-to-get-context-in-jetpack-compose
        val context = LocalContext.current
        //AppScreen(context)
        Home(rememberNavController(), emptyList())
    }
}