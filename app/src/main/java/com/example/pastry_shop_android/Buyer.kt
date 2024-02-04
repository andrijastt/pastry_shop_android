package com.example.pastry_shop_android

import android.os.Bundle
import android.view.Gravity.RIGHT
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.helper.widget.Carousel


class Buyer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.buyer)

        val dropdown_menu = findViewById<ImageView>(R.id.dropdown_menu)
        dropdown_menu.setOnClickListener {
            val popUpMenu = PopupMenu(this, dropdown_menu)
            popUpMenu.inflate(R.menu.popup_menu)
            popUpMenu.setOnMenuItemClickListener{
                Toast.makeText(this, "Item: " + it.title, Toast.LENGTH_SHORT).show()
                true
            }
            popUpMenu.show()
        }

        val carousel: Carousel = findViewById(R.id.carousel)
    }
}