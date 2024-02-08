package com.example.pastry_shop_android

class Comment(
    var user: User = User(),
    var dessert: Dessert = Dessert(),
    var comment: String = ""
) {
}