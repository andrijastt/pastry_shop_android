package com.example.pastry_shop_android

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PasswordData: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.password_data)
    }

    fun changePassword(view: View){

        val user: User = Gson().fromJson(this.intent.getStringExtra("user"), User::class.java)

        val oldPassword = findViewById<EditText>(R.id.oldPassword).text.toString()
        val newPassword = findViewById<EditText>(R.id.newPassword).text.toString()
        val newPasswordConfirm = findViewById<EditText>(R.id.newPasswordConfirm).text.toString()

        if(!oldPassword.equals(user.password) || !newPassword.equals(newPasswordConfirm)){
            Toast.makeText(this, "Pogresno uneti podaci", Toast.LENGTH_SHORT).show()
            return
        }

        user.password = newPassword
        val itemType = object : TypeToken<List<User>>(){}.type
        val users: MutableList<User> =
            Gson().fromJson<List<User>>(this.intent.getStringExtra("users"), itemType).toMutableList()

        for (i in 0..users.count()) {
            if(users[i].id == user.id){
                users[i] = user
                break }
        }

        Toast.makeText(this, "Lozinka uspesno promenjena", Toast.LENGTH_SHORT).show()

        findViewById<EditText>(R.id.oldPassword).text = null
        findViewById<EditText>(R.id.newPassword).text = null
        findViewById<EditText>(R.id.newPasswordConfirm).text = null

        intent.putExtra("user", Gson().toJson(user))
        intent.putExtra("users", Gson().toJson(users))
    }

}