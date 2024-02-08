package com.example.pastry_shop_android

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PasswordData: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.password_data)

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
    }

    fun changePassword(view: View){

        val user: User = Gson().fromJson(this.intent.getStringExtra("user"), User::class.java)

        Log.i("passwordDataBefore", Gson().toJson(user))

        val oldPassword = findViewById<EditText>(R.id.oldPassword).text.toString()
        val newPassword = findViewById<EditText>(R.id.newPassword).text.toString()
        val newPasswordConfirm = findViewById<EditText>(R.id.newPasswordConfirm).text.toString()

        if(!oldPassword.equals(user.password) || !newPassword.equals(newPasswordConfirm)){
            Toast.makeText(this, "Pogresno uneti podaci", Toast.LENGTH_SHORT).show()
            return
        }

        user.password = newPassword
        val itemType = object : TypeToken<ArrayList<User>>(){}.type
        val users: ArrayList<User> = Gson().fromJson(this.intent.getStringExtra("users"), itemType)

        for (i in 0..users.count()) {
            if(users[i].id == user.id){
                users[i] = user
                break }
        }

        Toast.makeText(this, "Lozinka uspesno promenjena", Toast.LENGTH_SHORT).show()

        findViewById<EditText>(R.id.oldPassword).text = null
        findViewById<EditText>(R.id.newPassword).text = null
        findViewById<EditText>(R.id.newPasswordConfirm).text = null

        Log.i("passwordDataAfter", Gson().toJson(user))

        intent.putExtra("user", Gson().toJson(user))
        intent.putExtra("users", Gson().toJson(users))
    }

}