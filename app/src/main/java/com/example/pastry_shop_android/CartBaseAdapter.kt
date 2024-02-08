package com.example.pastry_shop_android

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CartBaseAdapter(private val cartItems: List<CartItem>):
    RecyclerView.Adapter<CartBaseAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val cartItemImage : ImageView = itemView.findViewById(R.id.imgCartItem)
        val cartItemNameAndAmount : TextView = itemView.findViewById(R.id.cartItemNameAndAmount)
        val cartItemPrice : TextView = itemView.findViewById(R.id.cartItemPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = cartItems[position]
        holder.cartItemImage.setImageResource(currentItem.dessert.picture)
        holder.cartItemNameAndAmount.setText(currentItem.amount.toString() + " x " + currentItem.dessert.name)
        holder.cartItemPrice.setText((currentItem.amount * currentItem.dessert.price).toString())
    }

}