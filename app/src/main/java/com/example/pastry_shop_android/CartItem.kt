package com.example.pastry_shop_android

class CartItem(
    var user :User = User(),
    var dessert: Dessert = Dessert(),
    var amount: Int = 0,
) {
}