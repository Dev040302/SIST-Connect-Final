package com.example.food_order.Adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.food_order.*
import com.example.food_order.Data.order
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class orderAdaptor(options: FirebaseRecyclerOptions<order>) :
    FirebaseRecyclerAdapter<order, orderAdaptor.orderViewholder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): orderViewholder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.order, parent, false)
        return orderViewholder(view)
    }

    override fun onBindViewHolder(holder: orderViewholder, position: Int, model: order) {
        holder.Name.text = model.getName()
        holder.Qnty.text = model.getQnty().toString()
    }



    inner class orderViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var Name: TextView
        var Qnty: TextView

        init {
            Name = itemView.findViewById(R.id.name)
            Qnty = itemView.findViewById(R.id.quantity)
        }
    }
}