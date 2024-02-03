package com.example.pastry_shop_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class LogIn : AppCompatActivity() {

    val users: List<User> = listOf(
        User(1, "andrija", "andrija123", "Andrija",
            "Stojanovic", "+381600800249", "Vladetina"),
        User(2, "marija", "marija123", "Marija",
            "Nikolic", "+381644960196", "Dalmatinska")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.log_in)
    }

    fun login(view: View) {
        val username: String = findViewById<EditText>(R.id.username).text.toString()
        val password: String = findViewById<EditText>(R.id.password).text.toString()

        var found = false
        for(user in users){
            if(user.username.equals(username) && user.password.equals(password)){
                found = true
                break
            }
        }
        if(found){
            val intent = Intent(this.applicationContext, Buyer::class.java)
            startActivity(intent)
        }
        else {
            Toast.makeText(this, "Uneti su pogresni podaci", Toast.LENGTH_SHORT).show()
        }
    }


}