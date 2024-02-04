package com.example.pastry_shop_android

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Buyer : AppCompatActivity() {

    val desserts: ArrayList<Dessert> = arrayListOf(
        Dessert(1, "Kolac1", "Opis1", "Sastojci1", R.drawable.kolac_1, 100, true, false),
        Dessert(2, "Kolac2", "Opis2", "Sastojci2", R.drawable.kolac_2, 200, false, false),
        Dessert(3, "Kolac3", "Opis3", "Sastojci3", R.drawable.kolac_3, 300, true, false),
        Dessert(4, "Kolac4", "Opis4", "Sastojci4", R.drawable.kolac_4, 400, false, false),
        Dessert(5, "Kolac5", "Opis5", "Sastojci5", R.drawable.kolac_5, 500, true, false),
        Dessert(6, "Kolac6", "Opis6", "Sastojci6", R.drawable.kolac_6, 600, false, false),
        Dessert(7, "Kolac7", "Opis7", "Sastojci7", R.drawable.kolac_7, 700, true, false),
        Dessert(8, "Torta1", "Opis8", "Sastojci8", R.drawable.torta_1, 1000, false, true),
        Dessert(9, "Torta2", "Opis9", "Sastojci9", R.drawable.torta_2, 2000, true, true),
        Dessert(10, "Torta3", "Opis10", "Sastojci10", R.drawable.torta_3, 3000, false, true),
        Dessert(11, "Torta4", "Opis11", "Sastojci11", R.drawable.torta_4, 4000, true, true),
        Dessert(12, "Torta5", "Opis12", "Sastojci12", R.drawable.torta_5, 5000, false, true),
        Dessert(13, "Torta6", "Opis13", "Sastojci13", R.drawable.torta_6, 6000, true, true)
    )

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

        var listView: RecyclerView = findViewById<RecyclerView>(R.id.listOfDesserts)
        listView.layoutManager = LinearLayoutManager(this)
        listView.setHasFixedSize(true)
        listView.adapter = DessertBaseAdapter(desserts)

    }
}