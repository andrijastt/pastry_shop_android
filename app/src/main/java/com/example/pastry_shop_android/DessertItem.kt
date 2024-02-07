package com.example.pastry_shop_android

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.style.StyleSpan
import android.text.style.TypefaceSpan
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DessertItem: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dessert_item)

        val image = findViewById<ImageView>(R.id.dessertItemImg)
        val picture = this.intent.getIntExtra("dessertItemImg", R.drawable.kolac_8)
        image.setImageResource(picture)

        val itemName = findViewById<TextView>(R.id.dessertItemName)
        val name = this.intent.getStringExtra("dessertItemName")
        itemName.text = name

        val itemPrice = findViewById<TextView>(R.id.dessertItemPrice)
        val price = this.intent.getIntExtra("dessertItemPrice", 0)
        itemPrice.text = price.toString() + " RSD"

        val itemDescription = findViewById<TextView>(R.id.dessertItemDescription)
        val description = this.intent.getStringExtra("dessertItemDescription")

        var ss1 = SpannableString("Opis: ")
        ss1.setSpan(StyleSpan(Typeface.BOLD), 0, ss1.length, 0)
        itemDescription.append(ss1)
        itemDescription.append(description)

        val itemIngredients = findViewById<TextView>(R.id.dessertItemIngredients)
        val ingredients = this.intent.getStringExtra("dessertItemIngredients")
        var ss2 = SpannableString("Sastojci: ")
        ss2.setSpan(StyleSpan(Typeface.BOLD), 0, ss2.length, 0)
        itemIngredients.append(ss2)
        itemIngredients.append(ingredients)

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
                        startActivity(intent)
                    }
                    R.id.userData -> {
                        var intent = Intent(this.applicationContext, UserData::class.java)
                        intent.putExtra("user", this.intent.getStringExtra("user"))
                        intent.putExtra("users", this.intent.getStringExtra("users"))
                        startActivity(intent)
                    }
                    R.id.passwordData -> {
                        var intent = Intent(this.applicationContext, PasswordData::class.java)
                        intent.putExtra("user", this.intent.getStringExtra("user"))
                        intent.putExtra("users", this.intent.getStringExtra("users"))
                        startActivity(intent)
                    }
                    R.id.logOut -> {
                        var intent = Intent(this.applicationContext, LogIn::class.java)
                        intent.putExtra("users", this.intent.getStringExtra("users"))
                        startActivity(intent)
                    }
                    R.id.notifications -> {
                        var intent = Intent(this.applicationContext, NotificationActivity::class.java)
                        intent.putExtra("user", this.intent.getStringExtra("user"))
                        intent.putExtra("users", this.intent.getStringExtra("users"))
                        startActivity(intent)
                    }
                    else -> Toast.makeText(this, "Item: " + it.title, Toast.LENGTH_SHORT).show()
                }
                true
            }
            popUpMenu.show()
        }
    }

}