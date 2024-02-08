package com.example.pastry_shop_android

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CartActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cart)

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

        val curUser = Gson().fromJson(intent.getStringExtra("user"), User::class.java)
        var cartItems: List<CartItem> = listOf()
        val itemType = object : TypeToken<List<CartItem>>(){}.type

        val temp: String? = intent.getStringExtra("carts")
        if(temp != null){
            cartItems = Gson().fromJson(intent.getStringExtra("carts"), itemType)
        }

        var userCartItems = cartItems.filter { cartItem ->  cartItem.user.id == curUser.id}

        if(userCartItems.isEmpty()){
            var button = findViewById<Button>(R.id.acceptButton)
            button.visibility = Button.INVISIBLE
            return
        }

        var userCartItemsView = findViewById<RecyclerView>(R.id.userCartItems)
        userCartItemsView.layoutManager = LinearLayoutManager(this)
        userCartItemsView.setHasFixedSize(true)
        userCartItemsView.adapter = CartBaseAdapter(userCartItems)

        var totalPrice = 0
        for(item in userCartItems) totalPrice += item.amount * item.dessert.price

        var totalPriceText = findViewById<TextView>(R.id.money)
        totalPriceText.text = totalPrice.toString() + " RSD"
    }

    fun acceptOrder(view: View){
        var cartItems: List<CartItem> = listOf()
        val itemType = object : TypeToken<List<CartItem>>(){}.type

        val temp: String? = intent.getStringExtra("carts")
        if(temp != null){
            cartItems = Gson().fromJson(intent.getStringExtra("carts"), itemType)
        }
        val curUser = Gson().fromJson(intent.getStringExtra("user"), User::class.java)
        cartItems = cartItems.filter { item -> item.user.id != curUser.id }

        var intent = Intent(this.applicationContext, Buyer::class.java)
        intent.putExtra("desserts", this.intent.getStringExtra("desserts"))
        intent.putExtra("user", this.intent.getStringExtra("user"))
        intent.putExtra("users", this.intent.getStringExtra("users"))
        intent.putExtra("notifications", this.intent.getStringExtra("notifications"))
        intent.putExtra("carts", Gson().toJson(cartItems))
        intent.putExtra("comments", this.intent.getStringExtra("comments"))
        startActivity(intent)

        Toast.makeText(this, "Porudzbina poslata:", Toast.LENGTH_SHORT).show()
    }
}