package com.example.fyp.Adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fyp.Data.feeds
import com.example.fyp.R
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_add_student.*

class feedsAdaptor(options: FirebaseRecyclerOptions<feeds>) :
    FirebaseRecyclerAdapter<feeds, feedsAdaptor.feedsViewholder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): feedsViewholder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.feeds, parent, false)
        return feedsViewholder(view)
    }

    override fun onBindViewHolder(holder: feedsViewholder, position: Int, model: feeds) {
        holder.regno.text=model.getRegno()
        holder.content.text=model.getContent()
        var feedurl = model.getImgurl()
        var ppurl = ""
        Picasso.get().load(feedurl).into(holder.feed)
        var res = FirebaseDatabase.getInstance().reference.child("Students").child(holder.regno.text.toString())

        res.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                ppurl=snapshot.child("image").getValue().toString()
                Picasso.get().load(ppurl).into(holder.profileimg)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })




    }



    inner class feedsViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var regno:TextView
        var profileimg:ImageView
        var feed:ImageView
        var content:TextView

        init {
            regno=itemView.findViewById(R.id.profilename)
            profileimg=itemView.findViewById(R.id.profilepic)
            feed=itemView.findViewById(R.id.feedimg)
            content=itemView.findViewById(R.id.feedcontext)
        }
    }
}