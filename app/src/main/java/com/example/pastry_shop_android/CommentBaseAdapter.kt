package com.example.pastry_shop_android

import android.graphics.Typeface
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CommentBaseAdapter (private val comments: List<Comment>):
    RecyclerView.Adapter<CommentBaseAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val commentText : TextView = itemView.findViewById(R.id.commentText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.comment, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = comments[position]
        var ss = SpannableString(currentItem.user.firstname + " " + currentItem.user.lastname + ": ")
        ss.setSpan(StyleSpan(Typeface.BOLD), 0, ss.length, 0)
        holder.commentText.append(ss)
        holder.commentText.append(currentItem.comment)
    }

}