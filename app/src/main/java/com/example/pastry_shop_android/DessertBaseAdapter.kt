package com.example.pastry_shop_android

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson


class DessertBaseAdapter(private val desserts: List<Dessert>, user: User, users: ArrayList<User>,
    notifications: ArrayList<Notification>, carts: ArrayList<CartItem>, comments: ArrayList<Comment>, allDesserts: ArrayList<Dessert>):
    RecyclerView.Adapter<DessertBaseAdapter.MyViewHolder>() {

    private var user = user
    private var users: ArrayList<User> = users
    private var notifications: ArrayList<Notification> = notifications
    private var carts: ArrayList<CartItem> = carts
    private var comments: ArrayList<Comment> = comments
    private var allDesserts: ArrayList<Dessert> = allDesserts

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val dessertImage : ImageView = itemView.findViewById(R.id.img)
        val dessertName : TextView = itemView.findViewById(R.id.dessertName)
        val dessertDescription : TextView = itemView.findViewById(R.id.dessertDescription)
        val dessertMain : RelativeLayout = itemView.findViewById(R.id.dessertMain)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.dessert, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return desserts.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = desserts[position]
        holder.dessertImage.setImageResource(currentItem.picture)
        holder.dessertName.setText(currentItem.name)
        holder.dessertDescription.setText(currentItem.description)

        holder.dessertMain.setOnClickListener(View.OnClickListener {
            var intent: Intent = Intent(holder.itemView.context, DessertItem::class.java)
            intent.putExtra("dessertItemImg", currentItem.picture)
            intent.putExtra("dessertItemName", currentItem.name)
            intent.putExtra("dessertItemPrice", currentItem.price)
            intent.putExtra("dessertItemDescription", currentItem.description)
            intent.putExtra("dessertItemIngredients", currentItem.ingredients)
            intent.putExtra("dessertItemID", currentItem.id)
            intent.putExtra("dessertItem", Gson().toJson(currentItem))

            intent.putExtra("user", Gson().toJson(user))
            intent.putExtra("users", Gson().toJson(users))
            intent.putExtra("desserts", Gson().toJson(allDesserts))
            intent.putExtra("notifications", Gson().toJson(notifications))
            intent.putExtra("carts", Gson().toJson(carts))
            intent.putExtra("comments", Gson().toJson(comments))
            holder.itemView.context.startActivity(intent)
        })
    }

}