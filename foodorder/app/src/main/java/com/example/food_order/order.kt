package com.example.food_order

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.food_order.Adaptor.itemsAdaptor
import com.example.food_order.Adaptor.orderAdaptor
import com.example.food_order.Data.order
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_order.*

class order : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        lateinit var Rc: RecyclerView
        lateinit var adapter: orderAdaptor
        lateinit var mbase: DatabaseReference

        ordbtn.setOnClickListener {



            mbase = FirebaseDatabase.getInstance().getReference().child("Orders").child(orderno.text.toString())

            Rc = findViewById(R.id.recyclerview)

            Rc.layoutManager = LinearLayoutManager(this)

            val option: FirebaseRecyclerOptions<order> =
                FirebaseRecyclerOptions.Builder<order>().setQuery(
                    mbase,
                    order::class.java
                ).build()

            adapter = orderAdaptor(option)

            Rc.adapter = adapter

            adapter.startListening()
        }


    }
}