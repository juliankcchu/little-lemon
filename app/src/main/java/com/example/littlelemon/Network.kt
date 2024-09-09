package com.example.littlelemon

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// data URL:
// https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json
//
// expected JSON payload:
//{
//    "menu": [
//    {
//        "id": 1,
//        "title": "Greek Salad",
//        "description": "The famous greek salad of crispy lettuce, peppers, olives, our Chicago.",
//        "price": "10",
//        "image": "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/greekSalad.jpg?raw=true",
//        "category": "starters"
//    },
//    {
//        "id": 2,
//        "title": "Lemon Desert",
//        "description": "Traditional homemade Italian Lemon Ricotta Cake.",
//        "price": "10",
//        "image": "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/lemonDessert%202.jpg?raw=true",
//        "category": "desserts"
//    },
//    ...
//    ]
// }

@Serializable
data class MenuNetwork(
    @SerialName("menu")
    val menu: List<MenuItemNetwork>
)

@Serializable
data class MenuItemNetwork(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("price")
    val price: Double,
    @SerialName("image")
    val image: String,
    @SerialName("category")
    val category: String
) {
    fun toMenuItemRoom() = MenuItemRoom(
        id = this.id,
        title = this.title,
        description = this.description,
        price = this.price,
        image = this.image,
        category = this.category
    )
}