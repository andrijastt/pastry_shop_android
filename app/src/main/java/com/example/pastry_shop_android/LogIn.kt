package com.example.pastry_shop_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LogIn : AppCompatActivity() {

    public var users: List<User> = listOf(
        User(1, "andrija", "andrija123", "Andrija",
            "Stojanovic", "+381600800249", "Vladetina"),
        User(2, "marija", "marija123", "Marija",
            "Nikolic", "+381644960196", "Dalmatinska")
    )

    val desserts: List<Dessert> = listOf(
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

    public var notifications: List<Notification> = listOf(
        Notification(users[0], listOf(desserts[0], desserts[4], desserts[7]), listOf(1, 2, 3), true),
        Notification(users[0], listOf(desserts[1], desserts[2]), listOf(1, 3), false),
        Notification(users[1], listOf(desserts[2], desserts[8], desserts[3]), listOf(3, 2, 4), true)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.log_in)
    }

    fun login(view: View) {

        val temp: String? = intent.getStringExtra("users")
        if(temp != null){
            val itemType = object : TypeToken<List<User>>(){}.type
            users = Gson().fromJson(temp, itemType)
        }

        val username: String = findViewById<EditText>(R.id.username).text.toString()
        val password: String = findViewById<EditText>(R.id.password).text.toString()

        var found = false
        var userFound = User()
        for(user in users){
            if(user.username.equals(username) && user.password.equals(password)){
                found = true
                userFound = user
                break
            }
        }
        if(found){
            val intent = Intent(this.applicationContext, Buyer::class.java)
            val gson = Gson()
            intent.putExtra("users", gson.toJson(users))
            intent.putExtra("user", gson.toJson(userFound))
            intent.putExtra("desserts", gson.toJson(desserts))
            intent.putExtra("notifications", gson.toJson(notifications))
            startActivity(intent)
        }
        else {
            Toast.makeText(this, "Uneti su pogresni podaci", Toast.LENGTH_SHORT).show()
        }
    }


}