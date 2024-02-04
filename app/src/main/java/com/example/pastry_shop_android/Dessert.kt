package com.example.pastry_shop_android

class Dessert(
    val id: Int = 0,
    val name: String = "dessert",
    val description: String = "description",
    val ingredients: String = "ingredients",
    val picture: String = "kolac_1.jpg",
    val price: Int = 1000,
    val promotion: Boolean = false,
    val isCake: Boolean = false,
) {
}