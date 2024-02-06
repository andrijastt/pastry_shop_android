package com.example.pastry_shop_android

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UserData: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_data)
    }

    fun changeUserData(view: View) {

        val user: User = Gson().fromJson(this.intent.getStringExtra("user"), User::class.java)

        val username = findViewById<EditText>(R.id.username).text.toString()
        val firstname = findViewById<EditText>(R.id.firstname).text.toString()
        val lastname = findViewById<EditText>(R.id.lastname).text.toString()
        val address = findViewById<EditText>(R.id.address).text.toString()
        val telephone = findViewById<EditText>(R.id.telephone).text.toString()

        if(username != null) user.username = username
        if(firstname != null) user.firstname = firstname
        if(lastname != null) user.lastname = lastname
        if(address != null) user.address = address
        if(telephone != null) user.telephone = telephone

        val itemType = object : TypeToken<List<User>>(){}.type
        val users: MutableList<User> =
            Gson().fromJson<List<User>>(this.intent.getStringExtra("users"), itemType).toMutableList()

        for (i in 0..users.count()) {
           if(users[i].id == user.id){
               users[i] = user
               break }
        }

        Toast.makeText(this, "Podaci uspesno promenjeni", Toast.LENGTH_SHORT).show()

        findViewById<EditText>(R.id.username).text = null
        findViewById<EditText>(R.id.firstname).text = null
        findViewById<EditText>(R.id.lastname).text = null
        findViewById<EditText>(R.id.address).text = null
        findViewById<EditText>(R.id.telephone).text = null

        intent.putExtra("user", Gson().toJson(user))
        intent.putExtra("users", Gson().toJson(users))
    }


}