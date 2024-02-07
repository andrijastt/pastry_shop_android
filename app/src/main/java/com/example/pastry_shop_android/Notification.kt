package com.example.pastry_shop_android

class Notification(
    var user :User = User(),
    var items: List<Dessert>,
    var itemAmount: List<Int>,
    var approved: Boolean = false,
    ) {
}