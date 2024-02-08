package com.example.pastry_shop_android

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.style.StyleSpan
import android.text.style.TypefaceSpan
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

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
                        intent.putExtra("desserts", this.intent.getStringExtra("desserts"))
                        intent.putExtra("user", this.intent.getStringExtra("user"))
                        intent.putExtra("users", this.intent.getStringExtra("users"))
                        intent.putExtra("notifications", this.intent.getStringExtra("notifications"))
                        intent.putExtra("carts", this.intent.getStringExtra("carts"))
                        intent.putExtra("comments", this.intent.getStringExtra("comments"))
                        startActivity(intent)
                    }
                    R.id.userData -> {
                        var intent =Intent(this.applicationContext, UserData::class.java)
                        intent.putExtra("desserts", this.intent.getStringExtra("desserts"))
                        intent.putExtra("user", this.intent.getStringExtra("user"))
                        intent.putExtra("users", this.intent.getStringExtra("users"))
                        intent.putExtra("notifications", this.intent.getStringExtra("notifications"))
                        intent.putExtra("carts", this.intent.getStringExtra("carts"))
                        intent.putExtra("comments", this.intent.getStringExtra("comments"))
                        startActivity(intent)
                    }
                    R.id.passwordData -> {
                        var intent =Intent(this.applicationContext, PasswordData::class.java)
                        intent.putExtra("desserts", this.intent.getStringExtra("desserts"))
                        intent.putExtra("user", this.intent.getStringExtra("user"))
                        intent.putExtra("users", this.intent.getStringExtra("users"))
                        intent.putExtra("notifications", this.intent.getStringExtra("notifications"))
                        intent.putExtra("carts", this.intent.getStringExtra("carts"))
                        intent.putExtra("comments", this.intent.getStringExtra("comments"))
                        startActivity(intent)
                    }
                    R.id.logOut -> {
                        var intent = Intent(this.applicationContext, LogIn::class.java)
                        intent.putExtra("desserts", this.intent.getStringExtra("desserts"))
                        intent.removeExtra("user")
                        intent.putExtra("users", this.intent.getStringExtra("users"))
                        intent.putExtra("notifications", this.intent.getStringExtra("notifications"))
                        intent.putExtra("carts", this.intent.getStringExtra("carts"))
                        intent.putExtra("comments", this.intent.getStringExtra("comments"))
                        startActivity(intent)
                    }
                    R.id.notifications -> {
                        var intent = Intent(this.applicationContext, NotificationActivity::class.java)
                        intent.putExtra("desserts", this.intent.getStringExtra("desserts"))
                        intent.putExtra("user", this.intent.getStringExtra("user"))
                        intent.putExtra("users", this.intent.getStringExtra("users"))
                        intent.putExtra("notifications", this.intent.getStringExtra("notifications"))
                        intent.putExtra("carts", this.intent.getStringExtra("carts"))
                        intent.putExtra("comments", this.intent.getStringExtra("comments"))
                        startActivity(intent)
                    }
                    R.id.cart -> {
                        var intent = Intent(this.applicationContext, CartActivity::class.java)
                        intent.putExtra("desserts", this.intent.getStringExtra("desserts"))
                        intent.putExtra("user", this.intent.getStringExtra("user"))
                        intent.putExtra("users", this.intent.getStringExtra("users"))
                        intent.putExtra("notifications", this.intent.getStringExtra("notifications"))
                        intent.putExtra("carts", this.intent.getStringExtra("carts"))
                        intent.putExtra("comments", this.intent.getStringExtra("comments"))
                        startActivity(intent)
                    }
                    else -> Toast.makeText(this, "Item: " + it.title, Toast.LENGTH_SHORT).show()
                }
                true
            }
            popUpMenu.show()
        }

        var addCommentButton : Button= findViewById<Button>(R.id.addComment)
        addCommentButton.setOnClickListener {
            var comment = findViewById<EditText>(R.id.newComment).text.toString()
            if(!comment.equals("")){

            }
        }
    }

    fun addToCartAction(view: View){

        var numberString = findViewById<EditText>(R.id.numberOfItems).text.toString()
        var number = numberString.toInt()
        if(number <= 0){
            Toast.makeText(this, "Uneli ste broj manji 0", Toast.LENGTH_SHORT).show()
            return
        }

        val curUser = Gson().fromJson(intent.getStringExtra("user"), User::class.java)
        var cartItems: ArrayList<CartItem> = arrayListOf()
        val itemType = object : TypeToken<ArrayList<CartItem>>(){}.type

        val temp: String? = intent.getStringExtra("carts")
        if(temp != null){
            cartItems = Gson().fromJson(intent.getStringExtra("carts"), itemType)
        }

        var dessertID = this.intent.getIntExtra("dessertItemID", -1)

        var cartItem = cartItems.find { item -> item.user.id == curUser.id && item.dessert.id == dessertID}

        if(cartItem != null){
            var index = cartItems.indexOf(cartItem)
            cartItems[index].amount += number
        }
        else {
            var curDessert = Gson().fromJson(this.intent.getStringExtra("dessertItem"), Dessert::class.java)
            cartItem = CartItem(curUser, curDessert, number)
            cartItems.add(cartItem)
            this.intent.putExtra("carts", Gson().toJson(cartItems))
            Toast.makeText(this, "Uspesno ste dodali kolac u korpu", Toast.LENGTH_SHORT).show()
        }
    }
}