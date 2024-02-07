package com.example.pastry_shop_android

class Cart(
    var user :User = User(),
    var items: List<Dessert>,
    var itemAmount: List<Int>,
    var totalPrice: Int = 0,
) {
}