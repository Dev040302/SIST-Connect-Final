package com.example.fyp.Admin_Section

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.food_order.Adaptor.orderAdaptor
import com.example.fyp.Data.order
import com.example.fyp.R
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_order_bill.*

class OrderBill : AppCompatActivity() {

    lateinit var Rc: RecyclerView
    lateinit var adapter: orderAdaptor
    lateinit var mbase: DatabaseReference
    lateinit var orderid:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_bill)

        orderid= intent.getStringExtra("orderId").toString()

        mbase = FirebaseDatabase.getInstance().getReference().child("Orders").child(orderid)

        Billno.text="Bill.No :- " + orderid

        Rc = findViewById(R.id.recyclerview)

        Rc.layoutManager = LinearLayoutManager(this)

        val option: FirebaseRecyclerOptions<order> =
            FirebaseRecyclerOptions.Builder<order>().setQuery(
                mbase,
                order::class.java
            ).build()

        adapter = orderAdaptor(option)

        Rc.adapter = adapter

    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }


    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }
}