package com.example.pastry_shop_android

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotificationBaseAdapter (private val notifactions: List<Notification>):
    RecyclerView.Adapter<NotificationBaseAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val notificationText : TextView = itemView.findViewById(R.id.notificationText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.notification, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return notifactions.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = notifactions[position]
        val temp = currentItem.items.size
        for(i in 0..temp){
            holder.notificationText.append(currentItem.itemAmount[i].toString() + "x " + currentItem.items[i].name)
            if(i != temp){
                holder.notificationText.append(",")
            }
            holder.notificationText.append(" ")
        }

    }

}