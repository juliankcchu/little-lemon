package com.example.littlelemon

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun Home(navController : NavHostController, allMenuItems: List<MenuItemRoom>) {
    Column {
        TopPanel(navController)
        ContentPanel(navController, allMenuItems)
    }
}

@Composable
fun TopPanel(navController : NavHostController) {
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

@Composable
fun ContentPanel(navController : NavHostController, allMenuItems: List<MenuItemRoom>) {
    var menuItems: List<MenuItemRoom> = allMenuItems

    // filter by search phrase
    var search_phrase by remember { mutableStateOf<String>("") }

    // filter by categories
    var selected_category by remember { mutableStateOf("") }

    Column(
        //horizontalAlignment = Alignment.CenterHorizontally,
        //verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF495E57))
            .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.home_title),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFF4CE14)
        )
        Row(
            modifier = Modifier
                .padding(top = 0.dp, bottom = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 0.dp, bottom = 0.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.home_location),
                    fontSize = 24.sp,
                    color = Color(0xFFEDEFEE)
                )
                Text(
                    text = stringResource(id = R.string.home_description),
                    color = Color(0xFFEDEFEE),
                    fontSize = 14.sp,
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                )
            }
            Image(
                painter = painterResource(id = R.drawable.hero_image),
                contentDescription = "Hero image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(20.dp))
            )
        }

        TextField(
            singleLine = true,
            value = search_phrase,
            onValueChange = {
                search_phrase = it
            },
            label = { Text("Enter search phrase") },
            placeholder = { Text("Enter Search Phrase") },
            leadingIcon = { Icon( imageVector = Icons.Default.Search, contentDescription = "") },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF495E57))
        )
        Log.d("LittleLemon", "LowerPanel#search_phrase: ${search_phrase}")
        if (search_phrase.length > 0) {
            menuItems = menuItems.filter {
                it.title.contains(search_phrase, ignoreCase = true)
            }
        }
    }

    Column {
        Text(
            text = "ORDER FOR DELIVERY!",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        //var allCategories: List<String> = allMenuItems.map { it.category };
        val allCategoryItems : List<String> = (allMenuItems.map { it.category }).toSet().toList()
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(
                items = allCategoryItems, //allMenuItems,
                itemContent = { categoryItem -> //menuItem ->
                    // @ref https://stackoverflow.com/questions/66048620/how-to-change-button-background-color-on-click
                    Button(
                        modifier = Modifier.padding(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            (if (selected_category ==  categoryItem) Color(0xFFF4CE14) else Color(0xFFEDEFEE))
                        ),
                        onClick = {
                            if (selected_category == categoryItem) {
                                selected_category = ""
                            } else {
                                selected_category = categoryItem
                            }
                        }
                    ) {
                        Text(
                            text = categoryItem,
                            color = Color(0xFF000000)
                        )
                    }
                }
            )
        }
        Log.d("LittleLemon", "LowerPanel#selected_category: ${selected_category}")
        if (selected_category.length > 0) {
            menuItems = menuItems.filter {
                it.category.contains(selected_category, ignoreCase = true)
            }
        }

        // render a list of menu items
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 20.dp)
        ) {
            items(
                items = menuItems,
                itemContent = { menuItem ->
                    MenuDish(menuItem)
                }
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuDish(menuItem: MenuItemRoom) {
    Text(
        text = menuItem.title,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column {
            Text(
                text = menuItem.description,
                color = Color.Gray,
                modifier = Modifier
                    .padding(top = 5.dp, bottom = 5.dp)
                    .fillMaxWidth(.75f)
            )
            Text(
                text = "$%.2f".format(menuItem.price),
                color = Color.Gray
            )
        }
        // @ref https://bumptech.github.io/glide/int/compose.html
        GlideImage(
            model = menuItem.image,
            contentDescription = null
        )
    }
    Divider(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
        color = Color.LightGray,
        thickness = 1.dp
    )
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    val allMenuItem: List<MenuItemNetwork> = listOf(
        MenuItemNetwork(1,
            "Greek Salad",
            "The famous greek salad of crispy lettuce, peppers, olives, our Chicago.",
            10.0,
            "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/greekSalad.jpg?raw=true",
            "starters"),
        MenuItemNetwork(2,
            "Lemon Desert",
            "Traditional homemade Italian Lemon Ricotta Cake.",
            10.0,
            "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/lemonDessert%202.jpg?raw=true",
            "desserts"),
        MenuItemNetwork(3,
            "Grilled Fish",
            "Our Bruschetta is made from grilled bread that has been smeared with garlic and seasoned with salt and olive oil.",
            10.0,
            "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/grilledFish.jpg?raw=true",
            "mains"),
        MenuItemNetwork(4,
            "Pasta",
            "Penne with fried aubergines, cherry tomatoes, tomato sauce, fresh chilli, garlic, basil & salted ricotta cheese.",
            10.0,
            "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/pasta.jpg?raw=true",
            "mains"),
        MenuItemNetwork(5,
            "Bruschetta",
            "Oven-baked bruschetta stuffed with tomatos and herbs.",
            10.0,
            "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/bruschetta.jpg?raw=true",
            "starters"),
    )
    val allMenuItemRoom = allMenuItem.map { it.toMenuItemRoom() }

    //Home(rememberNavController(), emptyList())
    Home(rememberNavController(), allMenuItemRoom)
}