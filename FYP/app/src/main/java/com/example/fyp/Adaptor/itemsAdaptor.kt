package com.example.food_order.Adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.fyp.*
import com.example.fyp.Data.items
import com.example.fyp.Student_Section.item
import com.example.fyp.Student_Section.qnt
import com.example.fyp.Student_Section.total
import com.example.fyp.Student_Section.totaltxt
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import java.nio.file.Files.size

class itemsAdaptor(options: FirebaseRecyclerOptions<items>) :
    FirebaseRecyclerAdapter<items, itemsAdaptor.itemsViewholder>(options) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemsViewholder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.items, parent, false)
        return itemsViewholder(view)
    }

    override fun onBindViewHolder(holder: itemsViewholder, position: Int, model: items) {
        holder.Name.text = model.getName()
        holder.Rate.text = "₹"+model.getRate().toString()

        holder.sub.setOnClickListener {
            holder.qnty.text = (++holder.quantity).toString()
            total=total+Integer.parseInt(holder.Rate.text.toString())
            totaltxt.text="Total :- ₹" + total
            send(holder.Name.text.toString(),holder.quantity)
        }

        holder.add.setOnClickListener {
            if(holder.quantity>0) {
                holder.qnty.text = (--holder.quantity).toString()
                total=total-Integer.parseInt(holder.Rate.text.toString())
                totaltxt.text="Total :- ₹" + total
                send(holder.Name.text.toString(),holder.quantity)
            }
            else{
                holder.quantity=0
                send(holder.Name.text.toString(),0)
            }

        }
    }

    private fun send(itm: String, quantity: Int) {

        println(itm + " " +quantity.toString())
        if(!item.isEmpty()){
            var available=false
            var avaiind=0
            for(i in 0..(item.size-1)){

                if(item[i] == itm){
                    available=true
                    avaiind=i
                    break
                }
            }
            if(available){
                qnt[avaiind]=quantity
            }
            else{
                item+=itm
                qnt.add(quantity)
            }
        }
        else{
            item+=itm
            qnt.add(quantity)
        }


        println(item)
        println(qnt)

    }


    inner class itemsViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var Name: TextView
        var Rate: TextView
        var add: Button
        var sub:Button
        var qnty:TextView
        var quantity=0

        init {
            Name = itemView.findViewById(R.id.name)
            Rate = itemView.findViewById(R.id.rate)
            add=itemView.findViewById(R.id.btnadd)
            sub=itemView.findViewById(R.id.btnsub)
            qnty=itemView.findViewById(R.id.qnty)
        }
    }
}
