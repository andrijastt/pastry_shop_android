package com.example.pastry_shop_android

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import android.widget.ViewFlipper
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Buyer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.buyer)

        val dropdown_menu = findViewById<ImageView>(R.id.dropdown_menu)
        dropdown_menu.setOnClickListener {
            val popUpMenu = PopupMenu(this, dropdown_menu)
            popUpMenu.inflate(R.menu.popup_menu)
            popUpMenu.setOnMenuItemClickListener{

                when(it.itemId){

                    R.id.home -> {
                        var intent = Intent(this.applicationContext, Buyer::class.java)
                        intent.putExtra("user", this.intent.getStringExtra("user"))
                        intent.putExtra("users", this.intent.getStringExtra("users"))
                        intent.putExtra("notifications", this.intent.getStringExtra("notifications"))
                        startActivity(intent)
                    }
                    R.id.userData -> {
                        var intent =Intent(this.applicationContext, UserData::class.java)
                        intent.putExtra("user", this.intent.getStringExtra("user"))
                        intent.putExtra("users", this.intent.getStringExtra("users"))
                        intent.putExtra("notifications", this.intent.getStringExtra("notifications"))
                        startActivity(intent)
                    }
                    R.id.passwordData -> {
                        var intent =Intent(this.applicationContext, PasswordData::class.java)
                        intent.putExtra("user", this.intent.getStringExtra("user"))
                        intent.putExtra("users", this.intent.getStringExtra("users"))
                        intent.putExtra("notifications", this.intent.getStringExtra("notifications"))
                        startActivity(intent)
                    }
                    R.id.logOut -> {
                        var intent = Intent(this.applicationContext, LogIn::class.java)
                        intent.putExtra("users", this.intent.getStringExtra("users"))
                        intent.putExtra("notifications", this.intent.getStringExtra("notifications"))
                        startActivity(intent)
                    }
                    R.id.notifications -> {
                        var intent = Intent(this.applicationContext, NotificationActivity::class.java)
                        intent.putExtra("user", this.intent.getStringExtra("user"))
                        intent.putExtra("users", this.intent.getStringExtra("users"))
                        intent.putExtra("notifications", this.intent.getStringExtra("notifications"))
                        startActivity(intent)
                    }
                    else -> Toast.makeText(this, "Item: " + it.title, Toast.LENGTH_SHORT).show()
                }
                true
            }
            popUpMenu.show()
        }

        val stringTemp = intent.getStringExtra("desserts")
        val itemType = object : TypeToken<List<Dessert>>(){}.type
        var desserts: List<Dessert> = listOf()
        if(stringTemp != null)
            desserts = Gson().fromJson(stringTemp, itemType)

        var listView: RecyclerView = findViewById<RecyclerView>(R.id.listOfDesserts)
        listView.layoutManager = LinearLayoutManager(this)
        listView.setHasFixedSize(true)

        val onlyDessert: List<Dessert> = desserts.filter { dessert -> !dessert.isCake}
        listView.adapter = DessertBaseAdapter(onlyDessert)

        var listViewCakes: RecyclerView = findViewById<RecyclerView>(R.id.listOfCakes)
        listViewCakes.layoutManager = LinearLayoutManager(this)
        listViewCakes.setHasFixedSize(true)

        val onlyCakes: List<Dessert> = desserts.filter { dessert -> dessert.isCake}
        listViewCakes.adapter = DessertBaseAdapter(onlyCakes)

        val carousel = findViewById<ViewFlipper>(R.id.carousel)
        val promotions: List<Dessert> = desserts.filter { dessert -> dessert.promotion}

    }
}