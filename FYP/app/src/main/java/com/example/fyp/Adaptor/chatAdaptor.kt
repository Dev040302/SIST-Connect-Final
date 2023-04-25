package com.example.food_order.Adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.fyp.*
import com.example.fyp.Data.chat
import com.example.fyp.Student_Section.item
import com.example.fyp.Student_Section.qnt
import com.example.fyp.Student_Section.total
import com.example.fyp.Student_Section.totaltxt
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import java.nio.file.Files.size

class chatAdaptor(options: FirebaseRecyclerOptions<chat>) :
    FirebaseRecyclerAdapter<chat, chatAdaptor.chatViewholder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): chatViewholder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return chatViewholder(view)
    }

    override fun onBindViewHolder(holder: chatViewholder, position: Int, model: chat) {
        holder.Name.text = model.getName()
        holder.Message.text = model.getMessage().toString()
    }

    inner class chatViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var Name: TextView
        var Message: TextView

        init {
            Name = itemView.findViewById(R.id.id)
            Message = itemView.findViewById(R.id.msgtxt)
        }
    }
}
