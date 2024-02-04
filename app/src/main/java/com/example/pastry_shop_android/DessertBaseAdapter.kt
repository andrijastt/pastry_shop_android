package com.example.pastry_shop_android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class DessertBaseAdapter(private val desserts: List<Dessert>):
    RecyclerView.Adapter<DessertBaseAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val dessertImage : ImageView = itemView.findViewById(R.id.img)
        val dessertName : TextView = itemView.findViewById(R.id.dessertName)
        val dessertDescription : TextView = itemView.findViewById(R.id.dessertDescription)


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
    }

}